package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Item extends HasParams implements MapEntry {

    public Item(int attack, int hp) {
        super(attack, hp);
    }

    @Override
    public char getSymbol() {
        return 'I';
    }
}
