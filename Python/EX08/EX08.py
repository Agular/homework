"""
A program for testing the callcentre.py module.

Thanks to the teachers for the template.
"""
# !/usr/bin/env python
# coding: utf-8
import EX08_helper
import unittest


class Tests(unittest.TestCase):

    """
    This is the class for the tests.

    Testing, testing.
    """

    def setup(self):
        """
        Thi calls out the string maker from callcentre.py.

        Calling out.
        """
        call_centre_instance = EX08_helper.get_callcentre()
        return call_centre_instance

    def test_one_noun(self):
        """
        Thi test will test, if the first noun is equal to "koer !".

        As it seems, it's not.
        """
        cc = self.setup()
        create = cc.create_sentence
        res1 = create('noun !')
        self.assertEqual(res1, 'koer !')

    def test_sentence(self):
        """
        Thi test will test, if the sentence is correctly give.

        Again, it's not.
        """
        cc = self.setup()
        create = cc.create_sentence
        output = create('sentence')
        self.assertEqual(output, 'koer sööb koera .')

    def test_input_caps(self):
        """
        Test with capital letters.

        Maybe it's useless.
        """
        cc = self.setup()
        create = cc.create_sentence
        output = create('RANDOM TEXT')
        self.assertEqual(output, 'RANDOM TEXT')

    def test_all_nouns(self):
        """
        Tests, if all 5 nouns are given from the list.

        Nope.
        """
        cc = self.setup()
        create = cc.create_sentence
        output = create('noun noun noun noun noun')
        self.assertEqual(output, 'koer porgand madis kurk tomat')

    def test_loop_all_words_twice(self):
        """
        Thi test will loop all word-types twice.

        Here comes the error-wagon.
        """
        cc = self.setup()
        create = cc.create_sentence
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        self.assertEqual(output, 'porgand porgandit lööb kole koledat')

    def test_loop_all_words_seven_times(self):
        """
        Thi test will test, if the string-maker will loop in it's 5-length lists.

        It will not.
        """
        cc = self.setup()
        create = cc.create_sentence
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        output = create('noun target verb adjective targetadjective')
        self.assertEqual(output, 'porgand porgandit lööb kole koledat')


if __name__ == "__main__":
    unittest.main(verbosity=5)
