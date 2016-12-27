package ru.spbau.mit.kravchenkoyura;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Input {
    InputListener listener;
    public Input(InputListener listener) {
        this.listener = listener;
    }
    public void start() {
        new Thread(() -> {
            DataInputStream input = new DataInputStream(System.in);
            boolean notEnded = true;
            String s;
            while (notEnded) {
                try {
                    s = input.readLine();
                } catch (IOException e) {
                    s = "q";
                }
                switch (s.charAt(0)) {
                    case('w'):
                        listener.move(Map.Move.UP);
                    case('a'):
                        listener.move(Map.Move.LEFT);
                    case('s'):
                        listener.move(Map.Move.DOWN);
                    case('d'):
                        listener.move(Map.Move.RIGHT);
                    case('i'):
                        listener.itemList();
                    case('e'):
                        listener.equip(Integer.parseInt(s.substring(2)));
                    case('u'):
                        listener.unequip(Integer.parseInt(s.substring(2)));
                    case('q'):
                        listener.quit();
                        notEnded = false;
                }
            }
        }).start();
    }
}
