package main.ru.spbau.mit.kravchenkoyura.Control;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public interface GUIListener {
    void send(Message message);
    void connect(String ip, String port, boolean useGRPC);
    void connect(String text, boolean useGRPC);
    void disconnect();
}
