package main.ru.spbau.mit.kravchenkoyura.View;

import main.ru.spbau.mit.kravchenkoyura.GUIListener;
import main.ru.spbau.mit.kravchenkoyura.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
    private JTextPane textPane1;

    public View(GUIListener listener) {
        connectButton.addActionListener((ActionEvent event) -> {

            listener.connect(enterIPTextField.getText(), enterPortTextField.getText(), useGRPCCheckBox.isSelected());

        });
        waitForConnectionButton.addActionListener((ActionEvent event) -> {
            listener.connect(enterPortTextField1.getText(), useGRPCCheckBox.isSelected());

        });
        sendButton.addActionListener((ActionEvent event) -> listener.send(new Message(enterYourNameTextField.getText(), typeYourMessageHereTextField.getText())));
        disconnectButton.addActionListener((ActionEvent event) -> listener.disconnect());

    }

    public void onReceive(Message message) {
        String text = textPane1.getText();
        textPane1.setText(text + "\n" + message.getName() + " : " + message.getMessage());
    }

    @Override
    public JPanel get() {
        return panel1;
    }


}
