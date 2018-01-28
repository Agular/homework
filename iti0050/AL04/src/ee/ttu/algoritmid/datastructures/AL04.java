package ee.ttu.algoritmid.datastructures;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

/**
 * AL04A lab task.
 */
public class AL04 {

    /**
     * The number of runs to measure average time.
     */
    public static final int NUMBER_OF_REPEATS = 3;

    /**
     * Exception penalty for violating data structures (to force them not to be not feasible).
     */
    public static final int EXCEPTION_PENALTY = 99999999;


    public class ComparisonListImpl implements ComparisonList {

        ArrayList<Integer> arrayList = new ArrayList<>();

        @Override
        public List<Integer> getData() {
            return arrayList;
        }

        @Override
        public void clear() {
            arrayList.clear();
        }

        @Override
        public void insert(Integer value) throws Exception {
            arrayList.add(value);
        }

        @Override
        public boolean search(Integer value) {
            for (int arrayValue : arrayList) {
                if (arrayValue == value) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer delete(Integer value) {
            if (arrayList.remove(value)) {
                return value;
            }
            return null;
        }

        @Override
        public Integer deleteMin() {
            int idx = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < arrayList.size(); i++) {
                int value = arrayList.get(i);
                if (value < min) {
                    min = value;
                    idx = i;
                }
            }
            return arrayList.remove(idx);
        }
    }

    public class ComparisonPriorityQueueImpl implements ComparisonPriorityQueue {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        @Override
        public PriorityQueue<Integer> getData() {
            return queue;
        }

        @Override
        public void clear() {
            queue.clear();
        }

        @Override
        public void insert(Integer value) throws Exception {
            queue.add(value);
        }

        @Override
        public boolean search(Integer value) {
            return queue.contains(value);
        }

        @Override
        public Integer delete(Integer value) {
            if (queue.remove(value)) {
                return value;
            }
            return null;
        }

        @Override
        public Integer deleteMin() {
            return queue.poll();
        }
    }

    public class ComparisonTreeSetImpl implements ComparisonTreeSet {

        TreeSet<Integer> treeSet = new TreeSet<>();

        @Override
        public TreeSet<Integer> getData() {
            return treeSet;
        }

        @Override
        public void clear() {
            treeSet.clear();
        }

        @Override
        public void insert(Integer value) throws Exception {
            treeSet.add(value);
        }

        @Override
        public boolean search(Integer value) {
            return treeSet.contains(value);
        }

        @Override
        public Integer delete(Integer value) {
            if (treeSet.remove(value)) {
                return value;
            }
            return null;
        }

        @Override
        public Integer deleteMin() {
            return treeSet.pollFirst();
        }
    }

    public class ComparisonHashtableImpl implements ComparisonHashtable {

        Hashtable<Integer, Integer> hashtable = new Hashtable<>();

        @Override
        public Hashtable<Integer, Integer> getData() {
            return hashtable;
        }

        @Override
        public void clear() {
            hashtable.clear();
        }

        @Override
        public void insert(Integer value) throws Exception {
            hashtable.put(value, value);
        }

        @Override
        public boolean search(Integer value) {
            return hashtable.contains(value);
        }

        @Override
        public Integer delete(Integer value) {
            return hashtable.remove(value);
        }

        @Override
        public Integer deleteMin() {
            int key = -1;
            int min = Integer.MAX_VALUE;
            for (Integer hashtableKey : hashtable.keySet()) {
                if (hashtableKey < min) {
                    key = hashtableKey;
                    min = hashtableKey;
                }
            }
            return hashtable.remove(key);

        }
        // TODO: Implement the interface.
    }

