package messageHandlers;

import controllers.GameWindowController;
import javafx.fxml.Initializable;
import main.PacketType;

import java.io.BufferedReader;
import java.io.PrintWriter;

public abstract class BaseMessageHandler {

    protected PacketType packetType;

    /**
     * sends a message from the server to client
     */
    public abstract void transmit(String msg, PrintWriter out, BufferedReader in);

    /**
     * does an action using a message from the client to server
     */
    public abstract void receive(String msg, PrintWriter out, BufferedReader in);
}
