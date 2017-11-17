package praks11.threadoverwatchers;

public class SecurityGateOverwatcher implements Runnable {

    private Runnable gate;
    private final int TIME_TO_SLEEP;

    public SecurityGateOverwatcher(Runnable gate, int time_to_sleep) {
        this.gate = gate;
        TIME_TO_SLEEP = time_to_sleep;
    }


    @Override
    public void run() {
        Thread thread = new Thread(gate);
        thread.start();
        try {
            Thread.sleep(TIME_TO_SLEEP);
        } catch (InterruptedException e) {
            System.out.println("Interrupted externally!");
        }
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
