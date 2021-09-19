package main;

import controllers.GameWindowController;
import controllers.LoginWindowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private static StageManager instance;
    private Stage stage;
    private Client client;
    private ClientMessageHandler messageHandler;
    private IncomingMessageHandler incomingMessageHandler;

    @FXML
    private TextArea textArea_gameLog;


    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public void setup(Stage stage, Client client) {
        this.stage = stage;
        this.client = client;
    }

    public void changeToLoginWindowScene() throws IOException {
        FXMLLoader loginWindowLoader = new FXMLLoader(getClass().getResource("/loginWindow.fxml"));
        LoginWindowController lwc = new LoginWindowController(client);
        loginWindowLoader.setController(lwc);

        Parent loginWindowRoot = loginWindowLoader.load();
        Scene scene = new Scene(loginWindowRoot);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void changeToGameWindowScene() throws IOException {
        FXMLLoader gameWindowLoader = new FXMLLoader(getClass().getResource("/gameWindow.fxml"));
        GameWindowController gwc = new GameWindowController(client);
        gameWindowLoader.setController(gwc);

        Parent gameWindowRoot = gameWindowLoader.load();
        Scene scene = new Scene(gameWindowRoot);
        stage.setScene(scene);
    }

    public void renderToGameLog(String msg) {
        textArea_gameLog.appendText(msg);
    }
}
