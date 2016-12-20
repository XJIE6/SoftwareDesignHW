package ru.spbau.mit.kravchenkoyura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Supplier;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public class View implements Supplier<JPanel> {
    public JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton waitForConnectionButton;
    private JTextField enterIPTextField;
    private JTextField enterPortTextField;
    private JButton connectButton;
    private JList list1;
    private JButton sendButton;
    private JTextField typeYourMessageHereTextField;
    private JTextField enterYourNameTextField;
    private JButton disconnectButton;

    public View() {
        disconnectButton.addActionListener((ActionEvent e) -> System.out.print("lol"));
    }

    @Override
    public JPanel get() {
        return panel1;
    }
}
