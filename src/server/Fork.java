package server;

public class Fork {
    private final int id;
    private boolean isAvailable = true;

    public Fork(int id) {
        this.id = id;
    }

    public synchronized boolean pickUp() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public synchronized void putDown() {
        isAvailable = true;
    }

    public int getId() {
        return id;
    }
}
