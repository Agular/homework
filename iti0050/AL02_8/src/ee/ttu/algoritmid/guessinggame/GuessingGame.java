package ee.ttu.algoritmid.guessinggame;

import java.util.Arrays;
import java.util.Comparator;

public class GuessingGame {

    Oracle oracle;

    public GuessingGame(Oracle oracle) {
        this.oracle = oracle;
    }

    /**
     * @param fruitArray - All the possible fruits.
     * @return the name of the fruit.
     */
    public String play(Fruit[] fruitArray) {
        Arrays.sort(fruitArray, Comparator.comparing(Fruit::getWeight));
        String oracleAnswer;
        int low  = 0;
        int high = fruitArray.length - 1;
        int mid = (low + high) / 2;
        while(!(oracleAnswer = oracle.isIt(fruitArray[mid])).equals("correct!")){
            if(oracleAnswer.equals("heavier")){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
            if(low > high){
                return "";
            }
            mid = (low + high) / 2;
        }
        return fruitArray[mid].getName();
    }
}
