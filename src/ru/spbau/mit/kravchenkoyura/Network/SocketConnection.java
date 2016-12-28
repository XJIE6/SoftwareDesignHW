package ru.spbau.mit.kravchenkoyura.Network;

import ru.spbau.mit.kravchenkoyura.Control.Message;
import ru.spbau.mit.kravchenkoyura.Control.Messenger;
import ru.spbau.mit.kravchenkoyura.Control.NetworkListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public class SocketConnection implements Network {
    private Socket socket;
    private ServerSocket serverSocket;
    private NetworkListener listener;
    private final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);

    @Override
    public void start(NetworkListener listener) {
        this.listener = listener;
    }

    @Override
    public void disconnect() {
        LOGGER.info("Disconnecting.");
        if (socket != null) {
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {
                LOGGER.error("Socket closing failed.");
            }
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
                LOGGER.error("ServerSocket closing failed.");
            }
        }
    }

    @Override
    public void connect(String ip, String port) {
        LOGGER.info("Connecting.");
        socket = new java.net.Socket();
        try {
            socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)));
            runListener();
        } catch (IOException e) {
            LOGGER.error("Connection failed.");
            listener.onError("Connection failed.");
            listener.onDisconnect();
        }

    }

    @Override
    public void wait(String port) {
        LOGGER.info("Waiting for connection.");
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(Integer.parseInt(port));
                socket = serverSocket.accept();
                runListener();
            } catch (IOException | NumberFormatException e) {
                LOGGER.error("Connection failed.");
                listener.onError("Connection failed");
                listener.onDisconnect();
            }

        }).start();
    }

    @Override
    public void send(Message message) {
        LOGGER.info("Sending message.");
        try {
            new DataOutputStream(socket.getOutputStream()).writeUTF(message.getName());
            new DataOutputStream(socket.getOutputStream()).writeUTF(message.getMessage());
        } catch (IOException e) {
            LOGGER.error("Sending failed.");
            listener.onError("Sending failed.");
        }
    }

    private void runListener() {
        new Thread(() -> {
            try {
                while (true) {
                    String name = new DataInputStream(socket.getInputStream()).readUTF();
                    String mesage = new DataInputStream(socket.getInputStream()).readUTF();
                    listener.onReceive(new Message(name, mesage));
                }
            } catch (IOException e) {
                listener.onDisconnect();
            }
        }).start();
    }
}
