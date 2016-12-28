package main.ru.spbau.mit.kravchenkoyura;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public interface InputListener {
    public void quit();
    public void move(Map.Move move);
    public void itemList();
    public void equip(int n);
    public void unequip(int n);
}
