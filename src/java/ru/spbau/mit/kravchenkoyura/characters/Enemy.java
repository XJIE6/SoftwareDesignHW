package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.Map;
import ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.Random;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Enemy extends HasParams implements MapEntry {
    public Enemy(int attack, int hp) {
        super(attack, hp);
    }
    //случайное движение
    public Map.Move move() {
        return Map.Move.values()[new Random().nextInt(Map.Move.values().length)];
    }
    @Override
    public char getSymbol() {
        return 'E';
    }
}
