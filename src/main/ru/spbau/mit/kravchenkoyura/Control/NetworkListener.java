package main.ru.spbau.mit.kravchenkoyura.Control;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public interface NetworkListener {
    public void onReceive(Message message);
    public void onDisconnect();
    public void onError(String error);
}
