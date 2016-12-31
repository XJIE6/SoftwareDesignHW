package ru.spbau.mit.kravchenkoyura.Network;

import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.kravchenkoyura.Control.Message;
import ru.spbau.mit.kravchenkoyura.Control.NetworkListener;

import static org.junit.Assert.*;

/**
 * Created by YuryKravchenko on 28/12/2016.
 */
public class GRPCConnectionTest {

    @Test
    public void testGRPCConnection() throws Exception {
        GRPCConnection grpcConnection1 = new GRPCConnection();
        GRPCConnection grpcConnection2 = new GRPCConnection();
        Message msg1 = new Message("Yura", "Hello!");
        grpcConnection1.start(new NetworkListener() {
            @Override
            public void onReceive(Message message) {
                assertEquals(message.getMessage(), msg1.getMessage());
                assertEquals(message.getName(), msg1.getName());
            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {
                Assert.fail();
            }
        });
        Message msg2 = new Message("Yury", "Hi!");
        grpcConnection2.start(new NetworkListener() {
            @Override
            public void onReceive(Message message) {
                assertEquals(message.getMessage(), msg2.getMessage());
                assertEquals(message.getName(), msg2.getName());
            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {
                Assert.fail();
            }
        });
        grpcConnection1.wait("1234");
        grpcConnection2.connect("localhost", "1234");
        Thread.sleep(1000);
        grpcConnection2.send(msg1);
        grpcConnection1.send(msg2);
        grpcConnection1.disconnect();
    }
}