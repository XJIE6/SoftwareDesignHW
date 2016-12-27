package main.ru.spbau.mit.kravchenkoyura.Control;

import main.ru.spbau.mit.kravchenkoyura.GUI.UI;
import main.ru.spbau.mit.kravchenkoyura.Network.GRPCConnection;
import main.ru.spbau.mit.kravchenkoyura.Network.Network;
import main.ru.spbau.mit.kravchenkoyura.Network.SocketConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by YuryKravchenko on 06/12/2016.
 */
public class Messenger implements GUIListener, NetworkListener {
    private Network network;
    private UI ui;
    private final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);

    @Override
    public void send(Message message) {
        LOGGER.info("Sending message.");
        network.send(message);
    }

    @Override
    public void connect(String ip, String port, boolean useGRPC) {
        LOGGER.info("Connecting.");
        if (useGRPC) {
            network = new GRPCConnection();
        }
        else {
            network = new SocketConnection();
        }
        network.start(this);
        network.connect(ip, port);

    }

    @Override
    public void connect(String port, boolean useGRPC) {
        LOGGER.info("Waiting for connection.");
        if (useGRPC) {
            network = new GRPCConnection();
        }
        else {
            network = new SocketConnection();
        }
        network.start(this);
        network.wait(port);

    }

    @Override
    public void disconnect() {
        LOGGER.info("Disconnect.");
        if (network == null) {
            return;
        }
        network.disconnect();
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }

    @Override
    public void onReceive(Message message) {
        ui.onReceive(message);
    }

    @Override
    public void onDisconnect() {
        ui.onDisconnect();
    }

    @Override
    public void onError(String error) {
        ui.onInfo(error);
    }
}
