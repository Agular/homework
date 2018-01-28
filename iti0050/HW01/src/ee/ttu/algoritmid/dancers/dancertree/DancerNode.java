package ee.ttu.algoritmid.dancers.dancertree;

import ee.ttu.algoritmid.dancers.Dancer;

public class DancerNode {

    Dancer dancer;
    DancerNode left;
    DancerNode right;
    DancerNode parent;

    public DancerNode(Dancer dancer) {
        this.dancer = dancer;
        left = null;
        right = null;
        parent = null;
    }
}
