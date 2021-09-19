package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import main.Client;
import main.PacketType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class GameWindowController implements Initializable {

    private String name;
    private Client client;

    @FXML private Button btn_d2;
    @FXML private Button btn_d4;
    @FXML private Button btn_d6;
    @FXML private Button btn_d8;
    @FXML private Button btn_d10;
    @FXML private Button btn_d12;
    @FXML private Button btn_d20;
    @FXML private TextArea textArea_gameLog;

    public GameWindowController(Client client) {
        this.name = "anonymous"; // need to pass in name *after* log in screen
        this.client = client;
    }

    @FXML
    public void rollDice(ActionEvent event) throws IOException {
        int dice = Integer.parseInt(((Button) event.getSource()).getText().substring(1));
        int roll = ThreadLocalRandom.current().nextInt(1, dice+1);

        client.sendMessage(PacketType.DICE_ROLL+":"+roll);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
