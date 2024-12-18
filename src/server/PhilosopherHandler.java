package server;

import java.io.*;
import java.net.*;
import java.util.Random;

public class PhilosopherHandler extends Thread {
    private final Socket socket;
    private int philosopherId;
    private PrintWriter out;
    private BufferedReader in;
    private Random random = new Random();

    public PhilosopherHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            philosopherId = PhilosopherHandlerFactory.getNextPhilosopherId();
            Server.philosopherStates.put(philosopherId, new PhilosopherState(philosopherId));

            out.println("HI " + philosopherId);

            String message;
            while ((message = in.readLine()) != null) {
                handleRequest(message);
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRequest(String message) {
        switch (message) {
            case "HELLO":
                out.println("HI " + philosopherId);
                break;
            case "THINK":
                if (!Server.philosopherStates.get(philosopherId).isFull()) {
                    Server.philosopherStates.get(philosopherId).think();
                    out.println("It's thinking...");
                }
                break;
            case "EAT":
                tryEating();
                break;
            default:
                out.println("Unknown Command");
        }
    }

    private void tryEating() {
        if (Server.philosopherStates.get(philosopherId).isFull()) {
            out.println("Fome saciada");
            return;
        }

        Fork leftFork = Server.forks[philosopherId];
        Fork rightFork = Server.forks[(philosopherId + 1) % Server.NUM_PHILOSOPHERS];

        synchronized (leftFork) {
            if (!leftFork.pickUp()) {
                Server.philosopherStates.get(philosopherId).think();
                out.println("Left fork is not available, thinking...");
                return;
            }

            synchronized (rightFork) {
                if (!rightFork.pickUp()) {
                    leftFork.putDown();
                    Server.philosopherStates.get(philosopherId).think();
                    out.println("Right fork is not available, thinking...");
                    return;
                }

                Server.philosopherStates.get(philosopherId).eat();
                int eatTime = Math.max(0, Math.min(60000, random.nextInt(60000)));
                out.println("Eating for " + eatTime + " ms...");
                try {
                    Thread.sleep(eatTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                rightFork.putDown();
                leftFork.putDown();
                out.println("Finished eating");

                int thinkTime = Math.max(0, (int) (random.nextGaussian() * 2 + 5));
                out.println("Thinking for " + thinkTime + " ms...");
                try {
                    Thread.sleep(thinkTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
