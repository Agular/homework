"""
Codeclub excercise to return a input dictionary as a string

@author : raluga
"""


def dictToString(dictionary):
    """
    Take in a dictionary and turn it into a target string.
    """

    string = ";".join("%s=%s" % (key, value) for key, value in dictionary.items() if value is not None)
    return string if string[-1] == ";" else string + ";"


dict = {'test': 1, 'test2': "jou"}
print(dictToString(dict))
