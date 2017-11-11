package kodutoo5;

import kodutoo5.coffee.Grinder;
import kodutoo5.exceptions.GrinderNotCleanException;

import java.math.BigDecimal;
import java.time.LocalTime;

public class GrinderController {

    public static void main(String[] args){

        Grinder grinder = Grinder.newInstance(LocalTime.now());
        try {
            for(int i=0; i < 4; i++){
                grinder.grind();
            }
        } catch (GrinderNotCleanException e) {
            System.out.println("Grinder needs to be cleaned. Cleaning now.");
            grinder.clean();
        } finally {
            Grinder espressoGrinder = Grinder.newInstance(LocalTime.of(8,0,0));
            try {
                espressoGrinder.setCustomBeanRatio(new BigDecimal("0.83"), new BigDecimal("0.24"));
            } catch (IllegalArgumentException e1){
                e1.printStackTrace();
            }
        }
    }
}
