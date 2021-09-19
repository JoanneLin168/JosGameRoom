package main;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    protected Map<Integer, ClientHandler> clientHandlers = new HashMap<>();
    private int nextId = 1; // number 0 is for server
    private final ServerMessageHandler messageHandler = new ServerMessageHandler();

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

    public void removeClientHandler(int id) {
        clientHandlers.remove(id);
        System.out.println("Client "+id+" has disconnected");
    }

    private class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final int id;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket, int id) {
            this.clientSocket = socket;
            this.id = id;
        }

        public PrintWriter getOut() {
            return out;
        }

        public BufferedReader getIn() {
            return in;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // need to add the IOPair here if not, out and in is null
            messageHandler.addClientIOPair(nextId, new IOPair(out, in));

            String inputLine;
            while (true) {
                try {
                    if ((inputLine = in.readLine()) == null) break; // when a user disconnects
                    else {
                        // let the messageHandler deal with the message
                        messageHandler.handleMessage(inputLine, id, true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                in.close();
                out.close();
                clientSocket.close();
                removeClientHandler(this.id);
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
