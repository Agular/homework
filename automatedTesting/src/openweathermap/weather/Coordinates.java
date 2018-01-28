package openweathermap.weather;

public class Coordinates {
    private Double longitude;
    private Double latitude;

    public Coordinates(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException();
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getlongitude() {
        return longitude;
    }

    public Double getlatitude() {
        return latitude;
    }

    @Override
    public String toString(){
        return "lon:" + longitude + " lat:" + latitude;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Coordinates) {
            return coordinatesAreEqualWithOther((Coordinates) other);
        }
        return false;
    }

    private boolean coordinatesAreEqualWithOther(Coordinates other){
        return this.longitude.equals(other.longitude) && this.latitude.equals(other.latitude);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + longitude.hashCode();

        hashCode = 31 * hashCode + latitude.hashCode();
        return hashCode;
    }
}
