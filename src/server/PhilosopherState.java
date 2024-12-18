package server;

public class PhilosopherState {
    private final int id;
    private int timesThought = 0;
    private int timesAte = 0;

    public PhilosopherState(int id) {
        this.id = id;
    }

    public void think() {
        timesThought++;
    }

    public void eat() {
        timesAte++;
    }

    public int getTimesThought() {
        return timesThought;
    }

    public int getTimesAte() {
        return timesAte;
    }

    public boolean isFull() {
        return timesAte >= 10;
    }
}
