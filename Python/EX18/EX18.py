"""
Created on 28 Nov 2015.

@author: raluga
"""
import networkx as nx


def bfs_memory(nodes, probability, start, end):
    """
    Function to implement breath-first search memoryzing (using a queue) the nodes already visited.

    Using NetworkX to generate a random unweighted graph with nodes and probability of forming edges.
    Arguments:
    nodes: number of nodes in the random graph. Should be >10 (e.g. 20)
    probability: probability to generate edges between nodes (e.g. 0.2)
    start: starting node for breath-first search (e.g. 2)
    end: ending node for breath-first search (e.g. 10)
    Returns:
    set:nodes visited
    """
    graph = nx.gnp_random_graph(nodes, probability)
    unweighted = {}
    for edge in sorted(graph.edges()):
        if edge[0] not in unweighted.keys():
            unweighted[edge[0]] = {edge[1]}
        else:
            unweighted[edge[0]].add(edge[1])

        if edge[1] not in unweighted.keys():
            unweighted[edge[1]] = {edge[0]}
        else:
            unweighted[edge[1]].add(edge[0])
    print(unweighted)
    seen = set()
    queue = [start]
    if start not in unweighted:
        return queue
    else:
        while queue:
            node = queue.pop(0)
            if node in seen:
                pass
            else:
                seen.add(node)
                if node == end:
                    break
                for connection_node in sorted(unweighted[node]):
                    queue.append(connection_node)
        return seen


def bfs_without_memory(nodes, probability, start, end):
    """
    Function to implement breath-first search without memoryzing (using a queue) the nodes already visited.

    Using NetworkX to generate a random unweighted graph with nodes and probability of forming edges.
    Arguments:
    nodes: number of nodes in the random graph. Should be >10 (e.g. 20)
    probability: probability to generate edges between nodes (e.g. 0.2)
    start: starting node for breath-first search (e.g. 2)
    end: ending node for breath-first search (e.g. 10)
    Returns:
    boolean: True if the path from node {start} to node {end} exists else False
    """
    graph = nx.gnp_random_graph(nodes, probability)
    unweighted = {}
    for edge in sorted(graph.edges()):
        if edge[0] not in unweighted.keys():
            unweighted[edge[0]] = {edge[1]}
        else:
            unweighted[edge[0]].add(edge[1])

        if edge[1] not in unweighted.keys():
            unweighted[edge[1]] = {edge[0]}
        else:
            unweighted[edge[1]].add(edge[0])
    queue = [start]
    if start not in unweighted:
        return False
    else:
        counter = 0
        while queue and counter < 1000:
            node = queue.pop(0)
            queue.append(node)
            if node == end:
                return True
            for connection_node in sorted(unweighted[node]):
                queue.append(connection_node)
            counter += 1
        return False


if __name__ == '__main__':
    print(bfs_memory(20, 0.2, 0, 18))
