__author__ = 'Ragnar'


def read_dna_data_from_file(filename):
    """
    Reads a text file and returns a single DNA string

    Arguments:
    filename -- file name (e.g., "DNA.txt")

    Returns:
    A string of DNA (TTAAGGCCAATT)
    """
    with open(filename) as file:
        dna = file.read().replace("\n", "").replace("\r", "")
    return dna


def transcribe_dna_to_rna(dna):
    """
    Reads in a dna string and transcribes it into RNA

    Arguments:
    dna -- a string of DNA

    Returns:
    rna - a string of RNA (TTUUCCGG)
    """
    if len(dna) == 0:
        return None
    rna = ""
    for character in dna:
        if character == "T":
            rna += "A"
        elif character == "A":
            rna += "U"
        elif character == "G":
            rna += "C"
        elif character == "C":
            rna += "G"
        else:
            return None
    return rna


def translate_rna_to_protein(rna):
    """
    Reads in a string of RNA and translates it into a string of proteins

    Arguments:
    rna -- a string of RNA

    Returns:
    string_protein -- a string of Proteins
    """
    if rna == "":
        return None
    base_aminoacids = "UCAG"
    proteins = "FFLLSSSSYY**CC*WLLLLPPPPHHQQRRRRIIIMTTTTNNKKSSRRVVVVAAAADDEEGGGG"
    string_protein = ""
    rna_codons_list = rna_to_codons(rna)
    for codon in rna_codons_list:
        for acid in codon:
            if acid not in base_aminoacids:
                return None
        index = 0
        index += base_aminoacids.index(codon[0]) * 16
        index += base_aminoacids.index(codon[1]) * 4
        index += base_aminoacids.index(codon[2])
        if proteins[index] == "M":
            string_protein += "Met"
        elif proteins[index] == "*":
            string_protein += "Stop"
        else:
            string_protein += proteins[index]
    return string_protein


def determine_species(classification_file):
    """
    Uses all other functions to find the appearance of protein string in
    classification file to be found in the DNA string in "EX05_DNA.txt"

    Arguments:
    classification_file -- a .csv file containing Estonian name, latin name and protein sequence of each animal on a seperate line:
    Punarebane, Foximus Fox, PSPAIEVMCOQIETFFFFSDCSStopSMetABVBANANAWE

    Returns:
    species_count_dictionary -- a dictionary containing the animals which appeared in the DNA string {
    "Foximus Fox":5, "Wolfimus Wolf:3"}
    """
    dna_sequence = read_dna_data_from_file("EX05_DNA.txt")
    rna_sequence = transcribe_dna_to_rna(dna_sequence)
    protein_sequence = translate_rna_to_protein(rna_sequence)
    species_list = read_species_file_into_list(classification_file)
    species_count_dict = count_species_in_protein_string(species_list, protein_sequence)
    return species_count_dict


def rna_to_codons(rna_sequence):
    """
    Reads in a string of RNA and returns it in a list as pieces of 3-letters

    Arguments:
    rna_sequence -- a string of RNA (TTUTTGTTC...)

    Returns:
    rna_codons_list -- a list of RNA [[TTU],[TTG],[TTC],...]
    """
    rna_codons_list = []
    for i in range(0, len(rna_sequence), 3):
        rna_codons_list.append(rna_sequence[i:i + 3])
    return rna_codons_list


def read_species_file_into_list(species_file):
    """
    Reads the species file and returns a list of unique animals with unique protein sequences

    Arguments:
    species_file -- a .csv file with the description(see in function determine_species() )

    Returns:
    species_list -- a nice list of ladin names and protein string [["Wolfimus Wolf",PSAPDADWDA],...[]]
    """
    file_lines = []
    species_list = []
    with open(species_file) as file:
        for line in file:
            file_lines.append(line)
    file_lines.pop(0)
    unique_file_lines = set(file_lines)
    for line in unique_file_lines:
        species = line.split(",")
        species[2] = species[2].replace("\n", "")
        species_list.append([species[1], species[2]])
    return species_list


def count_species_in_protein_string(species, protein_string):
    """
    Checks how many times a species protein string exists in the long string of protein from EX05_DNA.txt(already transcribed etc.)

    Arguments:
    species -- a nice list of ladin names and protein string [["Wolfimus Wolf",PSAPDADWDA],...[]]
    protein_string -- a string of proteins

    Returns:
    final_species_count - a dictionary of animals and how many times they occurred in the protein string
    {"Wolfimus Wolf":4, "Foximus Fox":2,...}
    """
    species_count = {}
    for animal in species:
        if animal[0] in species_count:
            species_count[animal[0]] += protein_string.count(animal[1])
        else:
            species_count[animal[0]] = protein_string.count(animal[1])
    final_species_count = {k: v for k, v in species_count.items() if v != 0}
    return final_species_count
