package openweathermap.weather;

public class Temperature {

    private Double temperature;

    public Temperature(Double temperature){
        if(temperature == null){
            throw new IllegalArgumentException();
        }
        this.temperature = temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getValue() {
        return temperature;
    }

    @Override
    public String toString(){
        return "temp:" + temperature.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Temperature) {
            return this.temperature.equals(((Temperature)other).temperature);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + temperature.hashCode();
        return hashCode;
    }
}
