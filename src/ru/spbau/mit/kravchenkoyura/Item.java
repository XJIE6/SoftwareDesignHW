package ru.spbau.mit.kravchenkoyura;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Item extends Game.HasParams implements MapEntry{

    @Override
    public char getSymbol() {
        return 'I';
    }
}
