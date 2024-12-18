package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static final int NUM_PHILOSOPHERS = 5;
    public static final int PORT = 3030;
    public static final Fork[] forks = new Fork[NUM_PHILOSOPHERS];
    public static final ConcurrentMap<Integer, PhilosopherState> philosopherStates = new ConcurrentHashMap<>();
    private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_PHILOSOPHERS);

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
        }

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running! Use ´telnet localhost 3030´ to connect");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New connection: " + clientSocket.getInetAddress());
            new PhilosopherHandler(clientSocket).start();
        }
    }
}
