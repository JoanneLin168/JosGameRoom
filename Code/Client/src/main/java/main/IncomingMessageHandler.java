package main;

import main.Client;
import main.ClientMessageHandler;

/**
 * IncomingMessageHandler - Runnable to handle incoming messages from the server
 */
public class IncomingMessageHandler implements Runnable {

    private final Client client;
    private final ClientMessageHandler messageHandler;

    public IncomingMessageHandler(Client client, ClientMessageHandler messageHandler) {
        this.client = client;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        boolean isActive = true;

        System.out.println("Background thread active for incoming message handler");

        while(isActive) {
            try {
                if (this.client.isReady()) {
                    String resp = this.client.receiveMessage();
                    this.messageHandler.handleMessage(resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                isActive = false;
            }
        }

        System.out.println("Background thread not active for incoming message handler");
    }
}
