package messageHandlers;

import controllers.GameWindowController;
import controllers.LoginWindowController;
import javafx.fxml.Initializable;
import main.PacketType;
import main.StageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginMessageHandler extends BaseMessageHandler {

    public LoginMessageHandler() {this.packetType = PacketType.LOGIN; }

    @Override
    public void transmit(String msg, PrintWriter out, BufferedReader in) {
        System.out.println("?");
    }

    @Override
    public void receive(String msg, PrintWriter out, BufferedReader in) {
        System.out.println(msg);
        try {
            StageManager.getInstance().changeToGameWindowScene();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
