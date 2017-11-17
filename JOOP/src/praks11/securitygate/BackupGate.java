package praks11.securitygate;

public class BackupGate implements Runnable{
    @Override
    public void run() {
        while(!Thread.interrupted()){

        }
        System.out.println("Backup gate has finished it's work!");
    }
}
