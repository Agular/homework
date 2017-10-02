import math

magnet = 4 * math.pi * math.pow(10, -7)
S1 = 1052.1 * math.pow(10, -6)
N1 = 200
N = 360
l = 250 * math.pow(10, -3)
n = N / l
A = 1.72
w = 100 * math.pi
fexp = []
data = [17.485, 22.255, 28.635, 37.305, 49.065, 64.02, 82.355, 102.095, 120.74, 137.13, 149.605, 158.955, 165.305,
        169.45, 171.565, 172.4]
for U in data:
    fexp.append((U * math.pow(10, -3)) / (magnet * S1 * N1 * n * w * A))

l = 250
D = 150
fteooria = []

for x in range(225, -15, -15):
    fteooria.append((l / 2 - x) / math.sqrt(math.pow((l - 2 * x), 2) + math.pow(D, 2)) +
                    (l / 2 + x) / math.sqrt(math.pow((l + 2 * x), 2) + math.pow(D, 2))
                    )
fdelta = []
for i in range(len(fteooria)):
    fdelta.append(fexp[i] - fteooria[i])
keskmine = 0
for i in range(len(fdelta)):
    keskmine += (fdelta[i] / fteooria[i])
print(keskmine/len(fdelta))
