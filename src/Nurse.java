import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Nurse extends Thread {
    private final int id;
    private final Lock readLock;

    public Nurse(int id, Lock readLock) {
        this.id = id;
        this.readLock = readLock;
    }

    @Override
    public void run()
    {
        log("Nurse " + id + " is waiting for reading.");
        readLock.lock();
        try
        {
            log("Nurse " + id + " is reading the patient record.");
            Thread.sleep(1000 + new Random().nextInt(1000)); // Simulate reading time
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt(); // Restore interrupted status
        } 
         finally 
        {
            readLock.unlock();
            log("Nurse " + id + " finished reading and released the lock.");
        }
    }

    private void log(String message) 
    {
        System.out.printf("[%s] %s%n", java.time.LocalTime.now(), message);
    }
}

