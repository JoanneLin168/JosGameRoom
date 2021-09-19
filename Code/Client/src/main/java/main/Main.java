package main;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Client client;
    private ClientMessageHandler messageHandler;
    private IncomingMessageHandler incomingMessageHandler;

    private void connectToServer() throws IOException {
        client = new Client();
        client.startConnection("127.0.0.1", 6666);
    }

    @Override
    public void start(Stage stage) throws IOException {
        connectToServer();

        StageManager.getInstance().setup(stage, client);
        StageManager.getInstance().changeToLoginWindowScene();

        messageHandler = new ClientMessageHandler(client);
        incomingMessageHandler = new IncomingMessageHandler(client, messageHandler);

        // TODO: make StageManager stuff work (can't do JFX stuff on a non JFX thread)
//        Thread incomingMessagesThread = new Thread(incomingMessageHandler);
//        incomingMessagesThread.start();

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