package ru.spbau.mit.kravchenkoyura;

import java.util.ArrayList;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Player extends Game.HasParams implements MapEntry {
    {
        setAttack(1);
        setHp(10);
    }
    private static final int MAX_EQUIPED = 2;
    private ArrayList<Item> equiped = new ArrayList<Item>();
    private ArrayList<Item> backPack = new ArrayList<Item>();

    void add(Item item) {
        backPack.add(item);
    }

    void equip(int number) {
        if (equiped.size() < MAX_EQUIPED) {
            equiped.add(backPack.get(number));
            backPack.remove(number);
        }
    }

    void unequip(int number) {
        backPack.add(equiped.get(number));
        equiped.remove(number);
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}
