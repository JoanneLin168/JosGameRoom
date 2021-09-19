package main;

import java.net.*;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }


    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String msg) throws IOException {
        System.out.println(msg);
        out.println(msg);
    }

    public String receiveMessage() throws IOException {
        String resp = in.readLine();
        System.out.println(resp);
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * Determines if the handler is ready to receive a message
     * @return - Whether the handler is ready to receive a message
     * @throws IOException - Thrown when an error connecting to the server occurs
     */
    public boolean isReady() throws IOException {
        return this.in.ready();
    }
}
