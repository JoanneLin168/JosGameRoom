import main.Client;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTests {
    @Test
    public void test_clientGreetsServer() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("hello server");
        String resp4 = client.sendMessage(".");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("hello client", resp3.substring(0, 12));
        assertEquals("bye", resp4);

        client.stopConnection();
    }

    @Test
    public void test_multipleClientsGreetServer() throws IOException {
        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 6666);
        Client client2 = new Client();
        client2.startConnection("127.0.0.1", 6666);

        String msg1 = client1.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client1.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");

        client1.stopConnection();
        client2.stopConnection();
    }
}
