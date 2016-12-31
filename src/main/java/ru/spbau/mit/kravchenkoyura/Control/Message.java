package ru.spbau.mit.kravchenkoyura.Control;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public class Message {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Message(String name, String message) {

        this.name = name;
        this.message = message;
    }
}
