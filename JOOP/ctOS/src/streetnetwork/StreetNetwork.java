package streetnetwork;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author of graph solution: Samed Düzçay
 * Graph solution taken from his GitHub: https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
 * Renamed Graph to StreetNetwork.
 */

public class StreetNetwork {
    private List<Intersection> intersections;
    private Set<Street> streets;
    private List<Intersection> startingIntersections;
    private Map<Intersection, Set<Street>> adjList;

    public StreetNetwork() {
        intersections = new ArrayList<>();
        streets = new HashSet<>();
        startingIntersections = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public boolean addIntersection(int label) {
        return intersections.add(new Intersection(label));
    }

    public boolean addIntersection(Intersection i) {
        return intersections.add(i);
    }

    public boolean addStartingIntersection(int label) {
        Intersection startingIntersection = new Intersection(label);
        return intersections.add(startingIntersection) && startingIntersections.add(startingIntersection);
    }

    public List<Intersection> getStartingIntersections() {
        return startingIntersections;
    }

    public boolean addStreet(Street e) {
        if (!streets.add(e)) return false;

        adjList.putIfAbsent(e.i1, new HashSet<>());
        adjList.putIfAbsent(e.i2, new HashSet<>());

        adjList.get(e.i1).add(e);
        adjList.get(e.i2).add(e);

        if (intersectionIsAStartingIntersection(e.i1)) {
            startingIntersections.add(e.i1);
        }
        if (intersectionIsAStartingIntersection(e.i2)) {
            startingIntersections.add(e.i2);
        }

        if (!intersections.contains(e.i1)) {
            intersections.add(e.i1);
        }
        if (!intersections.contains(e.i2)) {
            intersections.add(e.i2);
        }
        return true;
    }

    public boolean intersectionIsAStartingIntersection(Intersection intersection) {
        return intersection.isStartingIntersection();
    }

    public List<Intersection> getAdjIntersections(Intersection i) {
        return adjList.get(i).stream()
                .map(e -> e.i1.equals(i) ? e.i2 : e.i1)
                .collect(Collectors.toList());
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public Street getStreet(Intersection intersection1, Intersection intersection2) {
        return streets.stream().filter(s -> (s.i1.equals(intersection1) && s.i2.equals(intersection2)) ||
                ((s.i1.equals(intersection2) && s.i2.equals(intersection1)))).findFirst().get();
    }
}
