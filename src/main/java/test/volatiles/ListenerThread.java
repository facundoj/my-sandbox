package test.volatiles;

import java.util.Date;

public class ListenerThread implements Runnable {
    private SharedResource resource;

    public ListenerThread(SharedResource shared) {
        this.resource = shared;
    }

    public void run() {
        while (resource.get()) { /* loop until other thread modifies the shared resource */ }
        System.out.println("Actually ended: " + new Date().getTime());
    }
}
