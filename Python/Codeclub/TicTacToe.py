def check_grid(tto_game):
    # check for vertical wins
    for i in range(3):
        values = set(tto_game[i])
        if len(values) == 1 and 0 not in values:
            winner = values.pop()
            return winner
    # check for horizontal wins
    for i in range(3):
        values = {grid[i] for grid in tto_game}
        if len(values) == 1 and 0 not in values:
            winner = values.pop()
            return winner
    # check for upper left to lower right diagonal
    values = {tto_game[0][0], tto_game[1][1], tto_game[2][2]}
    if len(values) == 1 and 0 not in values:
        winner = values.pop()
        return winner
    # check for lower left to upper right diagonal
    values = {tto_game[2][0], tto_game[1][1], tto_game[0][2]}
    if len(values) == 1 and 0 not in values:
        winner = values.pop()
        return winner
    return 0


game = [
    [1, 2, 0],
    [2, 2, 0],
    [2, 1, 1]
]
print(check_grid(game))
