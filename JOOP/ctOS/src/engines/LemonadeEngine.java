package engines;

public class LemonadeEngine implements Engine {
    @Override
    public String getType() {
        return "Lemonade";
    }

    @Override
    public double getPollutionUnits() {
        return 0.5f;
    }
}
