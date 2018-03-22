# -*- coding: utf-8 -*-
"""
Created on Thu Feb  8 13:58:00 2018

@author: raluga
"""

from queue import Queue

WALL = "*"
TREASURE = "D"
START = "s"

lava_map1 = [
    "      **               **      ",
    "     ***              ***      ",
    "     ***                       ",
    "            D         *****    ",
    "           ****      ********  ",
    "           ***          *******",
    " **                      ******",
    "*****             ****     *** ",
    "*****              **          ",
    "***                            ",
    "              **         ******",
    "**            ***       *******",
    "***                      ***** ",
    "   s                           ",
    "                               ",
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


def bfs_path(start, map):
    # leia start, näiteks tuple kujul (x, y)

    frontier = Queue()
    frontier.put(start)
    came_from = {}
    came_from[start] = None

    while not frontier.empty():
        current = frontier.get()
        cx, cz = current

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
    return path


def get_start_position_from_map(map):
    for rowIdx, row in enumerate(map):
        for colIdx, colValue in enumerate(row):
            if colValue is START:
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
            map[cx] = map[cx][:cz] + "+" + map[cx][cz+1:]
    return map

if __name__ == "__main__":
    start_position = get_start_position_from_map(lava_map1)
    print("Lava map 1, Start position:", start_position)
    path = bfs_path(start_position, lava_map1)
    print("Path to treasure: ", path)
    path_with_map = return_map_with_path(path, lava_map1)
    print("\n".join(path_with_map))

