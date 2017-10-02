"""
Program to create connected graphs and to color them using 4 colors.

@author: raluga
"""
import networkx as nx


def get_region_colors(adjacency_matrix):
    """
    Calculate color for each region in the graph.

    Input: adjacency_matrix - graph adjacency_matrix where [x][y] = 1 means
                              that region x and region y share common border.

    Output: colors_dictionary - dictionary object where key is region number
                                and value is color (witches - 1, vampires - 2, werewolves - 3, hybrids - 4)
    """
    re_graph = nx.MultiGraph()
    colors_dictionary = {}
    for i in range(len(adjacency_matrix)):  # add all edges to graph
        for j in range(len(adjacency_matrix[0])):
            if adjacency_matrix[i][j] == 1 and (j, i) not in re_graph.edges():
                re_graph.add_edge(i, j)
            re_graph.add_node(i, color="")
    for i in range(len(re_graph.nodes())):  # lands without connections are added with color 1
        if len(re_graph.neighbors(i)) < 1:
            colors_dictionary[i] = 1
        decide_node_color(re_graph, i)
        for j in re_graph.neighbors(i):
            decide_node_color(re_graph, j)
    for i in range(len(re_graph.nodes())):
        colors_dictionary[i] = re_graph.node[i]['color']
    return colors_dictionary


def decide_node_color(graph, node):
    """
    Decide the color of the node from the range 1 to 4.

    Thi shall be judged by the almighty, all powerful God.
    """
    colors = {1, 2, 3, 4}
    set_neighbors = neighbor_colors(graph, node)
    if len(set_neighbors) == 0 and graph.node[node]['color'] == "":  # if the node and neighbor nodes have no colors
        graph.node[node]['color'] = 1
    if len(set_neighbors) > 0 and graph.node[node]['color'] == "":
        graph.node[node]['color'] = min(colors - set_neighbors)


def neighbor_colors(graph, node):
    """
    Search for neigbor colors and create a set.

    Introduce yourself, my fellow friend.
    """
    colors = set()
    for i in range(len(graph.neighbors(node))):
        neighbor = graph.neighbors(node)[i]
        if graph.node[neighbor]['color'] != "":
            colors.add(graph.node[neighbor]['color'])
    return colors


def get_graph(adjacency_matrix):
    """
    Create colored graph from adjacency_matrix.

    Input: adjacency_matrix - graph adjacency_matrix where [x][y] = 1 means
                              that region x and region y share common border.
    Output: network_x_graph_object - plottable networkx graph
    """
    re_graph = nx.MultiGraph()
    colors_dictionary = {}
    for i in range(len(adjacency_matrix)):  # add all edges to graph
        for j in range(len(adjacency_matrix[0])):
            if adjacency_matrix[i][j] == 1 and (j, i) not in re_graph.edges():
                re_graph.add_edge(i, j)
            re_graph.add_node(i, color="")
    for i in range(len(re_graph.nodes())):  # lands without connections are added with color 1
        if len(re_graph.neighbors(i)) < 1:
            colors_dictionary[i] = 1
        decide_node_color(re_graph, i)
        for j in re_graph.neighbors(i):
            decide_node_color(re_graph, j)
    return re_graph


if __name__ == "__main__":
    matrix = [
        [0, 1, 0, 1, 1, 1],
        [1, 0, 0, 1, 0, 0],
        [0, 0, 0, 1, 0, 1],
        [1, 1, 1, 0, 0, 1],
        [1, 0, 0, 0, 0, 1],
        [1, 0, 1, 1, 1, 0]
    ]
    # matrix = [[0]]
    x = get_region_colors(matrix)
    print(x.items())
