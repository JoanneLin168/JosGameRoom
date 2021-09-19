package main;

public class Packet {
    private int id;
    private PacketType packetType;
    private String msg;

    public Packet(int id, String inputLine) {
        // Splits inputLine into packetType and message
        String[] packet = inputLine.split(":");
        this.packetType = PacketType.valueOf(packet[0]);
        this.msg = packet[1];
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public String getMsg() {
        return msg;
    }
}
