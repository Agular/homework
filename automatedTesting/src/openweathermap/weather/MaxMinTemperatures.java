package openweathermap.weather;

import java.time.LocalDate;

public class MaxMinTemperatures {
    private LocalDate date;
    private Temperature maxTemperature;
    private Temperature minTemperature;

    public MaxMinTemperatures() {
    }

    public MaxMinTemperatures(LocalDate date, Temperature maxTemperature, Temperature minTemperature) {
        if (date == null || maxTemperature == null || minTemperature == null) {
            throw new IllegalArgumentException();
        }
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMaxTemperature(Temperature maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(Temperature minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Temperature getMaxTemperature() {
        return maxTemperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public Temperature getMinTemperature() {
        return minTemperature;
    }

    @Override
    public String toString(){
        if (date != null && maxTemperature != null && minTemperature != null) {
            return "date:" + date.toString() + " max:" + maxTemperature.toString() + " min:" + minTemperature.toString();
        }
        else return "";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof MaxMinTemperatures) {
            MaxMinTemperatures o = (MaxMinTemperatures) other;
            return this.date.equals((o.date)) && this.maxTemperature.equals(o.maxTemperature) && this.minTemperature.equals(o.minTemperature);
        }
        return false;
    }



   @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + date.hashCode();

        hashCode = 31 * hashCode + maxTemperature.hashCode();

        hashCode = 31 * hashCode + minTemperature.hashCode();

        return hashCode;
    }
}