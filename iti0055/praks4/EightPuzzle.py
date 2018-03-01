import search


# state = [["1", "2", "3"], ["4", "5", "6"], ["7", "8", "X"]]
# actions - UP, DOWN, LEFT, RIGHT// this shows the way in which direction is a tile moved so it moves to the free space.
class EightPuzzle(search.Problem):

    def actions(self, state):
        state_list = list(map(list, state))
        row_idx = -1
        col_idx = -1
        for r_idx, row in enumerate(state_list):
            for c_idx, colValue in enumerate(row):
                if colValue is None:
                    row_idx = r_idx
                    col_idx = c_idx
                    break
        actions = []
        if row_idx > 0:
            actions.append("DOWN")
        if row_idx < 2:
            actions.append("UP")
        if col_idx > 0:
            actions.append("RIGHT")
        if col_idx < 2:
            actions.append("LEFT")
        return actions

    def result(self, state, action):
        state_list = list(map(list, state))
        row_idx = -1
        col_idx = -1
        for r_idx, row in enumerate(state_list):
            for c_idx, colValue in enumerate(row):
                if colValue is None:
                    row_idx = r_idx
                    col_idx = c_idx
                    break
        if action is "UP":
            state_list[row_idx][col_idx] = state_list[row_idx + 1][col_idx]
            state_list[row_idx + 1][col_idx] = None
        if action is "DOWN":
            state_list[row_idx][col_idx] = state_list[row_idx + -1][col_idx]
            state_list[row_idx - 1][col_idx] = None
        if action is "RIGHT":
            state_list[row_idx][col_idx] = state_list[row_idx][col_idx - 1]
            state_list[row_idx][col_idx - 1] = None
        if action is "LEFT":
            state_list[row_idx][col_idx] = state_list[row_idx][col_idx + 1]
            state_list[row_idx][col_idx + 1] = None
        return tuple(map(tuple, state_list))

    def goal_test(self, state):
        return hash(state) == hash(self.goal)

    def path_cost(self, c, state1, action, state2):
        return c + 1  # uus cost peale ühe sammu tegemist


# comparison
goal = ((1, 2, 3), (4, 5, 6), (7, 8, None))
inistate1 = ((1, 8, 2), (None, 4, 3), (7, 6, 5))
inistate2 = ((5, 4, None), (6, 1, 8), (7, 3, 2))
inistate3 = ((8, 6, 7), (2, 5, 4), (3, None, 1))

problem1 = EightPuzzle(inistate1, goal)


search.compare_searchers([problem1], ["Strateegia", "algolek1"], searchers=[search.breadth_first_search,
                                                                              search.iterative_deepening_search])

# esimene on mitu actionit tehti, teine on see, mitu korda goal testi kutsuti v2lja, kolmas n2itab, mitu korda
# kutsutakse resulti



#goalnode = search.breadth_first_search(problem)
# sammud (tegevused, käigud) algolekust lõppolekuni
#print(goalnode.solution())
# olekud algolekust lõppolekuni
#print(goalnode.path())