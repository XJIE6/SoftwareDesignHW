package ru.spbau.mit.kravchenkoyura.Network;

import ru.spbau.mit.kravchenkoyura.Control.Message;
import ru.spbau.mit.kravchenkoyura.Control.NetworkListener;

/**
 * Интерфейс, котый должны выполнять соединения для их обработки
 */
public interface Network {
    /**
     * Запуск
     */
    public void start(NetworkListener listener);

    /**
     * Отключиться
     */
    public void disconnect();

    /**
     * Подключиться по заданному ip и порту
     */
    public void connect(String ip, String port);

    /**
     * Ожиать подключения по заданному порту
     */
    public void wait(String port);

    /**
     * Отправить сообщение
     */
    public void send(Message message);
}
