package test.volatiles;

import java.util.Date;

public class SharedResource {
    // If it wasn't volatile, updates caused in a thread wouldn't be seen in other
    private /*volatile*/ boolean flag = true;

    public void turnOff() {
        flag = false;
        System.out.println("Attempt to end: " + new Date().getTime());
    }

    public boolean get() {
        return flag;
    }
}
