package ru.spbau.mit.kravchenkoyura.Control;

/**
 * Интерфейс, предоставляемый GUI для информирования месенджера
 */
public interface GUIListener {
    /**
     * Вызывается, когда пользователь захотел отправить сообщение
     */
    void send(Message message);

    /**
     * Вызывается, когда пользователь решил подключиться к сети
     * @param useGRPC - нужно ли использовать GRPC
     */
    void connect(String ip, String port, boolean useGRPC);

    /**
     * Вызывается, когда пользователь хочет стать сервером о ожидать подключения
     *  @param useGRPC - нужно ли испольщовать GRPC
     */
    void connect(String text, boolean useGRPC);

    /**
     * Вызывается, когда пользователь решил отключаться
     */
    void disconnect();
}
