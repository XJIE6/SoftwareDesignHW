package ru.spbau.mit.kravchenkoyura.Control;

/**
 * Интерфейс, который предоставляется сети для информирования месенджера
 */
public interface NetworkListener {
    /**
     * Вызывается, когда пришло сообщение
     */
    public void onReceive(Message message);

    /**
     * Вызывается, когда сеть отключилась
     */
    public void onDisconnect();

    /**
     * Вызывается, когда произошла ошибка
     */
    public void onError(String error);
}
