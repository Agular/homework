package ee.ttu.algoritmid.dancers.dancertree;

import ee.ttu.algoritmid.dancers.Dancer;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class DancerBinarySearchTree {

    private DancerNode root;

    public DancerBinarySearchTree() {
        this.root = null;
    }

    public void insertDancer(Dancer dancer) {
        DancerNode newNode = new DancerNode(dancer);
        if (root == null) {
            root = newNode;
        } else {
            DancerNode current = root;
            DancerNode parent;
            while (true) {
                parent = current;

                // we agree, that a dancer having equal height will always be added to the left side of the tree.
                if (dancer.getHeight() < current.dancer.getHeight() || dancer.getHeight() == current.dancer.getHeight() && dancer.getGender().equals(Dancer.Gender.FEMALE)) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        newNode.parent = parent;
                        break;
                    }
                } else if (dancer.getHeight() > current.dancer.getHeight() || dancer.getHeight() == current.dancer.getHeight() && dancer.getGender().equals(Dancer.Gender.MALE)) {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        newNode.parent = parent;
                        break;
                    }
                }
            }
            balanceTreeAfterInsert(parent);
        }
    }

    public void removeDancerNode(DancerNode dancerNode) {
        boolean isLeftChild = false;
        if (dancerNode.parent != null) {
            isLeftChild = dancerNode.parent.left == dancerNode;
        }

        if (nodeHasNoChildren(dancerNode)) {
            if (dancerNode == root) {
                root = null;
            } else if (isLeftChild) {
                dancerNode.parent.left = null;
            } else {
                dancerNode.parent.right = null;
            }
        }
        // dancerNode has only right child
        else if (dancerNode.left == null) {
            if (dancerNode == root) {
                root = dancerNode.right;
                root.parent = null;
            } else if (isLeftChild) {
                dancerNode.right.parent = dancerNode.parent;
                dancerNode.parent.left = dancerNode.right;
            } else {
                dancerNode.right.parent = dancerNode.parent;
                dancerNode.parent.right = dancerNode.right;
            }
            // dancerNode has only left child
        } else if (dancerNode.right == null) {
            if (dancerNode == root) {
                root = dancerNode.left;
                root.parent = null;
            } else if (isLeftChild) {
                dancerNode.left.parent = dancerNode.parent;
                dancerNode.parent.left = dancerNode.left;
            } else {
                dancerNode.left.parent = dancerNode.parent;
                dancerNode.parent.right = dancerNode.left;
            }
        } else if (nodeHasBothChildren(dancerNode)) {
            DancerNode successor = getSuccessor(dancerNode);
            if (dancerNode == root) {
                root = successor;
                root.parent = null;
            } else if (isLeftChild) {
                successor.parent = dancerNode.parent;
                successor.parent.left = successor;
            } else {
                successor.parent = dancerNode.parent;
                successor.parent.right = successor;
            }
            successor.left = dancerNode.left;
            successor.left.parent = successor;
        }
    }

    private boolean nodeHasNoChildren(DancerNode node) {
        return node.left == null && node.right == null;
    }

    private boolean nodeHasBothChildren(DancerNode node) {
        return node.left != null && node.right != null;
    }

    public DancerNode getSuccessor(DancerNode dancerNode) {
        DancerNode current = dancerNode.right;
        DancerNode successor = current;
        while (current.left != null) {
            current = current.left;
            successor = current;
        }

        if (dancerNode.right.dancer != successor.dancer) {
            if (nodeHasNoChildren(successor)) {
                successor.parent.left = null;
            } else {
                successor.right.parent = successor.parent;
                successor.parent.left = successor.right;
            }
            successor.parent = null;
            successor.right = dancerNode.right;
            successor.right.parent = successor;
        }
        return successor;
    }

    // in ascending order by height
    public List<Dancer> getDancerList() {
        List<Dancer> dancerList = new ArrayList<>();
        getDancerList(dancerList, root);
        tidyEqualManAndWomanInDancerList(dancerList);
        return dancerList;
    }

    private void getDancerList(List<Dancer> dancerList, DancerNode node) {
        if (node != null) {
            getDancerList(dancerList, node.left);
            dancerList.add(node.dancer);
            getDancerList(dancerList, node.right);
        }
    }


    private void tidyEqualManAndWomanInDancerList(List<Dancer> dancers) {
        for (int i = 0; i < dancers.size() - 1; i++) {
            if (dancersAreEqualAndFirstDancerIsManSecondIsWoman(dancers.get(i), dancers.get(i + 1))) {
                Dancer temp = dancers.get(i);
                dancers.set(i, dancers.get(i + 1));
                dancers.set(i + 1, temp);
            }
        }
    }

    private boolean dancersAreEqualAndFirstDancerIsManSecondIsWoman(Dancer dancer1, Dancer dancer2) {
        return dancer1.getHeight() == dancer2.getHeight() &&
                dancer1.getGender().equals(Dancer.Gender.MALE) && dancer2.getGender().equals(Dancer.Gender.FEMALE);
    }

    public SimpleEntry<Dancer, Dancer> findPartnerOrAddToTree(Dancer candidate) {
        DancerNode partnerNode;
        if (candidate.getGender().equals(Dancer.Gender.MALE)) {
            partnerNode = findParterForMan(candidate);
        } else {
            partnerNode = findPartnerForWoman(candidate);
        }
        if (partnerNode == null) {
            insertDancer(candidate);
            return null;
        } else {
            removeDancerNode(partnerNode);
            if (partnerNode.dancer.getGender().equals(Dancer.Gender.MALE)) {
                return new SimpleEntry<Dancer, Dancer>(candidate, partnerNode.dancer);
            } else {
                return new SimpleEntry<Dancer, Dancer>(partnerNode.dancer, candidate);
            }
        }
    }

    private DancerNode findParterForMan(Dancer manDancer) {
        DancerNode partnerNode = null;
        DancerNode current = root;
        while (current != null) {
            if (current.dancer.getHeight() >= manDancer.getHeight()) {
                current = current.left;
            } else {
                if (dancerIsWoman(current.dancer) && current.dancer.getHeight() < manDancer.getHeight()) {
                    partnerNode = current;
                }
                current = current.right;
            }
        }
        return partnerNode;
    }

    private DancerNode findPartnerForWoman(Dancer womanDancer) {
        DancerNode partnerNode = null;
        DancerNode current = root;
        while (current != null) {
            if (current.dancer.getHeight() <= womanDancer.getHeight()) {
                current = current.right;
            } else {
                if (dancerIsMan(current.dancer) && current.dancer.getHeight() > womanDancer.getHeight()) {
                    partnerNode = current;
                }
                current = current.left;
            }
        }
        return partnerNode;
    }

    private boolean dancerIsMan(Dancer dancer) {
        return dancer.getGender().equals(Dancer.Gender.MALE);
    }

    private boolean dancerIsWoman(Dancer dancer) {
        return dancer.getGender().equals(Dancer.Gender.FEMALE);
    }


    private void balanceTreeAfterInsert(DancerNode nodeZ) {
        DancerNode nodeG;
        DancerNode nodeN;

        // Loop up to root
        for (DancerNode nodeX = nodeZ.parent; nodeX != null; nodeX = nodeZ.parent) {
            // The right subtree increases
            if (nodeZ == nodeX.right) {
                // X is right-heavy
                if (balanceFactor(nodeX) > 0) {
                    // Save parent of X around rotations
                    nodeG = nodeX.parent;
                    // Right Left Case
                    if (balanceFactor(nodeZ) < 0) {
                        // Double rotation: Right(Z) then Left(X)
                        nodeN = rotateRightLeft(nodeX, nodeZ);
                    }
                    // Right Right Case
                    else {
                        // Single rotation Left(X)
                        nodeN = rotateLeft(nodeX, nodeZ);
                    }
                }
                // No increase noted, going up
                else {
                    nodeZ = nodeX;
                    continue;
                }
                // The left subtree increases
            } else {
                // X is left-heavy.
                if (balanceFactor(nodeX) < 0) {
                    // Save parent of nodeX around rotations
                    nodeG = nodeX.parent;
                    // Left Right Case
                    if (balanceFactor(nodeZ) > 0) {
                        // Double rotation: Left(Z) then Right(nodeX)
                        nodeN = rotateLeftRight(nodeX, nodeZ);
                        // Left Left Case
                    } else {
                        // Single rotation Right(nodeX)
                        nodeN = rotateRight(nodeX, nodeZ);
                    }
                } else {
                    //No increase noted, going up
                    nodeZ = nodeX;
                    continue;
                }
            }
            // After a rotation adapt parent link:
            // nodeN is the new root of the rotated subtree
            nodeN.parent = nodeG;
            if (nodeG != null) {
                if (nodeX == nodeG.left) {
                    nodeG.left = nodeN;
                } else {
                    nodeG.right = nodeN;
                }
                break;
            } else {
                root = nodeN; // nodeN is the new root of the total tree
                break;
            }
        }
    }


    public int balanceFactor(DancerNode node) {
        return height(node.right) - height(node.left);
    }

    public int height(DancerNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    private DancerNode rotateRight(DancerNode nodeX, DancerNode nodeZ) {
        DancerNode tempNodeZRight = nodeZ.right; // Inner child of Z
        nodeX.left = tempNodeZRight;
        if (tempNodeZRight != null)
            tempNodeZRight.parent = nodeX;
        nodeZ.right = nodeX;
        nodeX.parent = nodeZ;
        return nodeZ;
    }

    private DancerNode rotateLeft(DancerNode nodeX, DancerNode nodeZ) {
        DancerNode tempNodeZLeft = nodeZ.left; // Inner child of Z
        nodeX.right = tempNodeZLeft;
        if (tempNodeZLeft != null)
            tempNodeZLeft.parent = nodeX;
        nodeZ.left = nodeX;
        nodeX.parent = nodeZ;
        return nodeZ;
    }

    private DancerNode rotateRightLeft(DancerNode nodeX, DancerNode nodeZ) {
        DancerNode nodeY = nodeZ.left; // Inner child of Z
        DancerNode tempNodeYRight = nodeY.right;
        nodeZ.left = tempNodeYRight;
        if (tempNodeYRight != null)
            tempNodeYRight.parent = nodeZ;
        nodeY.right = nodeZ;
        nodeZ.parent = nodeY;
        DancerNode tempNodeYLeft = nodeY.left;
        nodeX.right = tempNodeYLeft;
        if (tempNodeYLeft != null)
            tempNodeYLeft.parent = nodeX;
        nodeY.left = nodeX;
        nodeX.parent = nodeY;
        return nodeY;
    }

    private DancerNode rotateLeftRight(DancerNode nodeX, DancerNode nodeZ) {
        DancerNode nodeY = nodeZ.right; // Inner child of Z
        DancerNode tempNodeYRight = nodeY.left;
        nodeZ.right = tempNodeYRight;
        if (tempNodeYRight != null)
            tempNodeYRight.parent = nodeZ;
        nodeY.left = nodeZ;
        nodeZ.parent = nodeY;
        DancerNode tempNodeYLeft = nodeY.right;
        nodeX.left = tempNodeYLeft;
        if (tempNodeYLeft != null)
            tempNodeYLeft.parent = nodeX;
        nodeY.right = nodeX;
        nodeX.parent = nodeY;
        return nodeY;
    }

    private void balanceTreeAfterDelete(DancerNode nodeN) {
        //TODO
        DancerNode nodeZ;
        DancerNode nodeG;
        for (DancerNode nodeX = nodeN.parent; nodeX != null; nodeX = nodeG) { // Loop (possibly up to the root)
            nodeG = nodeX.parent; // Save parent of X around rotations
            // BalanceFactor(X) has not yet been updated!
            if (nodeN == nodeX.left) { // the left subtree decreases
                if (balanceFactor(nodeX) > 0) { // X is right-heavy
                    // ===> the temporary BalanceFactor(X) == +2
                    // ===> rebalancing is required.
                    nodeZ = nodeX.right; // Sibling of N (higher by 2)
                    if (balanceFactor(nodeZ) < 0) {                     // Right Left Case     (see figure 5)
                        nodeN = rotateRightLeft(nodeX, nodeZ);
                    } // Double rotation: Right(Z) then Left(X)
                    else {                           // Right Right Case    (see figure 4)
                        nodeN = rotateLeft(nodeX, nodeZ);
                    }     // Single rotation Left(X)
                    // After rotation adapt parent link
                } else {

                    nodeN = nodeX;
                    continue;
                }
            } else { // (N == right_child(X)): The right subtree decreases
                if (balanceFactor(nodeX) < 0) { // X is left-heavy
                    // ===> the temporary BalanceFactor(X) == –2
                    // ===> rebalancing is required.
                    nodeZ = nodeX.left; // Sibling of N (higher by 2)
                    if (balanceFactor(nodeZ) > 0) {   // Left Right Case
                        nodeN = rotateLeftRight(nodeX, nodeZ);
                    } // Double rotation: Left(Z) then Right(X)
                    else {                       // Left Left Case
                        nodeN = rotateRight(nodeX, nodeZ);
                    }   // Single rotation Right(X)
                    // After rotation adapt parent link
                } else {
                    nodeN = nodeX;
                    continue;
                }
            }     // After a rotation adapt parent link:
            // N is the new root of the rotated subtree
            nodeN.parent = nodeG;
            if (nodeG != null) {
                if (nodeX == nodeG.left)
                    nodeG.left = nodeN;
                else
                    nodeG.right = nodeN;
                if (balanceFactor(nodeZ) == 0) {
                    break;
                } else {
                    // N is the new root of the total tree
                    root = nodeN;
                }
            }
        }

    }


}
