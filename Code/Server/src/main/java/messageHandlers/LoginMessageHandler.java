package messageHandlers;

import main.Packet;
import main.PacketType;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;

public class LoginMessageHandler extends BaseMessageHandler{

    private Map<Integer, String> clientNamesMap;

    public LoginMessageHandler(Map<Integer, String> clientNamesMap) {
        this.packetType = PacketType.LOGIN;
        this.clientNamesMap = clientNamesMap;
    }

    @Override
    public void transmit(Packet packet, PrintWriter out, BufferedReader in) {
        // transmit message back to client to change window
        out.println(this.packetType+":"+packet.getMsg());
    }

    @Override
    public void receive(Packet packet, PrintWriter out, BufferedReader in) {
        this.clientNamesMap.put(packet.getId(), packet.getMsg());
        transmit(packet, out, in);
    }
}
