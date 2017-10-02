import sys

for line in sys.stdin:
    values = line.split()
    print(int(values[0]) * 2 * int(values[1]))
