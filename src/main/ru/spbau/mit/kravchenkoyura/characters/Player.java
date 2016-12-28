package main.ru.spbau.mit.kravchenkoyura.characters;

import main.ru.spbau.mit.kravchenkoyura.Game;
import main.ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Player extends Game.HasParams implements MapEntry {
    private static final int MAX_EQUIPED = 2;
    private List<Item> equipped = new ArrayList<Item>();
    private List<Item> inventory = new ArrayList<Item>();

    void add(Item item) {
        inventory.add(item);
    }

    void equip(int number) {
        if (equipped.size() < MAX_EQUIPED) {
            if (number >= 0 && number < inventory.size()) {
                equipped.add(inventory.get(number));
                inventory.remove(number);
            }
        }
    }

    void unequip(int number) {
        if (number >= 0 && number < equipped.size()) {
            inventory.add(equipped.get(number));
            equipped.remove(number);
        }
    }

    @Override
    public char getSymbol() {
        return 'P';
    }

    @Override
    public int getAttack() {
        return super.getAttack() + equipped.stream().mapToInt(Game.HasParams::getAttack).sum();
    }

    @Override
    public int getHp() {
        return super.getHp() + equipped.stream().mapToInt(Game.HasParams::getHp).sum();
    }

    public List<Item> getEquipped() {
        return equipped;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public boolean isDead() {
        return getHp() <= 0;
    }
}
