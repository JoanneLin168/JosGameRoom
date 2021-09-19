package main;

import messageHandlers.BaseMessageHandler;
import messageHandlers.DiceRollMessageHandler;
import messageHandlers.LoginMessageHandler;

import java.util.HashMap;
import java.util.Map;

public class ServerMessageHandler {
    private Map<PacketType, BaseMessageHandler> messageHandlersMap = new HashMap<>();
    private Map<Integer, IOPair> clientsIOMap = new HashMap<>();
    private Map<Integer, String> clientsNameMap = new HashMap<>();

    ServerMessageHandler() {
        this.messageHandlersMap.put(PacketType.LOGIN, new LoginMessageHandler(clientsNameMap));
        this.messageHandlersMap.put(PacketType.DICE_ROLL, new DiceRollMessageHandler());
    }

    public void addClientIOPair(int id, IOPair ioPair) {
        this.clientsIOMap.put(id, ioPair);
    }

    public void handleMessage(String inputLine, int id, boolean sendToAll) {
        Packet packet = new Packet(id, inputLine);

        BaseMessageHandler handler = messageHandlersMap.get(packet.getPacketType());

        if (sendToAll) {
            for (int clientId : clientsIOMap.keySet()) {
                IOPair clientIOPair = clientsIOMap.get(clientId);
                handler.receive(packet, clientIOPair.getOut(), clientIOPair.getIn());
            }
        }
        // if only to send back to the requesting client
        else {
            IOPair clientIOPair = clientsIOMap.get(id);
            handler.receive(packet, clientIOPair.getOut(), clientIOPair.getIn());
        }
    }
}
