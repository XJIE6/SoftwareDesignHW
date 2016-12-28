package ru.spbau.mit.kravchenkoyura.printer;

import ru.spbau.mit.kravchenkoyura.characters.Item;
import ru.spbau.mit.kravchenkoyura.characters.Player;
import ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.List;
/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Printer {
    private static final int VISION = 2;
    private static final char HIDE_SYMBOL = '#';
    public void print(MapEntry[][] map) {
        int x = 0, y = 0;
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j] instanceof Player) {
                    x = i;
                    y = j;
                }
            }
        }
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (Math.max(Math.abs(i - x), Math.abs(j - y)) > VISION) {
                    System.out.print(HIDE_SYMBOL);
                }
                else {
                    System.out.print(map[i][j].getSymbol());
                }
            }
            System.out.println();
        }
    }
    public void print(int hp, int attack) {
        System.out.printf("HP: %d    DMG: %d\n", hp, attack);
    }
    public void print(List<Item> equipped, List<Item> inventory) {
        System.out.println("Equipped\n     №    HP   DMG");
        for (int i = 0; i < equipped.size(); ++i) {
            System.out.printf("%6d%6d%6d\n", i, equipped.get(i).getHp(), equipped.get(i).getAttack());
        }
        System.out.println("Inventory\n     №    HP   DMG");
        for (int i = 0; i < inventory.size(); ++i) {
            System.out.printf("%6d%6d%6d\n", i, inventory.get(i).getHp(), inventory.get(i).getAttack());
        }
    }
    public void print() {
        System.out.println("Game Over! Press 'q' to exit");
    }
}
