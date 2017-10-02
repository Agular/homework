"""
A module for creating graphs for labyrinths and finding the shortest route in them.

@author: raluga
Happy finding!
"""
import networkx as nx


def create_graph(labyrinth):
    """
    Create a NetworkX graph from an ASCII-labyrinth.

    And God shall make a plan for his travellings of fortune-finding and beautiful goddesses.
    """
    maze_graph = nx.MultiGraph()
    maze_nodes = create_mazelist(labyrinth)
    if len(maze_nodes) == 1:
        maze_graph.add_node("A0")
    else:
        for edge in maze_nodes:
            maze_graph.add_edge(edge[0], edge[1], weight=1)
        for i in maze_graph.nodes():
            if len(maze_graph.neighbors(i)) == 2 and i != "A0" and i != max(maze_graph):
                x = maze_graph[i][maze_graph.neighbors(i)[0]][0]["weight"]
                y = maze_graph[i][maze_graph.neighbors(i)[1]][0]["weight"]

                maze_graph.add_edge(maze_graph.neighbors(i)[0], maze_graph.neighbors(i)[1], weight=x + y)

                maze_graph.remove_edge(i, maze_graph.neighbors(i)[1])
                maze_graph.remove_edge(i, maze_graph.neighbors(i)[0])
                maze_graph.remove_node(i)
    return maze_graph


def create_mazelist(ascii_list):
    """
    Create a tuple list out of all free spaces in the maze.

    And the journey presents it's paths.
    """
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    maze_list = []
    columns = len(ascii_list[0])
    rows = len(ascii_list)
    for row in range(rows):
        for col in range(columns):
            if ascii_list[row][col] == " ":
                if col + 1 < columns:
                    if ascii_list[row][col + 1] == " ":
                        maze_list.append((alphabet[row] + str(col), alphabet[row] + str(col + 1)))
                if row + 1 < rows:
                    if ascii_list[row + 1][col] == " ":
                        maze_list.append((alphabet[row] + str(col), alphabet[row + 1] + str(col)))
    return maze_list


def find_shortest_path(labyrinth):
    """
    Find the shortest path from start to exit of given graph of a labyrinth.

    God is as almighty as he is lazy so he will never take the longest walk to Las Vegas Casino Royale.
    """
    moves = create_graph(labyrinth)
    return nx.dijkstra_path(moves, min(moves), max(moves))


if __name__ == "__main__":
    x = create_graph([[' ', 'X', 'X', 'X'],
                      [' ', ' ', ' ', 'X'],
                      ['X', 'X', ' ', ' '],
                      ['X', ' ', ' ', 'X'],
                      ['X', ' ', 'X', 'X'],
                      ['X', ' ', ' ', 'X'],
                      ['X', 'X', ' ', ' ']])
    # x = create_graph([' '])
    print(x.edges(data=True))
    tee = find_shortest_path([[' ', ' ', ' ', ' ', ' '],
                              [' ', 'X', ' ', 'X', ' '],
                              [' ', ' ', ' ', 'X', ' ']])
    print(tee)
import string
alphabet = string.ascii_lowercase
print(alphabet)