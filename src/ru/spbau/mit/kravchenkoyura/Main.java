package ru.spbau.mit.kravchenkoyura;

import javax.swing.*;
import java.util.function.Supplier;

class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Messenger");
        Network messenger = new Messenger();
        Supplier<JPanel> view = new View();
        frame.setContentPane(view.get());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}