package main.ru.spbau.mit.kravchenkoyura.characters;

import main.ru.spbau.mit.kravchenkoyura.Game;
import main.ru.spbau.mit.kravchenkoyura.map.MapEntry;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Item extends Game.HasParams implements MapEntry {

    @Override
    public char getSymbol() {
        return 'I';
    }
}
