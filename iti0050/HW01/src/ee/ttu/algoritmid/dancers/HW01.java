package ee.ttu.algoritmid.dancers;

import ee.ttu.algoritmid.dancers.dancertree.DancerBinarySearchTree;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class HW01 implements Dancers {

    private DancerBinarySearchTree binarySearchTree;

    public HW01(){
        this.binarySearchTree = new DancerBinarySearchTree();
    }

    @Override
    public SimpleEntry<Dancer, Dancer> findPartnerFor(Dancer candidate) throws IllegalArgumentException {
        if(candidate == null) throw new IllegalArgumentException();
        return binarySearchTree.findPartnerOrAddToTree(candidate);
    }

    @Override
    public List<Dancer> returnWaitingList() {
        return binarySearchTree.getDancerList();
    }
}