package main;

import controllers.GameWindowController;
import main.Client;
import main.PacketType;
import messageHandlers.BaseMessageHandler;
import messageHandlers.DiceRollMessageHandler;
import messageHandlers.LoginMessageHandler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ClientMessageHandler {
    private Map<PacketType, BaseMessageHandler> messageHandlersMap = new HashMap<>();
    private final Client client;

    ClientMessageHandler(Client client) {
        this.messageHandlersMap.put(PacketType.LOGIN, new LoginMessageHandler());
        this.messageHandlersMap.put(PacketType.DICE_ROLL, new DiceRollMessageHandler());
        this.client = client;
    }

    public void handleMessage(String inputLine) {
        // Splits inputLine into packetType and message
        String[] packet = inputLine.split(":");
        PacketType packetType = PacketType.valueOf(packet[0]);
        String msg = packet[1];

        BaseMessageHandler handler = messageHandlersMap.get(packetType);
        handler.receive(msg, client.getOut(), client.getIn());
    }
}