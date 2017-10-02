import csv

data = []  # Buffer list
with open("listid.csv", "rt") as the_file:
    reader = csv.reader(the_file, delimiter=" ")
    for row in reader:
        checkables = row[4].split(";")
        if (checkables[1] != "0"):
            print(checkables[1])
            data.append(row)

    with open('listidClean.csv', 'w', newline='') as outfile:
        writer = csv.writer(outfile, delimiter=" ")
        for row in data:
            writer.writerow(row)
