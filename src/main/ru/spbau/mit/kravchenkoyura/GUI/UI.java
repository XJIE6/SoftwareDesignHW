package main.ru.spbau.mit.kravchenkoyura.GUI;

import main.ru.spbau.mit.kravchenkoyura.Control.Message;

/**
 * Created by YuryKravchenko on 22/12/2016.
 */
public interface UI {
    public void onReceive(Message message);

    public void onDisconnect();

    public void onInfo(String message);
}
