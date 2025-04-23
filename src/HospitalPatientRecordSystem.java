// BEGIN HospitalPatientRecordSystem.java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HospitalPatientRecordSystem {
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true); // fair lock
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();

    public static void main(String[] args) {
        // Simulate multiple threads accessing the system
        for (int i = 1; i <= 5; i++) {
            new Nurse(i, readLock).start();
        }

        for (int i = 1; i <= 2; i++) {
            new Doctor(i, writeLock).start();
        }

        new MLT(1, writeLock).start();

    }


}
