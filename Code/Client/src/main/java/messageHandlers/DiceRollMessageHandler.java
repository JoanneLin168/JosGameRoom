package messageHandlers;

import controllers.GameWindowController;
import javafx.fxml.Initializable;
import main.PacketType;
import main.StageManager;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DiceRollMessageHandler extends BaseMessageHandler {

    public DiceRollMessageHandler() {
        this.packetType = PacketType.DICE_ROLL;
    }

    @Override
    public void transmit(String msg, PrintWriter out, BufferedReader in) {
        System.out.println("?");
    }

    @Override
    public void receive(String msg, PrintWriter out, BufferedReader in) {
        String toDisplay = "Rolled: " + msg + "\n";
        StageManager.getInstance().renderToGameLog(toDisplay);
    }
}