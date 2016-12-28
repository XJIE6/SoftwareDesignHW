package ru.spbau.mit.kravchenkoyura.Network;

import org.junit.Test;
import ru.spbau.mit.kravchenkoyura.Control.Message;
import ru.spbau.mit.kravchenkoyura.Control.NetworkListener;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by YuryKravchenko on 28/12/2016.
 */
public class SocketConnectionTest {

    @Test
    public void testSocketConnection() throws Exception {
        SocketConnection socketConnection1 = new SocketConnection();
        SocketConnection socketConnection2 = new SocketConnection();
        Message msg1 = new Message("Yura", "Hello!");
        socketConnection1.start(new NetworkListener() {
            @Override
            public void onReceive(Message message) {
                assertEquals(message, msg1);
            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {
                assertTrue(false);
            }
        });
        Message msg2 = new Message("Yury", "Hi!");
        socketConnection2.start(new NetworkListener() {
            @Override
            public void onReceive(Message message) {
                assertEquals(message, msg2);
            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {
                assertTrue(false);
            }
        });

        socketConnection1.wait(1234);
        socketConnection2.connect("localhost", "1234");
        socketConnection1.send(msg2);
        socketConnection2.send(msg1);
        socketConnection1.disconnect();
    }

}