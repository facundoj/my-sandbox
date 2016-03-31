package test.volatiles;

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        SharedResource shared = new SharedResource();
        Thread listener = new Thread(new ListenerThread(shared));

        // Trigger thread execution & wait
        listener.start();
        Thread.sleep(500);

        // Stop by another thread
        shared.turnOff();
    }
}

