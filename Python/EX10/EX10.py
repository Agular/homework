# -*- coding:utf-8 -*-
"""
Solution for the exercise EX10: EX08 Madise l2hetamine.

@author: Ragnar Luga
"""
__author__ = "ragnar"


class CallCentre(object):

    """Create the CallCentre object."""

    def __init__(self):
        """
        Initiate the CallCentre class on an object.

        This class is focused more on checking things in lists rather using different functions.
        """
        self.sentence = 'noun verb target .'
        self.twosentences = 'sentence sentence'
        self.beautifulsentence = 'adjective noun verb targetadjective target .'
        self.sentences = [self.sentence, self.twosentences, self.beautifulsentence]
        self.noun_generator = self.generator(['koer', 'porgand', 'madis', 'kurk', 'tomat'])
        self.target_generator = self.generator(['koera', 'porgandit', 'madist', 'kurki', 'tomatit'])
        self.verbs_generator = self.generator(['sööb', 'lööb', 'jagab', 'tahab', 'ei taha'])
        self.adjectives_generator = self.generator(['ilus', 'kole', 'pahane', 'magus', 'sinu'])
        self.targetadjectives_generator = self.generator(['ilusat', 'koledat', 'pahast', 'magusat', 'sinu'])
        self.generators = [self.noun_generator, self.target_generator, self.verbs_generator, self.adjectives_generator,
                           self.targetadjectives_generator]
        self.possible_rules = ["noun", "target", "verb", "adjective", "targetadjective", "sentence", "twosentences",
                               "beautifulsentence"]
        self.simple_rules = ["noun", "target", "verb", "adjective", "targetadjective"]
        self.sentence_rules = ["sentence", "twosentences", "beautifulsentence"]

    def generator(self, words):
        """
        Create a generator of the five words given.

        Return the next word in sequence, starting over when final word is reached.
        """
        while True:
            for word in words:
                yield word

    def create_sentence(self, input):
        """
        Return silly sentences.

        It's somewhat buggy in one place, but fixed by using lists.
        """
        return_list = []
        for word in input.split(" "):
            if word not in self.possible_rules:
                return_list.append(word)
            elif word in self.simple_rules:
                return_list.append(next(self.generators[self.simple_rules.index(word)]))
            elif word in self.sentence_rules:
                return_list.append(self.create_sentence(self.sentences[self.sentence_rules.index(word)]))
        return " ".join(return_list)


if __name__ == "__main__":
    centre = CallCentre()
    print(centre.create_sentence("sentence sentence sentence"))
    print("stupid")
