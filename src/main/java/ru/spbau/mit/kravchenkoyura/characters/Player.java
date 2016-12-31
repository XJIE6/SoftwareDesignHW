package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Игрок
 */
public class Player extends HasParams implements MapEntry {
    private static final int MAX_EQUIPPED = 2;
    /**
     * Список надетых вещей и вещей в инвентаре
     */
    private List<Item> equipped = new ArrayList<Item>();
    private List<Item> inventory = new ArrayList<Item>();

    public Player(int attack, int hp) {
        super(attack, hp);
    }

    /**
     * Подобрать вещь
     */
    public void add(Item item) {
        inventory.add(item);
    }

    /**
     * Одеть вещь
     * @param number - номер вещи
     */
    public void equip(int number) {
        if (equipped.size() < MAX_EQUIPPED) {
            if (number >= 0 && number < inventory.size()) {
                equipped.add(inventory.get(number));
                inventory.remove(number);
            }
        }
    }

    /**
     * Снять вещь
     * @param number номер вещи
     */
    public void unequip(int number) {
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
        return super.getAttack() + equipped.stream().mapToInt(HasParams::getAttack).sum();
    }

    @Override
    public int getHp() {
        return super.getHp() + equipped.stream().mapToInt(HasParams::getHp).sum();
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
