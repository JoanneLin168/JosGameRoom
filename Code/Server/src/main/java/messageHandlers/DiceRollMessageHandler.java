package messageHandlers;

import main.Packet;
import main.PacketType;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DiceRollMessageHandler extends BaseMessageHandler {

    public DiceRollMessageHandler() {
        this.packetType = PacketType.DICE_ROLL;
    }

    @Override
    public void transmit(Packet packet, PrintWriter out, BufferedReader in) {
        out.println(this.packetType+":"+packet.getMsg());
    }

    @Override
    public void receive(Packet packet, PrintWriter out, BufferedReader in) {
        transmit(packet, out, in);
    }
}
