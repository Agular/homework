package engines;

public class DieselEngine implements Engine {

    @Override
    public String getType() {
        return "Diesel";
    }

    @Override
    public double getPollutionUnits() {
        return 3.0f;
    }
}
