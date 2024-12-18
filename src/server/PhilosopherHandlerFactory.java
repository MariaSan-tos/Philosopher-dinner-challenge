package server;

public class PhilosopherHandlerFactory {
    private static int philosopherIdCounter = 0;

    public static synchronized int getNextPhilosopherId() {
        return philosopherIdCounter++;
    }
}
