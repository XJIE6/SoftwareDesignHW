package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Объект на карте,через который нельзя пройти
 */
public class Rock implements MapEntry {
    @Override
    public char getSymbol() {
        return 'R';
    }
}
