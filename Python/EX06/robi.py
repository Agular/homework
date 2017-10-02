"""
Decides where the robot goes and gives a half-random free.

This robot can go in -45 degrees, 0 degrees, 45 degrees.
"""


def decide(sensor_data, current_state):
    """
    Decide in which direction should robot move using sensor_data.

    Use resources as efficiently as possible.
    Arguments:
        List of blocking objects in specific direction.
        0  - Free
        -1  - Object
        sensor_data - [n, ne, e, se, s, sw, w, nw]
    Returns:
    One of the possible directions.
    """
    states = ["N", "NE", "E", "SE", "S", "SW", "W", "NW"]
    moves = [-1, 0, 1]
    if 0 not in sensor_data:
        return None
    elif sensor_data[states.index(current_state)] == 0:
        return 0
    else:
        for move in moves:
            if sensor_data[(states.index(current_state) + move) % 8] == 0:
                return move
