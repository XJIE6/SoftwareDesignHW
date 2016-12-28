package main.ru.spbau.mit.kravchenkoyura.Input;

import main.ru.spbau.mit.kravchenkoyura.Map;

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
                if (s.length() == 0) {
                    continue;
                }
                int val;
                switch (s.charAt(0)) {
                    case('w'):
                        listener.move(Map.Move.UP);
                        break;
                    case('a'):
                        listener.move(Map.Move.LEFT);
                        break;
                    case('s'):
                        listener.move(Map.Move.DOWN);
                        break;
                    case('d'):
                        listener.move(Map.Move.RIGHT);
                        break;
                    case('i'):
                        listener.itemList();
                        break;
                    case('e'):
                        try {
                            val = Integer.parseInt(s.substring(2));
                        }
                        catch (Exception e) {
                            break;
                        }
                        listener.equip(val);
                        break;
                    case('u'):
                        try {
                            val = Integer.parseInt(s.substring(2));
                        }
                        catch (Exception e) {
                            break;
                        }
                        listener.unequip(val);
                        break;
                    case('q'):
                        listener.quit();
                        notEnded = false;
                        break;
                }
            }
        }).start();
    }
}
