"""
EX07 - Treasure hunt (aka Terminator of students).

Simulates a robot in a world, where it has to find a treasure, by avoiding obstacles and other robots.
Thanks to @author gert for the template.
Reference for future me in 1 year: make this better, you can do it now.
"""

import random

import simulator


class Robot(simulator.Agent):

    """
    Create the Robot.

    Nothing more to describe.
    """

    def __init__(self, world, x, y, direction):
        """Create the Robot.

        Nothing more to describe
        """
        simulator.Agent.__init__(self, world, x, y, direction)

    def decide(self):
        """
        Decide, what is the amount of turn needed to continue.

        Analyzes the situation with different conditions and functions.
        More complex movement algorithm should be developed.
        """
        if self.treasure_in_turn_range1() is not None:
            print("test1")
            self.turn_and_drive_straight(self.treasure_in_turn_range1())
        elif self.treasure_in_turn_range2() is not None:
            print("test2")
            self.turn_and_drive_straight(self.treasure_in_turn_range2())
        elif self.treasure_in_range1() is not None:
            print("test3")
            self.turn_and_drive_straight(self.treasure_in_range_decide_optimal_turn(self.treasure_in_range1()))
        elif self.treasure_in_range2() is not None:
            print("test4")
            self.turn_and_drive_straight(self.treasure_in_range_decide_optimal_turn(self.treasure_in_range2()))
        else:
            print("random")
            self.turn_and_drive_straight(self.make_random_free_move())

    def treasure_in_turn_range1(self):
        """
        Turn towards the treasure when it's in range 1 and can be achieved in one turn.

        And the robot shall gain the treasure.
        """
        for i in range(-2, 3):
            if len(self.detect((self.compass() + i) % 8)) == 1 and -3 in self.detect((self.compass() + i) % 8):
                return i

    def treasure_in_turn_range2(self):
        """
        Turn towards the treasure when it's in range 2 and there are no obstacles.

        The treasure can be hidden in detect(i), but can be seen in detect(i-1), (or +1).
        """
        for i in range(-2, 3):
            if len(self.detect((self.compass() + i) % 8)) == 3 and -3 in self.detect((self.compass() + i) % 8):
                return i

    def treasure_in_range1(self):
        """
        Detect the treasure in range 1 which cannot be reached with 1 turn.

        And the robot is just physically incapable of reaching his goal.
        """
        for i in range(self.compass() + 3, self.compass() + 6):
            if len(self.detect(i % 8)) == 1 and -3 in self.detect(i % 8):
                return i % 8

    def treasure_in_range2(self):
        """
        Detect the treasure in range 2 which cannot be reached with 1 turn.

        Same as in treasure_in_turn_range2.
        """
        for i in range(self.compass() + 3, self.compass() + 6):
            if len(self.detect(i % 8)) == 3 and -3 in self.detect(i % 8):
                return i % 8

    def straight_path_is_safe_range1(self, direction):
        """
        Check, if the direction is clear of obstacles in a range of 1.

        Basically a self.detect() function, but returns True if there are no obstacles.
        """
        if len(self.detect(direction)) == 1 and self.detect(direction)[0] not in (
                -2, -1, 0, 1, 2, 3, 4, 5, 6, 7) or len(self.detect(direction)) == 3:
            return True
        return False

    def treasure_in_range_decide_optimal_turn(self, treasure_direction):
        """
        Decide the best way to approach the treasure if it is not reachable in 1 turn.

        And he shall take up a bigger journey to find his treasure.
        """
        if (self.compass() + 3) % 8 == treasure_direction:
            for i in range(2, 0, -1):
                if self.straight_path_is_safe_range1((self.compass() + i) % 8):
                    return i
        elif (self.compass() - 3) % 8 == treasure_direction:
            for i in range(-2, 0):
                if self.straight_path_is_safe_range1((self.compass() + i) % 8):
                    return i
        elif (self.compass() + 4) % 8 == treasure_direction:
            pass  # don't know yet, what to do

    def make_random_free_move(self):
        """
        Give a random free turn for the robot if there is no treasures nearby.

        This is a stage 1 moving algorithm, should be updated to be more effective.
        And he shall step blindly in the direction he knows shows no danger.
        """
        moves = [-2, -1, 0, 1, 2]
        free_moves = list()
        for move in moves:
            direction = (self.compass() + move) % 8
            if len(self.detect(direction)) == 3 and self.turn_into_corner(direction) is not True:
                free_moves.append(move)
        if len(free_moves) > 0:
            return random.choice(free_moves)
        else:
            return 0  # crash

    def turn_into_corner(self, direction):  # MUUDA
        """
        Check if the given direction is a corner in range 2.

        :param direction:
        :return:
        True, if there is a corner.
        False, if there is none.
        """
        """
        :param direction:
        :return:
        """
        if direction in (1, 3, 5, 7) and len(self.detect(direction)) == 3 and set(self.detect(direction)) < {-2, -1, 0,
                                                                                                             1, 2, 3,
                                                                                                             4, 5, 6,
                                                                                                             7}:
            return True
        return False


if __name__ == "__main__":
    world = simulator.World(width=10, height=10, sleep_time=0.1, reliability=1, treasure=(4, 0),
                            obstacles=[(3, 0), (3, 1), (3, 2), (5, 0), (5, 1), (5, 2), (6, 8), (5, 8), (6, 7)])
    robots = list()
    robots.append(Robot(world, 8, 8, 3))  # add more robots here if you like
    for _ in range(10):  # Simulate 50 ticks
        world.print_state()
        for robot in robots:
            robot.decide()
        world.print_state()
        world.tick()
