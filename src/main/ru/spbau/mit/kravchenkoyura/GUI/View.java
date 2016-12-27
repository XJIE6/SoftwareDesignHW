package main.ru.spbau.mit.kravchenkoyura.GUI;

import main.ru.spbau.mit.kravchenkoyura.Control.GUIListener;
import main.ru.spbau.mit.kravchenkoyura.Control.Message;
import main.ru.spbau.mit.kravchenkoyura.Control.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.Timer;

/**
 * Created by YuryKravchenko on 07/12/2016.
 */
public class View implements UI{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton waitForConnectionButton;
    private JTextField enterIPTextField;
    private JTextField enterPortTextField;
    private JButton connectButton;
    private JTextField typeYourMessageHereTextField;
    private JTextField enterYourNameTextField;
    private JButton disconnectButton;
    private JButton sendButton;
    private JCheckBox useGRPCCheckBox;
    private JTextField enterPortTextField1;
    private JTextPane textPane1;
    private JTextPane helloTextPane;

    private java.util.Timer timer;

    private final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);
    
    private static final int UPDATETIME = 1000;

    public View(GUIListener listener) {
        setSetting(true);
        helloTextPane.setEnabled(false);
        textPane1.setEnabled(false);

        connectButton.addActionListener((ActionEvent event) -> {
            LOGGER.info("Connect clicked.");
            setSetting(false);
            listener.connect(enterIPTextField.getText(), enterPortTextField.getText(), useGRPCCheckBox.isSelected());
        });
        waitForConnectionButton.addActionListener((ActionEvent event) -> {
            LOGGER.info("Wait clicked.");
            setSetting(false);
            listener.connect(enterPortTextField1.getText(), useGRPCCheckBox.isSelected());
        });
        sendButton.addActionListener((ActionEvent event) -> {
            LOGGER.info("Send clicked.");
            Message message = new Message(enterYourNameTextField.getText(), typeYourMessageHereTextField.getText());
            if (!Objects.equals(message.getMessage(), "")) {
                typeYourMessageHereTextField.setText("");
                listener.send(message);
                onReceive(message);
            }
        });
        disconnectButton.addActionListener((ActionEvent event) -> {
            LOGGER.info("Disconnect clicked.");
            listener.disconnect();
            setSetting(true);
        });
        typeYourMessageHereTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            private void warn() {
                LOGGER.info("Typing started.");
                listener.send(new Message(enterYourNameTextField.getText(), ""));
            }
        });
    }

    public void onReceive(Message message) {
        if (Objects.equals(message.getMessage(), "")) {
            LOGGER.info("Got typing message from {}.", message.getName());
            onInfo(message.getName() + " typing...");
        }
        else {
            LOGGER.info("Got message \'{}\' from {}.", message.getMessage(), message.getName());
            String text = textPane1.getText();
            textPane1.setText(text + "\n" + message.getName() + " : " + message.getMessage());
        }
    }

    public void onDisconnect() {
        LOGGER.info("Got disconnect signal.");
        setSetting(true);
    }

    public void onInfo(String message) {
        LOGGER.info("Got info message {}.", message);
        if (timer != null) {
            timer.cancel();
        }
        helloTextPane.setText(message);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                helloTextPane.setText("");
            }
        }, UPDATETIME);
    }

    private void setSetting(boolean enable) {
        tabbedPane1.setEnabled(enable);
        waitForConnectionButton.setEnabled(enable);
        connectButton.setEnabled(enable);
        enterIPTextField.setEnabled(enable);
        enterPortTextField.setEnabled(enable);
        enterYourNameTextField.setEnabled(enable);
        useGRPCCheckBox.setEnabled(enable);
        enterPortTextField1.setEnabled(enable);

        typeYourMessageHereTextField.setEnabled(!enable);
        sendButton.setEnabled(!enable);
        disconnectButton.setEnabled(!enable);
    }

    public JPanel getPanel() {
        return panel1;
    }


}
