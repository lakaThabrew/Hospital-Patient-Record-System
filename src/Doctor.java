import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Doctor extends Thread {
    private final int id;
    private final Lock writeLock;

    public Doctor(int id, Lock writeLock) {
        this.id = id;
        this.writeLock = writeLock;
    }

    @Override
    public void run()
    {
        log("Doctor " + id + " is waiting for writing.");
        writeLock.lock();
        try
        {
            log("Doctor " + id + " is writing diagnosis and prescriptions.");
            Thread.sleep(1500 + new Random().nextInt(2000)); // Simulate reading time
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        } 
         finally 
        {
            writeLock.unlock();
            log("Doctor " + id + " has finished writing and released the lock.");
        }
    }

    private void log(String message) {
        System.out.printf("[%s] %s%n", java.time.LocalTime.now(), message);
    }
}