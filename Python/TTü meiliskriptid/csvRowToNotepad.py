import csv

data = []  # Buffer list
with open("listidClean.csv", "rt") as the_file:
    reader = csv.reader(the_file, delimiter=";")
    for row in reader:
        data.append(row[0])

    with open('ttuListid.txt', 'w', newline='') as outfile:
        for header in data:
            outfile.write(header + "\n")
