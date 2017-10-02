def create_mine_map(mines, layers):
    """
    program that makes a matrix map from given mines and layers

    this program takes in mines in tuples (layer,sector) and layers (int)
    it returns a matrix map (polar_map), which  has mines marked as X and numbers
    counting the amount of mines which are surrounded by them
    the layers form a circle/square with 4 corners
    """
    polar_map = []
    if layers > 0:  # if less, the cycle won't start
        for i in range(0, layers + 1):  # makes an empty matrix
            polar_map.append([0, 0, 0, 0])
        for mine in mines:  # gets rid of excess mines
            if mines.count(mine) > 1:
                for i in range(0, mines.count(mine) - 1):
                    mines.remove(mine)
        for mine in mines:
            if mine[0] == 0 and mine[1] in range(0, 4):  # mine is in the first layers
                for i in range(0, 4):
                    polar_map[0][i] += 1
                if layers > 1:  # if there are more layers than 1
                    if mine[1] == 0:
                        polar_map[1][0] += 1
                        polar_map[1][1] += 1
                        polar_map[1][3] += 1
                    elif mine[1] == 3:
                        polar_map[1][3] += 1
                        polar_map[1][2] += 1
                        polar_map[1][0] += 1
                    elif mine[1] == 1 or mine[1] == 2:
                        for j in range(mine[1] - 1, mine[1] + 2):
                            polar_map[1][j] += 1
            elif 0 < mine[0] <= layers:  # if the mine is on another layer
                if mine[1] == 0:
                    if mine[0] + 1 == layers + 1:  # checks if there's another layers after the mine
                        stopp = 1
                    else:
                        stopp = 2
                    for j in range(-1, stopp):
                        polar_map[mine[0] + j][0] += 1
                        polar_map[mine[0] + j][1] += 1
                        polar_map[mine[0] + j][3] += 1
                elif mine[1] == 3:
                    if mine[0] + 1 == layers + 1:
                        stopp = 1
                    else:
                        stopp = 2
                    for j in range(-1, stopp):
                        polar_map[mine[0] + j][0] += 1
                        polar_map[mine[0] + j][2] += 1
                        polar_map[mine[0] + j][3] += 1
                elif mine[1] == 1 or mine[1] == 2:
                    for j in range(mine[1] - 1, mine[1] + 2):
                        polar_map[mine[0] - 1][j] += 1
                        polar_map[mine[0]][j] += 1
                        if mine[0] + 1 != layers + 1:
                            polar_map[mine[0] + 1][j] += 1

        for i in range(0, len(mines)):  # prints only the mines that are within given layer range
            if 0 <= mines[i][0] < layers and mines[i][1] in range(0, 4):
                polar_map[mines[i][0]][mines[i][1]] = "X"
        polar_map.pop()
    return polar_map
