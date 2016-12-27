package ru.spbau.mit.kravchenkoyura;

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
}
