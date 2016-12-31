package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Вешь. Игрок может их надевать и снимать
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
