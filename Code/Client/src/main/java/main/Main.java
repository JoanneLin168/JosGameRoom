package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Client client;

    private void connectToServer() throws IOException {
        client = new Client();
        client.startConnection("127.0.0.1", 6666);
    }

    @Override
    public void start(Stage stage) throws IOException {
        connectToServer();

        Parent root = FXMLLoader.load(getClass().getResource("/gameWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        client.stopConnection();
        System.out.println("Disconnected from server");
    }

    public static void main(String[] args) {
        launch();
    }

}