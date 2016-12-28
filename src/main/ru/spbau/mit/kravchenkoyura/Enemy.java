package main.ru.spbau.mit.kravchenkoyura;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Enemy extends Game.HasParams implements MapEntry {
    @Override
    public char getSymbol() {
        return 'E';
    }
}
