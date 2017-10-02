import csv
from random import randint

words_list = ['sau23sage', 'bl65er', 'pen1cil', 'clou6d', 'mo4on', 'w6ater', 'comp65uter', 'sch65ool', 'netw43ork', 'ha0mmer',
              'walk32ing', 'vi32ently', 'mediocre', 'liter5', 'chair1', 't2wo', 'wi4ndow', 'c5ords', 'mu7sical', 'zeb4ra',
              'xyl41hone', 'p12nguin', 'hom2e', '1dog', 'f3inal', 'i23nk', 'tea12cher', '6fun', 'w5ebsite', 'banana', 'un2cle',
              'sof4tly', 'meg4a', 'ten', 'aw2esome', 'atta3t5ch', 'bl3ue', 'i2nternet', 'bot2tle', 'tig4ht', 'zone', 'tom4ato',
              'pris5on', 'hydr5o', 'cl423eaning', 'telivision', 'sen3d', '32oeg', 'c3up', 'bo3ok', 'zoom2ing', 'fall5ing', 'evily',
              'ga3mer', 'lid', 'juic2e', 'mon77er', 'captain', 'bon2ing', 'lo4udly', 'thuddi2ng', 'guit24ar', 'shavin2g',
              'hair', 'socce23r', 'w2ater', 'racke33t', 'ta53ble', 'lat5e', 'med55ia', 'desktop6', 'fl5ipper', 'c4lub', 'flying',
              'smoot7h', 'mons2ter', 'pu88le', 'gd545ian', 'b4old', 'hy5perl', 'prnta4tion', 'worl4d', 'n7aional',
              'com8ment', 'els23ent', 'magi3c', 'l2ion', 's4and', 'crus2t', 't3oast', '2jam', 'hun3ter', 'fores64t', 'fo2arging',
              'sile7ntly', 'tawesomated', 'j99hing', 'p00g']

import csv

print(len(words_list))
data = []  # Buffer list
addedPasses = []
with open("in.csv", "rt") as the_file:
    reader = csv.reader(the_file, delimiter=" ")
    for row in reader:
        if len(row) != 0:
            added = False
            while (not added):
                randomPass = [words_list[randint(0, 97)] for i in range(3)]
                if randomPass not in addedPasses:
                    addedPasses.append(randomPass)
                    added = True
            row.append(";" + "".join(randomPass)+";500")
            data.append(row)

    with open('out.csv', 'w', newline='') as outfile:
        writer = csv.writer(outfile, delimiter=" ")
        for row in data:
            writer.writerow(row)
