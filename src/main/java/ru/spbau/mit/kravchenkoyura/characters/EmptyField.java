package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Пустое поле на карте
 */
public class EmptyField implements MapEntry {

    @Override
    public char getSymbol() {
        return '.';
    }
}
