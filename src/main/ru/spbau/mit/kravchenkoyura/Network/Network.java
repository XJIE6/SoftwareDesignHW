package main.ru.spbau.mit.kravchenkoyura.Network;

import main.ru.spbau.mit.kravchenkoyura.Control.Message;
import main.ru.spbau.mit.kravchenkoyura.Control.NetworkListener;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public interface Network {
    public void start(NetworkListener listener);
    public void disconnect();
    public void connect(String ip, String port);
    public void wait(String port);
    public void send(Message message);
}
