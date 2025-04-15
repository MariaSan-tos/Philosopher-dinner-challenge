# Philosophers' Dinner – Parallel and Distributed Programming Assignment

## Description

Five philosophers are seated around a round table. Each philosopher needs two forks to eat, but each fork can only be used by one philosopher at a time. This code implements a server that simulates the philosophers' behavior using multiple Telnet connections. Each philosopher can alternate between **thinking** and **eating**, with the possibility of thinking for a random amount of time and eating for up to 1 minute.

## Usage Instructions

1. **Compilation**:  
   To compile the code, simply use the `javac` command:

   ```bash
   javac Server.java
   ```

2. **Execution**:  
   After compilation, run the server with the following command:

   ```bash
   java Server
   ```

   The server will start and wait for client connections on port **12345**.

3. **Telnet Connection**:  
   In another terminal, you can use Telnet to connect to the server. Run the following command:

   ```bash
   telnet localhost 12345
   ```

   Upon connection, the server will send a greeting message, and you'll be able to interact with the philosophers using the following commands:

   - **HELLO**: Receive a greeting from the server.
   - **THINK**: The philosopher starts thinking. Once the forks are free, they will begin eating.
   - **EAT**: The philosopher attempts to eat. If forks aren't available, they will think. Eating time is random but limited to a maximum of 1 minute.

   You can open multiple Telnet connections (up to 5) to simulate philosophers trying to eat.

4. **Exit Command**:  
   To disconnect, simply close the Telnet connection.

## How It Works

- **Philosophers**: Represented by clients connecting to the server via Telnet. Each client receives a unique ID.
- **Forks**: Shared resources among philosophers. Each philosopher needs two forks to eat.
- **Behavior**: Philosophers alternate between the **thinking** and **eating** states. If forks are available, the philosopher eats; otherwise, they think until the forks become available.

## Limitations

- The server limits the number of meals to **10 times** per philosopher, after which they are considered "full."
- The thinking time is randomly generated between **10 and 70 seconds**.
- Eating time is randomly generated but guaranteed to be **no more than 1 minute**.

## Purpose

This code was developed to demonstrate the use of parallel and distributed programming by simulating a system where multiple philosophers (clients) attempt to access shared resources (forks) while alternating between the **thinking** and **eating** states.
