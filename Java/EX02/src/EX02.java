/**
 * Created by Ragnar on 09.02.2016
 * Let's test.
 * Let's test again.
 */


public class EX02 {
    public static Integer[] DayList = new Integer[30];
    /**
     * Constant.
     * Every 3 days, feed worms.
     */
    public static final int WORM_FEEDING_DAY = 3;

    /**
     * Constant.
     * Every 5 days, bathe in sand.
     */
    public static final int BATHING_DAY = 5;

    /**
     * Constant.
     * Total number of days for which instructions are needed.
     */
    public static final int NUMBER_OF_DAYS = 30;

    /**
     * Entry point of the program.
     *
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        // call and print getInstructionForCurrentDay inside a loop here
        for (int i = 1; i < 31; i++) {
            System.out.println(getInstructionForCurrentDay(i));
        }
    }

    /**
     * Return instruction for given day.
     *
     * @param currentDay number of day to print instructions for.
     * @return A string with given day and instructions to feed the squirrel.
     */
    public static  String getInstructionForCurrentDay(int currentDay) {
        if (currentDay < 1) {
            return ("Can't fly back in time");
        } else if (currentDay % 15 == 0) {
            return ("Day " + currentDay + " : glide in wind");
        } else if (currentDay % 3 == 0) {
            return ("Day " + currentDay + " : feed worms");
        } else if (currentDay % 5 == 0) {
            return ("Day " + currentDay + " : bathe in sand");
        } else {
            return ("Day " + currentDay + " : give fruit and water");
        }
    }
}