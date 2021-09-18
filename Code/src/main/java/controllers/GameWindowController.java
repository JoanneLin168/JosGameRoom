package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class GameWindowController implements Initializable {

    private String name = "anonymous";

    @FXML private Button btn_d2;
    @FXML private Button btn_d4;
    @FXML private Button btn_d6;
    @FXML private Button btn_d8;
    @FXML private Button btn_d10;
    @FXML private Button btn_d12;
    @FXML private Button btn_d20;
    @FXML private TextArea textArea_gameLog;
    @FXML private TextArea textArea_name;

    @FXML
    public void rollDice(ActionEvent event) {
        int dice = Integer.parseInt(((Button) event.getSource()).getText().substring(1));
        int roll = ThreadLocalRandom.current().nextInt(1, dice+1);

//        String logText = textArea_gameLog_gameLog.getText() + Integer.toString(roll) + "\n";
//        textArea_gameLog.setText(logText);

        // TODO: Find a better autoscroll method
        textArea_gameLog.appendText(name + " rolled: " + Integer.toString(roll) + "\n");

    }

    @FXML
    public void updateName(ActionEvent event) {
        name = textArea_name.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
