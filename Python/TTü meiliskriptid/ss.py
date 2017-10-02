lights = {(i, j): "off" for i in range(1000) for j in range(1000)}
print(lights[(0, 0)], len(lights))
with open("advent.txt") as file:
    for line in file:
        instructions = line.strip().split(" ")
