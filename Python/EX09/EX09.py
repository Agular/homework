"""
This is a program.

A docstring.
"""

from MockService import MockService
import random


class MonteCarloSimulation(object):

    """
    This is a a template for a Monte Carlo simulation.

    A docstring.
    """

    def __init__(self, service):
        """
        Constructor for the simulation.

        Arguments:
            Input service
        Returns:
            Output service
        """
        self.service = service
        if self.service.get_area() != 0:
            self.width = self.service.get_width() - 1
            self.height = self.service.get_height()
            self.treasure_area = self.width * self.height
            self.counter = self.service.tries_left()
            self.total_tries = self.counter
            self.final_dictionary = {}
        else:
            self.final_dictionary = {}

    def get_area(self):
        """
        You need to implement yo code hear.

        Bla Monte Carlo simulation here taking into consideration the file input.
        A docstring.
        """
        if self.service.get_area() == 0:
            return self.final_dictionary
        while self.counter != 0:
            rand_height = random.randint(0, self.height - 1)
            rand_width = random.randint(0, self.width - 1)
            random_point = self.service.info(rand_height, rand_width)
            if random_point in self.final_dictionary:
                self.final_dictionary[random_point] += 1
            else:
                self.final_dictionary[random_point] = 1
            self.counter -= 1
        for key in self.final_dictionary.keys():
            self.final_dictionary[key] = self.treasure_area * (self.final_dictionary[key] / self.total_tries)
        return self.final_dictionary


if __name__ == "__main__":
    service1 = MockService('circle_10.txt')
    service2 = MockService('circle_100.txt')
    service3 = MockService('circle_20.txt')
    service4 = MockService('circle_200.txt')
    service5 = MockService('squares.txt')
    service6 = MockService('circle_0.txt')

    sim1 = MonteCarloSimulation(service1)
    sim2 = MonteCarloSimulation(service2)
    sim3 = MonteCarloSimulation(service3)
    sim4 = MonteCarloSimulation(service4)
    sim5 = MonteCarloSimulation(service5)
    sim6 = MonteCarloSimulation(service6)

    print(sim1.get_area())
    print(sim2.get_area())
    print(sim3.get_area())
    print(sim4.get_area())
    print(sim5.get_area())
    print(sim6.get_area())
