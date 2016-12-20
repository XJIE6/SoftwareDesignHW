package ru.spbau.mit.kravchenkoyura;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public abstract class Network {
    private Consumer<String> onReceive;
    public Network(Consumer<String> onReceive) {
        this.onReceive = onReceive;
    }
    public abstract void disconnect();
    public abstract void connect(String ip, String port) throws IOException;
    public abstract void wait(String port) throws IOException;
    public abstract void send(String message, String name);
}
