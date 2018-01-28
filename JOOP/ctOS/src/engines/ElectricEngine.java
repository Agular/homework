package engines;

public class ElectricEngine implements Engine{

    @Override
    public String getType() {
        return "Electric";
    }

    @Override
    public double getPollutionUnits() {
        return 0.1f;
    }
}
