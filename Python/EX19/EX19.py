"""
Module to implement in-depth search algorithms recursively and iteratively.

@author: raluga
"""
import networkx as nx


def find_relative_iterative(graph, name):
    r"""
    Iterative implementation.

    Find how many nodes are visited during the depth first search.
    The algorithm must work with cycles and look through every node if
    name doesn't exist in the graph.
    Example graph:

       Mary     <-- find(Tim)  == 5

       /       \      <-- find(Mary) == 1

    Jane Dane   <-- find(Jane) == 2

     /           \     <-- find(John) == 3

    John   Mike  <-- find(Dane) == 4

                <-- find(Mike) == 5

    (Assumed that left half is always preferred)
    Args: graph - family tree (networkx graph object)
          name  - searched relative (string)
    Return: number of nodes visited (int)
    """
    seen = set()
    queue = [name]
    while queue:
        print(queue)
        node = queue.pop(0)
        if node not in seen:
            seen.add(node)
            if node == name:
                return len(seen)
            for idx, neighbor in enumerate(set(graph.neighbors(node)) - seen):
                if neighbor not in seen:
                    queue.insert(idx, neighbor)
    return len(seen)


def find_relative_recursive(graph, name):
    r"""
    Recursive implementation.

    Find how many nodes are visited during the depth first search.
    The algorithm must work with cycles and look through every node if
    name doesn't exist in the graph.
    Example graph:

       Mary     <-- find(Tim)  == 5

       /      \      <-- find(Mary) == 1

    Jane Dane   <-- find(Jane) == 2

     /          \     <-- find(John) == 3

    John   Mike  <-- find(Dane) == 4

                <-- find(Mike) == 5

    (Assumed that left half is always preferred)
    Args: graph - family tree (networkx graph object)
          name  - searched relative (string)
    Return: number of nodes visited (int)
    """

    def recursive_search(node, end, graph, visited=None):
        """
        A recursive implementation of depth-first search.

        It's like mining tunnels, you make one long tunnel and then come back to make another.
        Almost like what Eesti Energia is doing at the moment.
        Woop woop.
        """
        if visited is None:
            visited = set()
        visited.add(node)
        if node == end:
            return visited
        for neighbor in graph.neighbors(node):
            if neighbor not in visited:
                if end in recursive_search(neighbor, end, graph, visited):
                    break
        return visited
    return len(recursive_search("Mary", name, graph))


if __name__ == "__main__":
    example_graph = nx.Graph()
    example_graph.add_edge("Mary", "Jane")
    example_graph.add_edge("Jane", "John")
    example_graph.add_edge("Mary", "Dane")
    example_graph.add_edge("Dane", "Mike")
    x = find_relative_recursive(example_graph, "Mike")
    print(x)
