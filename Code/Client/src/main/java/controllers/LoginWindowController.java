package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.Client;
import main.PacketType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {

    private Client client;

    @FXML private TextArea textArea_name;

    public LoginWindowController(Client client) {
        this.client = client;
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        String name = textArea_name.getText();
        if (name.equals("")) {
            name = "anonymous";
        }

        client.sendMessage(PacketType.LOGIN+":"+name);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
