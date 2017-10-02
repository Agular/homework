"""
Utilities for EX17.
"""
__author__ = 'Uku'
from random import choice, seed, randint, getstate, setstate
from copy import deepcopy
from string import ascii_uppercase, digits


class Alien:
    """Not a human, dawg"""
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return "Alien name: {name}, age: {age}".format(
            name=self.name,
            age=self.age
        )


class AlienGenerator:
    """Generates aliens yo"""
    STARTING_SEED = 'dis seed tho'
    NAME_LENGTH = 30
    MIN_AGE = 18
    MAX_AGE = 200
    SPACE_AMOUNT = 5

    def __init__(self):
        seed(self.STARTING_SEED)
        self.aliens = []
        self.start_state = getstate()

    @classmethod
    def __get_name(cls):
        """Get a random name, brah

        returns:
            A random name
        """
        length = cls.NAME_LENGTH
        spaces = ' ' * cls.SPACE_AMOUNT
        symbols = ascii_uppercase + digits + spaces
        return ''.join(choice(symbols) for _ in range(length)).title()

    def get_aliens(self, size):
        """Get a bunch o' aliens, son

        args:
            size (int): amount of aliens dawg
        returns:
            lots o' aliens
        """
        if len(self.aliens) != size:
            self.aliens = [
                Alien(
                    self.__get_name(),
                    randint(self.MIN_AGE, self.MAX_AGE)
                ) for _ in range(size)]
        setstate(self.start_state)
        return deepcopy(self.aliens)

    def get_search_aliens(self):
        """Generates aliens to search for, buddy"""
        while True:
            yield choice(self.aliens)

    def reset(self):
        """Reset this generator's state, bro"""
        setstate(self.start_state)