from sklearn import tree
from sklearn.model_selection import cross_val_score
from sklearn.feature_selection import VarianceThreshold
from sklearn.feature_selection import SelectKBest
from sklearn.model_selection import StratifiedKFold
from sklearn.feature_selection import SelectFromModel
from sklearn.feature_selection import RFECV
import pandas
import numpy as np
import graphviz


def decisionTree(filename):
    # read training data
    training_data = pandas.read_csv("IFI6057_hw2017_data.txt")
    # print(training_data)
    classes = np.array(training_data["Result"])
    attributes = np.array(training_data.drop("Result", axis=1))

        # train decisionTree
    clf = tree.DecisionTreeClassifier()
    clf = clf.fit(attributes, classes)

    # predict a class for random dataSet
    # print(clf.predict([[1, 0, 1, 1, 1, 0, -1, -1, 0]]))

    # tree in a graph
    #dot_data = tree.export_graphviz(clf, out_file=None)
    #graph = graphviz.Source(dot_data)
    #graph.render("Phishing")


    # cross-validation
    scores = cross_val_score(clf, attributes, classes)
    print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))


    # Try to improve accuracy
    print("\nModifying to improve accuracy, try 1")

    # remove features that have little variance in data
    p = 0.8
    sel = VarianceThreshold(threshold=(p * (1 - p)))
    print("Number of features initally in a set: " + str(attributes.shape[1]))
    attributes2 = sel.fit_transform(attributes)
    print("Number of features after pruning: " + str(attributes2.shape[1]))


    # train decisionTree, changing depth and min leaves
    clf = tree.DecisionTreeClassifier(max_depth=9, min_samples_leaf=6)
    clf = clf.fit(attributes2, classes)

    # cross-validation
    scores = cross_val_score(clf, attributes2, classes)
    print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))




    print("\nModifying to improve accuracy, try 2")
    clf = tree.DecisionTreeClassifier(max_depth=9)
    rfecv = RFECV(estimator=clf, step=1, scoring='accuracy', cv=StratifiedKFold(10))
    rfecv = rfecv.fit(attributes, classes)

    print("Optimal number of features : %d" % rfecv.n_features_)
    #cross-validation
    scores = cross_val_score(rfecv, attributes, classes,  cv=StratifiedKFold(10))
    print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))



    print("\nModifying to improve accuracy, try 3")
    clf = tree.DecisionTreeClassifier(max_depth=7, min_samples_leaf=3)

    clf = clf.fit(attributes, classes)
    model = SelectFromModel(clf, prefit=True)
    X_new = model.transform(attributes)

    print("Optimal number of features : " + str(X_new.shape))

    #cross-validation
    scores = cross_val_score(clf, attributes, classes)
    print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))


if __name__ == "__main__":
    decisionTree("IFI6057_hw2017_data.txt")
