package ee.ttu.algoritmid.dancers.tests;

import ee.ttu.algoritmid.dancers.Dancer;
import ee.ttu.algoritmid.dancers.HW01;
import ee.ttu.algoritmid.dancers.dancertree.BallroomDancer;
import org.junit.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

import static ee.ttu.algoritmid.dancers.Dancer.Gender.FEMALE;
import static ee.ttu.algoritmid.dancers.Dancer.Gender.MALE;
import static org.junit.Assert.fail;


public class TreeImplementationTester {

    @Test
    public void testFemaleTreeImplementation() {
        HW01 impl = new HW01();

        addDancerAndTest(impl, 80, FEMALE, null);
        addDancerAndTest(impl, 120, FEMALE, null);
        addDancerAndTest(impl, 40, FEMALE, null);
        addDancerAndTest(impl, 100, FEMALE, null);
        addDancerAndTest(impl, 20, FEMALE, null);
        addDancerAndTest(impl, 140, FEMALE, null);
        addDancerAndTest(impl, 110, FEMALE, null);
        addDancerAndTest(impl, 60, FEMALE, null);
        addDancerAndTest(impl, 130, FEMALE, null);
        addDancerAndTest(impl, 30, FEMALE, null);
        addDancerAndTest(impl, 50, FEMALE, null);
        addDancerAndTest(impl, 10, FEMALE, null);

        addDancerAndTest(impl, 81, MALE, 80);
        addDancerAndTest(impl, 51, MALE, 50);
        addDancerAndTest(impl, 111, MALE, 110);

        addDancerAndTest(impl, 49, FEMALE, null);
        addDancerAndTest(impl, 109, FEMALE, null);
        addDancerAndTest(impl, 51, FEMALE, null);
        addDancerAndTest(impl, 111, FEMALE, null);

        addDancerAndTest(impl, 141, MALE, 140);
        addDancerAndTest(impl, 121, MALE, 120);
        addDancerAndTest(impl, 131, MALE, 130);
        addDancerAndTest(impl, 101, MALE, 100);
        addDancerAndTest(impl, 112, MALE, 111);
        addDancerAndTest(impl, 112, MALE, 109);

        List<Integer> correctIds = Arrays.asList(10, 20, 30, 40, 49, 51, 60);
        List<Dancer> waitingList = impl.returnWaitingList();

        final boolean result1 = waitingList.size() == correctIds.size();
        if (!result1) fail("Number of remaining dancers is not correct.");

        boolean result2 = checkDancerIds(waitingList, correctIds);
        if (!result2) fail("Remaining dancer IDs are not correct.");
    }


    @Test
    public void testMaleTreeImplementation() {
        HW01 impl = new HW01();
        List<Integer> correctIds = Arrays.asList(15, 20, 27, 32, 40, 45, 70, 75, 80, 85, 90);

        addDancerAndTest(impl, 30, MALE, null);
        addDancerAndTest(impl, 80, MALE, null);
        addDancerAndTest(impl, 20, MALE, null);
        addDancerAndTest(impl, 50, MALE, null);
        addDancerAndTest(impl, 40, MALE, null);
        addDancerAndTest(impl, 70, MALE, null);
        addDancerAndTest(impl, 90, MALE, null);
        addDancerAndTest(impl, 15, MALE, null);
        addDancerAndTest(impl, 25, MALE, null);
        addDancerAndTest(impl, 35, MALE, null);
        addDancerAndTest(impl, 45, MALE, null);
        addDancerAndTest(impl, 75, MALE, null);
        addDancerAndTest(impl, 85, MALE, null);
        addDancerAndTest(impl, 27, MALE, null);
        addDancerAndTest(impl, 32, MALE, null);

        System.out.println(impl.returnWaitingList());

        addDancerAndTest(impl, 29, FEMALE, 30);
        addDancerAndTest(impl, 24, FEMALE, 25);
        addDancerAndTest(impl, 34, FEMALE, 35);

        addDancerAndTest(impl, 26, MALE, null);
        addDancerAndTest(impl, 33, MALE, null);

        addDancerAndTest(impl, 25, FEMALE, 26);
        addDancerAndTest(impl, 32, FEMALE, 33);
        addDancerAndTest(impl, 49, FEMALE, 50);

        List<Dancer> waitingList = impl.returnWaitingList();
        final boolean result1 = waitingList.size() == correctIds.size();
        if (!result1) fail("Number of remaining dancers is not correct.");

        boolean result2 = checkDancerIds(waitingList, correctIds);
        if (!result2) fail("Remaining dancer IDs are not correct.");
    }

    private static void addDancerAndTest(HW01 impl, int height, Dancer.Gender gender, Integer takeoutHeight) {
        final Dancer dancer = new BallroomDancer(height, gender, height);
        SimpleEntry<Dancer, Dancer> se = impl.findPartnerFor(dancer);

        if (takeoutHeight != null) {
            final boolean result;
            if (gender == MALE) {
                result = se != null && se.getKey().getID() == takeoutHeight && se.getValue().getID() == height;
            } else {
                result = se != null && se.getKey().getID() == height && se.getValue().getID() == takeoutHeight;
            }


            if (!result) fail("Your implementation found an incorrect pair.");
        } else {
            if (se != null) {
                fail("Your implementation found a pair when it should not have.");
            }
        }
    }

    private static boolean checkDancerIds(List<Dancer> dancers, List<Integer> correctIds) {
        for (int i = 0; i < correctIds.size(); i++) {
            final int dancerId = dancers.get(i).getID();
            final int correctId = correctIds.get(i);
            if (correctId != dancerId) {
                return false;
            }
        }
        return true;
    }
}
