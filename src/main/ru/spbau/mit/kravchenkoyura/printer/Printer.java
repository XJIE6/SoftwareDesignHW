package main.ru.spbau.mit.kravchenkoyura.printer;

import main.ru.spbau.mit.kravchenkoyura.characters.Item;
import main.ru.spbau.mit.kravchenkoyura.map.MapEntry;

import java.util.List;
/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Printer {
    public void print(MapEntry[][] map) {
        for (MapEntry[] aMap : map) {
            for (MapEntry anAMap : aMap) {
                System.out.print(anAMap.getSymbol());
            }
            System.out.println();
        }
    }
    public void print(int attack, int hp) {
        System.out.printf("DMG: %d    HP: %d", attack, hp);
    }
    public void print(List<Item> equipped, List<Item> inventory) {
        System.out.println("Equipped\n     №    HP   DMG");
        for (int i = 0; i < equipped.size(); ++i) {
            System.out.printf("%6d%6d%6d\n", i, equipped.get(i).getHp(), equipped.get(i).getHp());
        }
        System.out.println("Inventory\n     №    HP   DMG");
        for (int i = 0; i < inventory.size(); ++i) {
            System.out.printf("%6d%6d%6d\n", i, inventory.get(i).getHp(), inventory.get(i).getHp());
        }
    }
}
