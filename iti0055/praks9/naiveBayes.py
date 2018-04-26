import csv


def read_training_data_file(filename):
    with open(filename, 'rt') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        training_data = []
        for row in spamreader:
            training_data.append(row)
    return training_data


# print(read_training_data_file('restaurant.csv'))


def get_truth_tables(training_data):
    yes = {n: {} for n in range(len(training_data[0]) - 1)}
    no = {n: {} for n in range(len(training_data[0]) - 1)}
    total = {n: {} for n in range(len(training_data[0]) - 1)}
    for row in training_data:
        for idx, elem in enumerate(row):
            if idx is not len(row) - 1:
                if row[len(row) - 1] == "Yes":
                    if elem not in yes[idx]:
                        yes[idx][elem] = 1
                    else:
                        yes[idx][elem] += 1
                else:
                    if elem not in no[idx]:
                        no[idx][elem] = 1
                    else:
                        no[idx][elem] += 1
                if elem not in yes[idx]:
                    if elem not in total[idx]:
                        total[idx][elem] = 1
                    else:
                        total[idx][elem] += 1
    return yes, no, total


def get_prob_tables(yes_table, no_table):
    yes_table_prob = {n: {} for n in range(len(yes_table))}
    no_table_prob = {n: {} for n in range(len(no_table))}
    for atrib in range(len(yes_table)):

        yes_atrib_n_answers = len(yes_table[atrib])
        no_atrib_n_answers = len(no_table[atrib])

        yes_n_occurrences = sum(yes_table[0][answer] for answer in yes_table[0])
        no_n_occurrences = sum(no_table[0][answer] for answer in no_table[0])

        for answer in yes_table[atrib]:
            yes_table_prob[atrib][answer] = (float(yes_table[atrib][answer]) + 1) / (
                    float(yes_n_occurrences) + yes_atrib_n_answers)
        for answer in no_table[atrib]:
            no_table_prob[atrib][answer] = (float(no_table[atrib][answer]) + 1) / (
                    float(no_n_occurrences) + no_atrib_n_answers)
    return yes_table_prob, no_table_prob


def naive_bayes(filename, test_data):
    training_data = read_training_data_file(filename)
    yes_table, no_table, total_table = get_truth_tables(training_data)
    yes_table_prob, no_table_prob = get_prob_tables(yes_table, no_table)

    yes_n_occurrences = sum(yes_table[0][answer] for answer in yes_table[0])
    no_n_occurrences = sum(no_table[0][answer] for answer in no_table[0])
    total_occurrences = yes_n_occurrences + no_n_occurrences

    yes_class_percent = float(yes_n_occurrences) / total_occurrences
    no_class_percent = float(no_n_occurrences) / total_occurrences

    print(yes_class_percent, no_class_percent)
    print()

    for atrib, evidence in enumerate(test_data):
        if evidence in yes_table_prob[atrib]:
            yes_class_percent *= yes_table_prob[atrib][evidence]
        else:
            yes_class_percent = 0
        if evidence in no_table_prob[atrib]:
            no_class_percent *= no_table_prob[atrib][evidence]
        else:
            no_class_percent = 0;

    if yes_class_percent > no_class_percent:
        print("Test data indicates class YES!")
    else:
        print("Test data indicates class NO!")


if __name__ == "__main__":
    test_data = ['Yes', 'No', 'No', 'Yes', 'Some', '$$$', 'No', 'Yes', 'French', '0-10']
    naive_bayes('restaurant.csv', test_data)
