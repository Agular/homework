package openweathermap.weather;

import java.util.List;

public class ForecastMaxMinTemperatures {

    private List<MaxMinTemperatures> maxMinTemperatures;

    public ForecastMaxMinTemperatures(List<MaxMinTemperatures> maxMinTemperatures) {
        if (maxMinTemperatures == null) {
            throw new IllegalArgumentException();
        }
        this.maxMinTemperatures = maxMinTemperatures;
    }

    private boolean arrayIsNotOrderedByDate(List<MaxMinTemperatures> maxMinTemperatures) {

        for (int i = 0; i < maxMinTemperatures.size() - 1; i++) {
            if (maxMinTemperatures.get(i).getDate().plusDays(1).compareTo(
                    maxMinTemperatures.get(i + 1).getDate()) != 0) {
                return true;
            }
        }
        return false;
    }

    private List<MaxMinTemperatures> getmaxMinTemperatures() {
        return maxMinTemperatures;
    }

    @Override
    public String toString() {
        if (maxMinTemperatures != null) {
            String string = "";
            for (int i = 0; i < maxMinTemperatures.size(); i++) {
                string = string +
                        "date:" + maxMinTemperatures.get(i).getDate() +
                        " max:" + maxMinTemperatures.get(i).getMaxTemperature().getValue() +
                        " min:" + maxMinTemperatures.get(i).getMinTemperature().getValue();
                if (i != maxMinTemperatures.size() - 1) {
                    string = string + "\n";
                }
            }
            return string;
        } else return "";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ForecastMaxMinTemperatures) {
            return this.maxMinTemperatures.equals(((ForecastMaxMinTemperatures) other).maxMinTemperatures);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + maxMinTemperatures.hashCode();
        return hashCode;
    }
}
