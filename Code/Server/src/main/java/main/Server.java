package main;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    protected Map<Integer, ClientHandler> clientHandlers = new HashMap<>();
    private int nextId = 0;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Server is running");

        while (true) {
            ClientHandler handler = new ClientHandler(serverSocket.accept(), nextId);
            clientHandlers.put(nextId, handler);
            System.out.println("Client "+nextId+" has connected");
            nextId++;
            handler.start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public void removeHandler(int id) {
        clientHandlers.remove(id);
        System.out.println("Client "+id+" has disconnected");
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private int id;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket, int id) {
            this.clientSocket = socket;
            this.id = id;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String inputLine;
            while (true) {
                try {
                    if ((inputLine = in.readLine()) == null) break; // when a user disconnects
                    if (inputLine.equals(".")) {
                        out.println("bye");
                        break;
                    }
                    else if (inputLine.equals("hello server")) {
                        out.println("hello client "+this.id);
                    }
                    else {
                        out.println(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                in.close();
                out.close();
                clientSocket.close();
                removeHandler(this.id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
    }
}
