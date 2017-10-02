import sys

for line in sys.stdin:
    for char in line:
        if char != "\n":
            print(chr(ord(char)-7), end="")
    print()