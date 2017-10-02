"""
A binary tree implementation in Python.

@author:raluga
"""


class Node:

    """
    Creates a node with left and right values and holds the value of the word given.

    Thi shall be born as "name" with no brothers or sisters. Until you get some.
    """

    def __init__(self, name):
        """
        Initiate the Node class.

        Create the decoration for the tree.
        """
        self.name = name
        self.count = 1
        self.left = None
        self.right = None


class Tree:

    """
    Binary tree class which has add, find and print ordered functions.

    Hohohoho. Oh wait, 0101010101010101.
    """

    def __init__(self):
        """
        Initiate the Tree class.

        And the tree has been created.
        """
        self.nodes = []
        self.root = None

    def add(self, word, count=None):
        """
        Add a node to the binary tree. Increases the word count if the word already exists.

        And the tree grows more beautiful with each node.
        """
        if self.root is None:
            self.root = Node(word)
            self.nodes.append(self.root)
        elif count is not None:
            self.rec_add(word, self.root, count)
        else:
            self.rec_add(word, self.root)

    def rec_add(self, word, node, count=None):
        """
        Recursive method to add the node to the binary tree.

        Everything you own is in the box to the left.
        """
        if word == node.name:
            node.count += 1
        elif word < node.name:
            if node.left is None:
                node.left = Node(word)
                if count:
                    node.left.count = count
                self.nodes.append(node.left)
            else:
                return self.rec_add(word, node.left, count)
        else:
            if node.right is None:
                node.right = Node(word)
                if count:
                    node.right.count = count
                self.nodes.append(node.right)
            else:
                return self.rec_add(word, node.right, count)

    def find(self, word):
        """
        Find the given node in the tree.

        One, two, three, here I come.
        """
        if self.root is None:
            return None
        else:
            return self.rec_find(word, self.root)

    def rec_find(self, word, node, layer=None):
        """
        Recursive find method, return given node name,count and depth on found.

        Return None if node does not exist in the tree or the given word is None.
        """
        if layer is None:
            layer = 0
        if word is None:
            return None
        elif node is None:
            return None
        elif node.name == word:
            return node.name, node.count, layer
        elif word < node.name:
            layer += 1
            return self.rec_find(word, node.left, layer)
        elif word > node.name:
            layer += 1
            return self.rec_find(word, node.right, layer)

    def print_ordered(self):
        """
        Print the nodes in the tree in alphabetical order.

        Sing a simple melody.
        """
        node_list = [[node.name, node.count] for node in self.nodes]
        node_list.sort()
        for node in node_list:
            print(node[0], node[1])


def get_balanced_tree(tree): ####
    balanced_tree = get_tree()
    nodes_list = [[node.name, node.count] for node in tree.nodes]
    nodes_list.sort()
    center_idx = len(nodes_list) // 2
    balanced_tree.add(nodes_list[center_idx][0], nodes_list[center_idx][1])
    left_list = nodes_list[0:center_idx]
    right_list = nodes_list[center_idx + 1:]
    while left_list:
        idx = len(left_list) // 2
        balanced_tree.add(left_list[idx][0], left_list[idx][1])
        left_list.pop(idx)
    while right_list:
        idx = len(right_list) // 2
        balanced_tree.add(right_list[idx][0], right_list[idx][1])
        right_list.pop(idx)
    return balanced_tree

# def get_balanced_tree(tree):
#     """
#     Return a balanced version of the tree.
#
#     Currently broken.
#     """
#     return tree


def get_tree():
    """
    Return the Tree instance for the testers.

    Here it is.
    """
    return Tree()


if __name__ == "__main__":
    tree = get_tree()
    tree.add("a")
    print(tree.find("a"))
    tree.add("a")
    tree.add("a")
    tree.add("b")
    tree.add("b")
    tree.add("c")
    balanced_tree = get_balanced_tree(tree)
    print(balanced_tree.find("a"))
    print(balanced_tree.find("b"))
    print(balanced_tree.find("c"))
    print(balanced_tree.ret_right("b"))
    # lisa count add ja rec_add funktsioonile
