import search


def read_cities_from_file(filename):
    with open(filename) as file:
        file.readline()
        city_map = []
        for line in file:
            city_map.append(line.strip().split(" "))
    return city_map


class TSP(search.Problem):

    def __init__(self, filename):
        self.city_matrix = read_cities_from_file(filename)
        self.initial = list(range(len(self.city_matrix)))

    def actions(self, state):
        return [(i, k) for i in range(len(state) - 1) for k in range(i + 1, len(state))]

    def result(self, state, action):
        i, k = action
        return state[0:i] + list(reversed(state[i:k + 1])) + state[k + 1:]

    def value(self, state):
        path_length = 0
        for i in range(len(state) - 1):
            path_length += int(self.city_matrix[state[i]][state[i+1]])
        path_length += int(self.city_matrix[state[len(state)-1]][state[0]])
        return -path_length


problem = TSP("gr17.txt")

g = search.hill_climbing(problem)
print(g)
print(-problem.value(g))
g = search.simulated_annealing(problem)
print(g)
print(-problem.value(g))

g = search.simulated_annealing(problem, search.exp_schedule(limit=10000))
print(g)
print(-problem.value(g))
