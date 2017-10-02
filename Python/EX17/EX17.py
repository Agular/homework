"""
A module that that will have 3 different search functions.

Linear search, binary search sorted once, binary search sorted for each searchable item.
A graphic is made after so 3 search methods could be compared.
@author: raluga
"""
import alienutils
import timeit
import matplotlib.pyplot as plt
from math import log


def linear_search(list, search_item):
    """
    Search method that scans every item from beginning till the end until it finds the searchable item.

    And sometimes when I wipe...I keep wiping and wiping and wiping and wiping and wiping.
    """
    for item in list:
        if search_item == item:
            return search_item


def binary_search_sorted(list, search_item):
    """
    A common binary search implemented in Python.

    A woman on diet: "Can I have a half of half the pie? (1/4)
    A woman on extreme diet: "Can I have a half of half of half of half the pie?" (1/16)
    """
    low_idx = 0
    high_idx = len(list) - 1
    while True:
        middle = (low_idx + high_idx) // 2
        if str(list[middle]) == str(search_item):
            return search_item
        elif str(search_item) < str(list[middle]):
            high_idx = middle - 1
        else:
            low_idx = middle + 1


def binary_search_multisorted(list, search_item):
    """
    A common binary search that sorts it's list before searching.

    All this mess, why do I have to do this?
    """
    list = sorted(list, key=lambda alien: alien.name)
    low_idx = 0
    high_idx = len(list) - 1
    while True:
        middle = (low_idx + high_idx) // 2
        if str(list[middle]) == str(search_item):
            return search_item
        elif str(search_item) < str(list[middle]):
            high_idx = middle - 1
        else:
            low_idx = middle + 1


def wrapper(func, *args, **kwargs):
    """
    A code that returns the function with it's arguments.

    Pretty useful stuff.
    """

    def wrapped():
        return func(*args, **kwargs)

    return wrapped


if __name__ == "__main__":
    gen = alienutils.AlienGenerator()
    linear_results = []
    binary_sorted_results = []
    binary_multisorted_results = []
    sizes = [10, 100, 200, 300, 400, 500]
    for idx, size in enumerate(sizes):
        aliens = gen.get_aliens(size)
        wrapped = wrapper(sorted, aliens, key=lambda alien: alien.name)
        print("Size of list:", sizes[idx])
        sum1 = 0
        sum2 = timeit.timeit(wrapped, number=1)
        sum3 = 0
        for _ in range(10000):
            search_alien = next(gen.get_search_aliens())
            wrapped1 = wrapper(linear_search, aliens, search_alien)
            wrapped2 = wrapper(binary_search_sorted, sorted(aliens, key=lambda alien: alien.name), search_alien)
            wrapped3 = wrapper(binary_search_multisorted, aliens, search_alien)
            sum1 += (timeit.timeit(wrapped1, number=1))
            sum2 += (timeit.timeit(wrapped2, number=1))
            sum3 += (timeit.timeit(wrapped3, number=1))
        linear_results.append(sum1)
        binary_sorted_results.append(sum2)
        binary_multisorted_results.append(sum3)
        print("Linear search:", linear_results[idx])
        print("Binary sorted search:", binary_sorted_results[idx])
        print("Binary multisorted search:", binary_multisorted_results[idx])
        gen.reset()
    linear_results = [log(i * 1000) for i in linear_results]
    binary_sorted_results = [log(i * 1000) for i in binary_sorted_results]
    binary_multisorted_results = [log(i * 1000) for i in binary_multisorted_results]
    plt.plot(sizes, linear_results, "r", sizes, binary_sorted_results, "g", sizes, binary_multisorted_results,
             "b")
    plt.xscale("log")
    plt.xlabel("Number of arguments in list")
    plt.ylabel("Execution time (s)")
    plt.title("Comparison of different search algorithms executed 10 000 times.")
    plt.savefig("Search_algorithms.png")
    plt.show()
