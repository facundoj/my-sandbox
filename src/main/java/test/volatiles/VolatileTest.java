package test.volatiles;

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        SharedResource shared = new SharedResource();
        Thread listener1 = new Thread(new ListenerThread(shared));
        Thread listener2 = new Thread(new ListenerThread(shared));

        // Trigger thread execution & wait
        listener1.start();
        listener2.start();
        Thread.sleep(1000);

        // Stop by another thread
        shared.turnOff();
    }
}