    /**
     * Create a good example to demonstrate under which circumstances PriorityQueue is best.
     * Choose the appropriate parameters by experimentation and thinking logically.
     * Choose parameters reasonably (i.e., no point having no inserts etc.)
     *
     * @param insertOrder insertion order
     * @param searchOrder search order
     * @param deleteOrder delete order
     * @param deleteMin   find and delete minimum element the number of elements times
     * @return the list with data structure names and measurement times
     * (e.g., ["ComparisonListArrayImpl", 0.3], ["ComparisonHashtableImpl", 0.4] etc.)
     */
    public final List<Entry<String, Double>> priorityQueueIsBestWhen(List<Integer> insertOrder, List<Integer> searchOrder,
                                                                     List<Integer> deleteOrder, List<Boolean> deleteMin) {
        insertOrder.clear();
        searchOrder.clear();
        deleteOrder.clear();
        deleteMin.clear();

        int elements = 6000;

        // Insert 5000 elments (elements 0 - 5000).
        for (int i = 0; i < elements; i++) {
            insertOrder.add(i);
        }
        // I don't want to delete anything specific, so I can leave the deleteOrder empty.
        // Delete the minimal element 700 times.
        for (int i = 0; i < 2000; i++) {
            deleteMin.add(true);
        }
        return dataStructureComparison(insertOrder, searchOrder, deleteOrder, deleteMin, NUMBER_OF_REPEATS);
    }

    /**
     * Create a good example to demonstrate under which circumstances ArrayList is not the worst (or best!).
     * Choose the appropriate parameters by experimentation and thinking logically.
     * Choose parameters reasonably (i.e., no point having no inserts etc.)
     *
     * @param insertOrder insertion order
     * @param searchOrder search order
     * @param deleteOrder delete order
     * @param deleteMin   find and delete minimum element the number of elements times
     * @return the list with data structure names and measurement times (e.g., ["List", 0.3], ["Hashtable", 0.4] etc.)
     */
    public final List<Entry<String, Double>> arrayListIsNotTheWorstWhen(List<Integer> insertOrder, List<Integer> searchOrder,
                                                                        List<Integer> deleteOrder, List<Boolean> deleteMin) {
        insertOrder.clear();
        searchOrder.clear();
        deleteOrder.clear();
        deleteMin.clear();

        int elements = 6000;

        // Insert 5000 eleents
        for (int i = 0; i < elements; i++) {
            insertOrder.add(i);
        }

        for (int i = 500; i < 1000; i += 10) {
            searchOrder.add(i);
        }

        return dataStructureComparison(insertOrder, searchOrder, deleteOrder, deleteMin, NUMBER_OF_REPEATS);
    }

    /**
     * Create a good example to demonstrate under which circumstances HashTable is the best data structure to use.
     * Choose the appropriate parameters by experimentation and thinking logically.
     * Choose parameters reasonably (i.e., no point having no inserts etc.)
     *
     * @param insertOrder insertion order
     * @param searchOrder search order
     * @param deleteOrder delete order
     * @param deleteMin   find and delete minimum element the number of elements times
     * @return the list with data structure names and measurement times (e.g., ["List", 0.3], ["Hashtable", 0.4] etc.)
     */
    public final List<Entry<String, Double>> hashtableIsTheBestWhen(List<Integer> insertOrder, List<Integer> searchOrder,
                                                                    List<Integer> deleteOrder, List<Boolean> deleteMin) {
        insertOrder.clear();
        searchOrder.clear();
        deleteOrder.clear();
        deleteMin.clear();

        int elements = 5000;

        //HashTable should be the best, when we want to get an element from the list. (or search) - O(1)

        // Insert 5000 elements (elements 0 - 5000).
        for (int i = elements; i >= 0; i--) {
            insertOrder.add(i);
        }
        // Search for 5000 elements (elements 500 - 699).
        for (int i = 500; i < 1000; i += 5) {
            searchOrder.add(i);
        }
        for (int i = 500; i < 1000; i += 5) {
            deleteOrder.add(i);
        }

        return dataStructureComparison(insertOrder, searchOrder, deleteOrder, deleteMin, NUMBER_OF_REPEATS);
    }

