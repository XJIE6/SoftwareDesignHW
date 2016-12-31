package ru.spbau.mit.kravchenkoyura.GUI;

import ru.spbau.mit.kravchenkoyura.Control.Message;

/**
 * Интервейс, который должен выполнять GUI для работы с ним
 */
public interface UI {
    /**
     * Вызывается, когда пришло сообщение
     */
    public void onReceive(Message message);

    /**
     * Вызывается, когда сеть отключилась
     */
    public void onDisconnect();

    /**
     * Вызывается, огда пользователю нужно сообщить информацию
     */
    public void onInfo(String message);
}
