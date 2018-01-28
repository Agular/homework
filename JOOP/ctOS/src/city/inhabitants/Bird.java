package city.inhabitants;

import city.City;

public class Bird implements Runnable {

    private City city;
    private final int MAX_POLLUTION_LEVEL = 400;

    public Bird(City city) {
        this.city = city;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (city.getPollutionUnits() > MAX_POLLUTION_LEVEL) {
                singSadSong();
            } else {
                singHappySong();
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void singHappySong(){
        System.out.println("Puhas õhk on puhas õhk on rõõmus linnu elu!");
    }

    private void singSadSong(){
        System.out.println("Inimene tark, inimene tark – saastet täis on linnapark");
    }
}
