"""
Created on 31 Oct 2015.

@author: Ragnar.
"""
# -*- coding:utf-8 -*-
from PIL import Image, ImageDraw
from math import log


def read_file(file):
    """
    Read in the file and creates a list of word that occur in them.

    Ignores any numbers or symbols that are not in the estonian alphabet.
    """
    words_list = []
    valid_alphabet = "abcdefghijklmnopqrsšzžtuvwõäöüxy"
    with open(file, "rt", encoding="utf-8") as file:
        for line in file:
            words = line.lower().strip().split()
            for word in words:
                new_word = ""
                for char in word:
                    if char in valid_alphabet:
                        new_word += char
                    elif char not in valid_alphabet and len(new_word) >= 1:
                        words_list.append(new_word)
                        new_word = ""
                if len(new_word) >= 1:
                    words_list.append(new_word)
    return words_list


def pair_frequency(word_list):
    """
    Create a pair frequency dictionry from a given list with n words.

    Words are not considered case sensitive for example ag and AG are considered the same.
    """
    pair_dictionary = {}
    for word in word_list:
        for n in range(2, len(word) + 1):
            if word[n - 2:n] not in pair_dictionary:
                pair_dictionary[word[n - 2:n]] = 1
            else:
                pair_dictionary[word[n - 2:n]] += 1
    return pair_dictionary


def rgb(minimum, maximum, value):
    """Choose a color for the value."""
    if minimum != maximum:
        minimum, maximum = float(minimum), float(maximum)
        ratio = 2 * (value - minimum) / (maximum - minimum)
        b = int(max(0, 255 * (1 - ratio)))
        r = int(max(0, 255 * (ratio - 1)))
        g = 255 - b - r
    else:
        r = 255
        g = 255
        b = 255
    return r, g, b


def create_heatmap(filename, pair_dictionary):
    """
    Create the heatmap for the excercise.

    By default it is black.
    """
    heatmap = Image.new("RGB", (320, 320), "black")
    draw = ImageDraw.Draw(heatmap)
    valid_alphabet = "abcdefghijklmnopqrsšzžtuvwõäöüxy"
    start_x = 0
    start_y = 0
    if pair_dictionary != {}:
        pair_dictionary = {k: log(v) if v != 0 else 0 for k, v in pair_dictionary.items()}
        for i in valid_alphabet:
            for j in valid_alphabet:
                if i + j in pair_dictionary.keys():
                    colour = rgb(min(pair_dictionary.values()), max(pair_dictionary.values()),
                                 pair_dictionary[i + j])
                    draw.rectangle((start_x, start_y, start_x + 10, start_y + 10), fill=colour, outline=colour)
                else:
                    draw.rectangle((start_x, start_y, start_x + 10, start_y + 10), fill=(0, 0, 0), outline=(0, 0, 0))
                start_x += 10
            start_x = 0
            start_y += 10
    heatmap.save(filename)


if __name__ == '__main__':
    word_list = read_file("blabla1.txt")
    pair_dict = pair_frequency(word_list)
    print(word_list)
    print(pair_dict)
    create_heatmap("heatmap.png", pair_dict)
