package ru.spbau.mit.kravchenkoyura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.function.Supplier;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public class View implements Supplier<JPanel> {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton waitForConnectionButton;
    private JTextField enterIPTextField;
    private JTextField enterPortTextField;
    private JButton connectButton;
    private JList list1;
    private JTextField typeYourMessageHereTextField;
    private JTextField enterYourNameTextField;
    private JButton disconnectButton;
    private JButton sendButton;
    private JCheckBox useGRPCCheckBox;
    private JTextArea helloTextArea;
    private JTextField enterPortTextField1;

    public View(Network network) {
        connectButton.addActionListener((ActionEvent event) -> {
            try {
                network.connect(enterIPTextField.getText(), enterPortTextField.getText());
            } catch (IOException e) {
                helloTextArea.setText("Connection failed");
            }
        });
        waitForConnectionButton.addActionListener((ActionEvent event) -> {
            try {
                network.wait(enterPortTextField1.getText());
            } catch (IOException e) {
                helloTextArea.setText("Connection failed");
            }
        });
        sendButton.addActionListener((ActionEvent event) -> network.send(typeYourMessageHereTextField.getText(), enterYourNameTextField.getText()));
        disconnectButton.addActionListener((ActionEvent event) -> network.disconnect());

    }

    @Override
    public JPanel get() {
        return panel1;
    }
}
