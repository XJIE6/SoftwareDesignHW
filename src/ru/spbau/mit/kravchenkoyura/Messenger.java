package ru.spbau.mit.kravchenkoyura;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Created by YuryKravchenko on 06/12/2016.
 */
public class Messenger extends Network {

    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Messenger(Consumer<String> onReceive) {
        super(onReceive);
    }

    @Override
    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                //do nothing
            }
        }
        socket = null;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }

    @Override
    public void connect(String ip, String port) throws IOException {
        disconnect();
        socket = new Socket(ip, Integer.valueOf(port));
    }

    @Override
    public void wait(String port) throws IOException {
        disconnect();
        socket = new ServerSocket().accept();
    }

    @Override
    public void send(String message, String name) {

    }

    private void listen() {
        while (true) {
            try {
                socket.getInputStream().read()
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
