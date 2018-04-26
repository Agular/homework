from hmmlearn import hmm
import numpy as np


# S M S S L L M M L M S M M S S L S L L L L L L L L L L S L S
# S is 0, M is 1, L is 2
# [0],[1],[0],[0],[2],[2],[1],[1],[2],[1],[0],[1],[1],[0],[0],[2],[0],[2],[2],[2],[2],[2],[2],[2],[2],[2],[2],[0],[2],[0]

def convert(list):
    dict = {0:"H", 1:"C"}
    return [dict[x] for x in list]

def markov():
    year_circles = [[0], [1], [0], [0], [2], [2], [1], [1], [2], [1], [0], [1], [1], [0], [0], [2], [0], [2], [2], [2],
                    [2], [2], [2], [2], [2], [2], [2], [0], [2], [0]]

    initial = np.array([0.5, 0.5])  # initial state probability
    transition = np.array([[0.7, 0.3], [0.3, 0.7]])  # state transition
    sensor = np.array([[0.1, 0.4, 0.5], [0.7, 0.2, 0.1]])  # observation, given state

    hm1 = hmm.MultinomialHMM(n_components=2)
    hm1.startprob_ = initial
    hm1.transmat_ = transition
    hm1.emissionprob_ = sensor

    states = hm1.predict(year_circles)
    print("Most likely hidden states (known model)", " ".join(convert(states)), "\n")

    hm2 = hmm.MultinomialHMM(n_components=2, params="st", init_params="st")
    hm2.emissionprob_ = sensor

    hm2.fit(year_circles)
    states = hm2.predict(year_circles)
    print("Most likely hidden states (partially known)", " ".join(convert(states)))
    print("Learned transition probabilities")
    print(hm2.transmat_, "\n")


    hm3 = hmm.MultinomialHMM(n_components=2, params="st", init_params="ste")
    hm3.fit(year_circles)
    states = hm3.predict(year_circles)
    print("Most likely hidden states (only year circles)", " ".join(convert(states)))
    print("Learned transition probabilities")
    print(hm3.transmat_, "\n")





if __name__ == "__main__":
    markov()
