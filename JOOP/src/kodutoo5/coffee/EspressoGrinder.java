package kodutoo5.coffee;

import kodutoo5.exceptions.GrinderNotCleanException;

public class EspressoGrinder extends Grinder{

    @Override
    public double grind() throws GrinderNotCleanException {
        if(super.calloutCounter == 3){
            throw new GrinderNotCleanException();
        }
        if(super.isInEnergySavingMode()){
            super.exitEnergySavingMode();
        }
        super.calloutCounter++;
        return 0.3;
    }

}
