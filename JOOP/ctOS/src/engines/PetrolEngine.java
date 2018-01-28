package engines;

public class PetrolEngine implements Engine {
    @Override
    public String getType() {
        return "Petrol";
    }

    @Override
    public double getPollutionUnits() {
        return 2.0f;
    }
}
