from sklearn.neural_network import MLPClassifier
import os
import gzip
import numpy as np
from glob import glob
from scipy.misc import imread
from sklearn.externals import joblib


def load_mnist(path, kind='train'):

    """Load MNIST data from `path`"""
    labels_path = os.path.join(path,
                               '%s-labels-idx1-ubyte.gz'
                               % kind)
    images_path = os.path.join(path,
                               '%s-images-idx3-ubyte.gz'
                               % kind)

    with gzip.open(labels_path, 'rb') as lbpath:
        labels = np.frombuffer(lbpath.read(), dtype=np.uint8,
                               offset=8)

    with gzip.open(images_path, 'rb') as imgpath:
        images = np.frombuffer(imgpath.read(), dtype=np.uint8,
                               offset=16).reshape(len(labels), 784)

    return images, labels


def neuron_network(train = True):
    labels = ["T-shirt", "Trouser", "Pullover", "Dress", "Coat", "Sandal",
              "Shirt", "Sneaker", "Bag", "Ankle boot"]
    
    
    if train:
        x_train, y_train = load_mnist("")
        x_test, y_test = load_mnist("", "t10k")
        print("Loaded data")
    
        clf = MLPClassifier(max_iter=200, hidden_layer_sizes=(100,100, 100), random_state=1,alpha=0.0001,verbose=True,shuffle=True)
        x_train = x_train / 255.0
        x_test = x_test / 255.0
        print("Normalized data")
    
        #x_small, y_small = zip(*random.sample(list(zip(x_train, y_train)), 30000))
        #clf.fit(x_small, y_small)
    
        clf.fit(x_train, y_train)
        print(clf.score(x_test, y_test))
        joblib.dump(clf, 'nerve.pkl') 
    else:
        clf = joblib.load('nerve.pkl') 

        
        
    print("Testing")
    for file in glob("pictures/*.bmp"):
        bla = imread(file, mode='L')
        X = np.reshape(bla, (1,28*28)) / 255.0
        print("MLP labeled " + file + " as " + labels[int(clf.predict(X)[0])])

    

if __name__ == "__main__":
    neuron_network()