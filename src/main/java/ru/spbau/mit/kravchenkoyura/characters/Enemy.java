package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.map.Map;
import ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.Random;

/**
 * Враг. Игрок должен сражаться с ними
 */
public class Enemy extends HasParams implements MapEntry {
    public Enemy(int attack, int hp) {
        super(attack, hp);
    }

    /**
     * Метод возвращает направление, куда хочет передвинуться враг
     */
    public Map.Move move() {
        return Map.Move.values()[new Random().nextInt(Map.Move.values().length)];
    }
    @Override
    public char getSymbol() {
        return 'E';
    }
}
