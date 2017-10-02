"""
A module to show Estonia's demographic parameters on a graph.

The data is from 1965-2015 and there are 4 data lists:
all people, people aged 15-19,20-24 and 25-29.
Just dance and be okay.
"""
__author__ = 'raluga'
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt


def get_series(series):
    """Tagastab argumendile „series“ vastava andmejada. Voimalikud argumendid on„Vanuseryhmad kokku“, „15-19“, „20-24“ ja „25-29“."""
    vanused_koos = [1286262, 1302870, 1314323, 1323569, 1338858, 1351640, 1368511, 1385399, 1399637, 1412265, 1424073,
                    1434630, 1444522, 1455900, 1464476, 1472190, 1482247, 1493085, 1503743, 1513747, 1523486, 1534076,
                    1546304, 1558137, 1565662, 1570599, 1567749, 1554878, 1511303, 1476952, 1448075, 1425192, 1405996,
                    1393074, 1379237, 1401250, 1392720, 1383510, 1375190, 1366250, 1358850, 1350700, 1342920, 1338440,
                    1335740, 1333290, 1329660, 1325217, 1320174, 1315819, 1313271]
    vanused_15_19 = [90942, 96471, 99428, 100886, 100942, 100679, 101321, 100832, 101466, 103274, 104452, 105265,
                     106286, 106834, 106079, 105065, 102773, 101420, 99647, 99901, 101055, 104001, 107353, 111010,
                     111696, 109719, 108511, 107641, 104774, 102019, 99958, 99129, 98966, 99564, 101406, 102000, 102040,
                     101830, 102580, 102720, 102680, 101660, 98420, 93980, 87180, 79750, 73180, 68634, 64021, 61311,
                     59842]
    vanused_20_24 = [89850, 87798, 83872, 84855, 91326, 99889, 106678, 110052, 111684, 111447, 110685, 112309, 112824,
                     113620, 115187, 114229, 113763, 113566, 113628, 112455, 111154, 108428, 105989, 103767, 104027,
                     105679, 107063, 106471, 102906, 101515, 99551, 97600, 95731, 94786, 94477, 98280, 98050, 97910,
                     97620, 98550, 99070, 98770, 98510, 99560, 100160, 100710, 100000, 96669, 92203, 85651, 78493]
    vanused_25_29 = [108387, 109351, 110860, 106961, 101350, 95046, 91201, 88156, 90898, 97991, 105741, 111772, 114409,
                     116507, 116386, 116614, 118938, 119496, 119790, 120720, 120463, 120777, 121384, 121463, 119514,
                     117298, 112872, 107553, 100241, 95906, 93270, 93026, 94143, 95350, 95133, 99710, 98780, 97650,
                     97020, 96310, 95560, 94850, 94240, 93850, 94770, 95620, 95590, 95603, 96742, 97499, 98197]
    if series == "Vanuserühmad kokku":
        return vanused_koos
    elif series == "15-19":
        return vanused_15_19
    elif series == "20-24":
        return vanused_20_24
    elif series == "25-29":
        return vanused_25_29
    else:
        return []


def save_plot(series_axis1, series_axis2, filename):
    """Salvestab etteantud andmejadadest moodustatud graafiku faili, mille nimi on ette antud argumendis „filename“."""
    aastad = [1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981,
              1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998,
              1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015]
    fig, ax1 = plt.subplots()
    ax1.plot(aastad, series_axis1, "b")
    ax1.set_xlabel("Aasta")
    ax1.set_ylabel("Arv", color="b")
    for tl in ax1.get_yticklabels():
        tl.set_color("b")

    ax2 = ax1.twinx()
    ax2.plot(aastad, series_axis2, "r")
    ax2.set_ylabel("Arv", color="r")
    for tl in ax2.get_yticklabels():
        tl.set_color("r")
    plt.savefig(filename)


if __name__ == "__main__":
    koos = get_series("Vanuserühmad kokku")
    noored = get_series("15-19")
    save_plot(koos, noored, "tere.png")
