import math

l = 250
D = 150
fteooria = []

for x in range(225, -15, -15):
    fteooria.append((l / 2 - x) / math.sqrt(math.pow((l - 2 * x), 2) + math.pow(D, 2)) +
                    (l / 2 + x) / math.sqrt(math.pow((l + 2 * x), 2) + math.pow(D, 2))
                    )
