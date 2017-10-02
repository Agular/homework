"""
@author: siduun
@author: raluga
"""
while True:
    line = input()
    line = line.rstrip()
    if line == "#":
        break

    graphData, M, T, k = line.split()
    k = int(k)

    graphData = graphData.strip('.')

    graph = {}
    for node in graphData.split(";"):
        for i in range(2, len(node)):
            if node[0] in graph:
                graph[node[0]].append(node[i])
            else:
                graph[node[0]] = [node[i]]

    candles = []
    trapped = False
    count = 0
    while not trapped:
        if (M not in graph):
            break
        for nextPos in graph[M]:
            if nextPos not in candles and nextPos != T:
                T = M
                M = nextPos
                count += 1
                if count == k:
                    candles.append(T)
                    count = 0
                break
        else:
            trapped = True


    if not candles:
        print("/" + M)
    else:
        print(" ".join(candles) + " /" + M)

