package main.ru.spbau.mit.kravchenkoyura.characters;

import main.ru.spbau.mit.kravchenkoyura.Game;
import main.ru.spbau.mit.kravchenkoyura.map.Map;
import main.ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.Random;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Enemy extends Game.HasParams implements MapEntry {
    public Map.Move move() {
        return Map.Move.values()[new Random().nextInt(Map.Move.values().length)];
    }
    @Override
    public char getSymbol() {
        return 'E';
    }
}