    /**
     * Create a good example to demonstrate under which circumstances TreeSet is the best data structure to use.
     * Choose the appropriate parameters by experimentation and thinking logically.
     * Choose parameters reasonably (i.e., no point having no inserts etc.)
     *
     * @param insertOrder insertion order
     * @param searchOrder search order
     * @param deleteOrder delete order
     * @param deleteMin   find and delete minimum element the number of elements times
     * @return the list with data structure names and measurement times (e.g., ["List", 0.3], ["Hashtable", 0.4] etc.)
     */
    public final List<Entry<String, Double>> treeSetIsTheBestWhen(List<Integer> insertOrder, List<Integer> searchOrder,
                                                                  List<Integer> deleteOrder, List<Boolean> deleteMin) {
        insertOrder.clear();
        searchOrder.clear();
        deleteOrder.clear();
        deleteMin.clear();

        int elements = 5000;

        // Insert 5000 elements (elements 0 - 5000).
        for (int i = 0; i <= elements; i++) {
            insertOrder.add(i);
        }
        // Search for 5000 elements (elements 500 - 699).
        for (int i = 0; i < elements; i++) {
            searchOrder.add(i);
        }
        for (int i = 0; i < elements; i ++) {
            deleteOrder.add(i);
        }

        for (int i = 0; i < elements; i ++) {
            deleteMin.add(true);
        }

        return dataStructureComparison(insertOrder, searchOrder, deleteOrder, deleteMin, NUMBER_OF_REPEATS);
    }
    
    /* ----------------------------------------------------------------------------------------------
     * The stuff below you don't need to change. It's bookkeeping stuff for making the measurements.
     * ----------------------------------------------------------------------------------------------
     */

    /**
     * Data structure procedures - insert, search and delete.
     * Insert all elements in insertOrder, then
     * Search for every item in searchOrder and then
     * Delete all of the items in deleteMin and deleteOrder.
     *
     * @param insertOrder insertion order
     * @param searchOrder search order
     * @param deleteOrder delete order
     * @param deleteMin   find and delete minimum element the number of elements times
     */
    public final void procedures(ComparisonGeneric dataStructure, List<Integer> insertOrder, List<Integer> searchOrder,
                                 List<Integer> deleteOrder, List<Boolean> deleteMin)
            throws Exception {
        for (Integer l : insertOrder) {
            dataStructure.insert(l);
        }
        for (Integer l : searchOrder) {
            dataStructure.search(l);
        }
        for (int i = 0; i < deleteMin.size(); i++) {
            dataStructure.deleteMin();
        }
        for (Integer l : deleteOrder) {
            dataStructure.delete(l);
        }
    }

    /**
     * Compare the data structures to each other by measuring their performance given the input data.
     *
     * @param insertOrder   insertion order
     * @param searchOrder   search order
     * @param deleteOrder   delete order
     * @param deleteMin     find and delete minimum element the number of elements times
     * @param numberOfTimes the number of repeats for averaging
     * @return the resulting data structure
     */
    public final List<Entry<String, Double>> dataStructureComparison(List<Integer> insertOrder, List<Integer> searchOrder,
                                                                     List<Integer> deleteOrder, List<Boolean> deleteMin,
                                                                     int numberOfTimes) {
        List<Entry<String, Double>> rv = new ArrayList<>();
        List<ComparisonGeneric> dataStructures = new ArrayList<>();
        dataStructures.add(new ComparisonListImpl());
        dataStructures.add(new ComparisonHashtableImpl());
        dataStructures.add(new ComparisonPriorityQueueImpl());
        dataStructures.add(new ComparisonTreeSetImpl());
        for (int i = 0; i < dataStructures.size(); i++) {
            long total = 0;
            for (int j = 0; j < numberOfTimes; j++) {
                long start = System.currentTimeMillis();
                long end = start;
                try {
                    dataStructures.get(i).clear();
                    procedures(dataStructures.get(i), insertOrder, searchOrder, deleteOrder, deleteMin);
                    end = System.currentTimeMillis();
                } catch (Exception e) {
                    end = System.currentTimeMillis() + EXCEPTION_PENALTY;
                }
                total += end - start;
                System.out.println(dataStructures.get(i).getClass().getSimpleName() + " procedures took " + (end - start) + " ms.");
            }
            System.out.println(dataStructures.get(i).getClass().getSimpleName() + " average is " + (total / numberOfTimes) + " ms.");
            rv.add(new SimpleEntry<String, Double>(dataStructures.get(i).getClass().getSimpleName(), ((double) (total / numberOfTimes)) / 1000.0));
        }
        return rv;
    }
}