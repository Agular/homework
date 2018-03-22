# -*- coding: utf-8 -*-
"""
Created on Thu Feb  8 13:58:00 2018

@author: raluga
"""

from queue import Queue, PriorityQueue
from timeit import default_timer as timer

from math import sqrt

WALL = "*"
TREASURE = "D"
START = "s"

lava_map1 = [
    "      **               **      ",
    "     ***     D        ***      ",
    "     ***                       ",
    "                      *****    ",
    "           ****      ********  ",
    "           ***          *******",
    " **                      ******",
    "*****             ****     *** ",
    "*****              **          ",
    "***                            ",
    "              **         ******",
    "**            ***       *******",
    "***                      ***** ",
    "                               ",
    "                s              ",
]

lava_map2 = [
    "     **********************    ",
    "   *******   D    **********   ",
    "   *******                     ",
    " ****************    **********",
    "***********          ********  ",
    "            *******************",
    " ********    ******************",
    "********                   ****",
    "*****       ************       ",
    "***               *********    ",
    "*      ******      ************",
    "*****************       *******",
    "***      ****            ***** ",
    "                               ",
    "                s              "]


def greedy_path(start, end, map):
    frontier = PriorityQueue()
    frontier.put((0, start))
    came_from = {start: None}

    info = 0
    while not frontier.empty():
        _, current = frontier.get()
        cx, cz = current
        info+=1
        if map[cx][cz] is TREASURE:
            break

        for next in graph_get_neighbors(current, map):
            if next not in came_from:
                priority = manhattan_h(next, end)
                frontier.put((priority, next))
                came_from[next] = current

    path = []
    while came_from[current] != None:
        path.insert(0, came_from[current])
        current = came_from[current]
    print("greedy info", info)
    return path


def a_path(start, end, map):
    frontier = PriorityQueue()
    frontier.put((0, start))
    came_from = {start: None}
    cost_so_far = {start: 0}

    info = 0
    while not frontier.empty():
        _, current = frontier.get()
        new_cost = cost_so_far[current] + 1
        cx, cz = current

        info+=1

        if map[cx][cz] is TREASURE:
            break

        for next in graph_get_neighbors(current, map):
            if next not in cost_so_far or new_cost < cost_so_far[next]:
                cost_so_far[next] = new_cost
                priority = new_cost + manhattan_h(next, end)  ## g(n) + h(n)
                frontier.put((priority, next))
                came_from[next] = current

    path = []
    while came_from[current] != None:
        path.insert(0, came_from[current])
        current = came_from[current]
    print("a nodes: " , info)
    return path


def bfs_path(start, map):
    frontier = Queue()
    frontier.put(start)
    came_from = {start: None}

    info = 0
    while not frontier.empty():
        current = frontier.get()
        cx, cz = current
        info += 1
        if map[cx][cz] is TREASURE:
            break

        for next in graph_get_neighbors(current, map):
            if next not in came_from:
                frontier.put(next)
                came_from[next] = current

    path = []
    while came_from[current] != None:
        path.insert(0, came_from[current])
        current = came_from[current]

    print("bfs info", info)
    return path


def get_map_from_file(file_name):
    with open(file_name) as file:
        map_data = [line.strip("\n") for line in file.readlines() if len(line) > 1]
    return map_data


def get_start_position_from_map(map):
    for rowIdx, row in enumerate(map):
        for colIdx, colValue in enumerate(row):
            if colValue is START:
                return rowIdx, colIdx


def get_end_position_from_map(map):
    for rowIdx, row in enumerate(map):
        for colIdx, colValue in enumerate(row):
            if colValue is TREASURE:
                return rowIdx, colIdx


def graph_get_neighbors(current, map):
    neighbors = []
    cx, cz = current

    if valid_move(cx + 1, cz, map):
        neighbors.append((cx + 1, cz))
    if valid_move(cx - 1, cz, map):
        neighbors.append((cx - 1, cz))
    if valid_move(cx, cz + 1, map):
        neighbors.append((cx, cz + 1))
    if valid_move(cx, cz - 1, map):
        neighbors.append((cx, cz - 1))

    return neighbors


def valid_move(cx, cz, map):
    if 0 <= cx < len(map) and 0 <= cz < len(map[cx]):
        if not map[cx][cz] == "*":
            return True
        else:
            return False
    else:
        return False


def return_map_with_path(path, map):
    for coordinate in path:
        cx, cz = coordinate
        if not map[cx][cz] == TREASURE and not map[cx][cz] == START:
            map[cx] = map[cx][:cz] + "+" + map[cx][cz + 1:]
    return map


def manhattan_h(current, goal):
    cx, cz = current
    gx, gz = goal
    return abs(gx - cx) + abs(gz - cz)


def pythagoras_h(current, goal):
    cx, cz = current
    gx, gz = goal
    return sqrt((gx-cx) ** 2 + (gz - cz) ** 2)


if __name__ == "__main__":
    maps = ["300x300.txt", "600x600.txt", "900x900.txt", "900x900Empty.txt"]
    for map_name in maps:
        ascii_map = get_map_from_file(map_name)
        start_position = get_start_position_from_map(ascii_map)
        end_position = get_end_position_from_map(ascii_map)
        print("Map name:", map_name)
        print()
        print("Start position:", start_position)
        print("End position:", end_position)

        bfs_start = timer()
        bfs = bfs_path(start_position, ascii_map)
        bfs_end = timer()

        greedy_start = timer()
        greedy = greedy_path(start_position, end_position, ascii_map)
        greedy_end = timer()

        a_start = timer()
        a = a_path(start_position, end_position, ascii_map)
        a_end = timer()

        bfs_time = bfs_end - bfs_start
        greedy_time = greedy_end - greedy_start
        a_time = a_end - a_start

        print("BFS length:", len(bfs))
        print("GREEDY length:", len(greedy))
        print("A* length:", len(a))
        print()
        print("BFS time:", bfs_time)
        print("GREEDY time:", greedy_time)
        print("A* time:", a_time)
        print()


    # path_with_map = return_map_with_path(path, map)
    # print("\n".join(path_with_map))
