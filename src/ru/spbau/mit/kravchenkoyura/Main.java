package ru.spbau.mit.kravchenkoyura;

import ru.spbau.mit.kravchenkoyura.Control.Messenger;
import ru.spbau.mit.kravchenkoyura.GUI.View;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Messenger");
        Messenger messenger = new Messenger();
        View view = new View(messenger);
        messenger.setUI(view);
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}