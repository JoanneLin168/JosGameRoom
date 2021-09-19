import main.Client;
import main.PacketType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTests {
    @Test
    public void test_oneClientJoinsServer() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        client.sendMessage(PacketType.DICE_ROLL+":"+1);
        String resp1 = client.receiveMessage();

        assertEquals(PacketType.DICE_ROLL+":"+1, resp1);

        client.stopConnection();
    }

    @Test
    public void test_multipleClientsJoinServer() throws IOException {
        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 6666);
        Client client2 = new Client();
        client2.startConnection("127.0.0.1", 6666);

        client1.sendMessage(PacketType.DICE_ROLL+":"+1);
        String msg1 = client1.receiveMessage();
        client2.sendMessage(PacketType.DICE_ROLL+":"+2);
        String msg2 = client2.receiveMessage();

        assertEquals(PacketType.DICE_ROLL+":"+1, msg1);
        assertEquals(PacketType.DICE_ROLL+":"+2, msg2);

        client1.stopConnection();
        client2.stopConnection();
    }
}
