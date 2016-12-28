package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class EmptyField implements MapEntry {

    @Override
    public char getSymbol() {
        return '.';
    }
}
