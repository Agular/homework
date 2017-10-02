"""
A codeclub excercise to practice datetimes.

@author: raluga
"""
import datetime


def get_age(birth_date, current_date=None):
    if current_date is None:
        current_date = datetime.datetime.utcnow()
        return (current_date - birth_date).year
    else:
        return (current_date - birth_date).year

if __name__ == "__main__":
    get_age(None, None)
