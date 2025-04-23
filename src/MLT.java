import java.util.Random;
import java.util.concurrent.locks.Lock;

public class MLT extends Thread {
    private final int id;
    private final Lock writeLock;

    public MLT(int id, Lock writeLock) {
        this.id = id;
        this.writeLock = writeLock;
    }

    @Override
    public void run()
    {
        log("MLT " + id + " is waiting for writing.");
        writeLock.lock();
        try
        {
            log("MLT " + id + " is writing diagnosis and prescriptions.");
            Thread.sleep(2500 + new Random().nextInt(3000)); // Simulate reading time
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        } 
         finally 
        {
            writeLock.unlock();
            log("MLT " + id + " has finished writing and released the lock.");
        }
    }

    private void log(String message) {
        System.out.printf("[%s] %s%n", java.time.LocalTime.now(), message);
    }
}