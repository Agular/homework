"""
A program which tests different regex searches.

Good practice material.
@author: agular.
"""
__author__ = 'Luga'
import re


def ex1_solution(input_string):
    """Check if the given string is binary."""
    if re.search("^[01]+$", input_string):
        return True
    return False


def ex2_solution(input_string):
    """Check if the given string in binary is even."""
    if re.search("[01]*0$", input_string):
        return True
    return False


def ex3_solution(input_string):
    """Check if the length of given string is even."""
    if re.search("^((0|1)(0|1))*$", input_string):
        return True
    return False


def ex4_solution(input_string):
    """Check if the string has pattern 0110 or 1001."""
    if re.search("(0110)|(1001)", input_string):
        return True
    return False


def ex5_solution(input_string):
    """Check if the string has pattern 0110 and 1001."""
    if re.search("1001[01]*0110|0110[01]*1001|100110|011001", input_string):
        return True
    return False


def ex6_solution(input_string):
    """Check if the string has kastiauto ratas or kasti auto ratas or kasti-auto ratas."""
    if re.search("kasti[ -]?auto ratas", input_string):
        return True
    return False


def ex7_solution(input_string):
    """Check if the string 3 or 4 words."""
    if re.search("^\w+ \w+ \w+( \w+)?[\?]?$", input_string):
        return True
    return False


def ex8_solution(input_string):
    """Check if there are maximum of 2 words between words kass and koer."""
    if re.search("kass( \w+)?( \w+)? koer", input_string):
        return True
    return False


def ex9_solution(input_string):
    """Check if given string is in correct time format."""
    if re.search("^([0-9]|(1[0-9]|2[0-3])):[0-5][0-9]$", input_string):
        return True
    return False


def ex10_solution(input_string):
    """Check if given string is a correct DNA strand."""
    if re.search("ATG([ACGT]{3})+(TAA|TAG|TGA)", input_string):
        return True
    return False


def ex11_solution(input_string):
    """Check if given number is 'Murican."""
    if re.search("^(\$[0-9]|\$[1-9][0-9]{0,2})(,[0-9]{3})*(\.[0]$|\.[0-9]{2}$)?$", input_string):
        return True
    return False


def ex12_solution(input_string):
    """Check if given binary string has even number of 0's."""
    if re.search("(^(1*01*01*)*$)|^1*$", input_string):
        return True
    return False


def ex13_solution(input_string):
    """Check if each binary bit is different from it's neighbour bits(1,0,10,01,101,010,1010101010101)."""
    if re.search("^1(01)*0?$|^0(10)*1?$", input_string):
        return True
    return False


def ex14_solution(input_string):
    r"""
    Check if string has kasti[ \-]?auto ratas and the price in dollars in it.

    This exercise is a douche on dollars. Uses simplified dollar marking and does not care about format besides .00.
    Right regex: kasti[ -]?auto(\s)ratas( \w+)* (\$[0-9]|\$[1-9][0-9]{0,2})(,[0-9]{3})*(\.[0]$|\.[0-9]{2}$)?$.
    """
    if re.search('kasti[ -]?auto ratas( \w+)* (\$[1-9][0-9]*)(\.[0-9]{2})?',
                 input_string):
        return True
    return False


def ex15_solution(input_string):
    """Check if binary string is divisible by 3."""
    if re.search("^(1(01*0)*1|0)+$", input_string):
        return True
    return False


if __name__ == "__main__":
    print(ex5_solution("100100000000000110"))
    print("....")
