import sys

for line in sys.stdin:
    print(" ".join(word[::-1] for word in line.split()))