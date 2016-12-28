package main.ru.spbau.mit.kravchenkoyura.map;

import main.ru.spbau.mit.kravchenkoyura.EmptyField;
import main.ru.spbau.mit.kravchenkoyura.Rock;

/**
 * Created by YuryKravchenko on 27/12/2016.
 */
public class Map {
    public static enum Move {UP, DOWN, LEFT, RIGHT}
    private class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MapEntry get() {
            return Map.this.map[x][y];
        }

        public Position add(Move move) {
            switch(move) {
                case UP:
                    return new Position(x - 1, y);
                case DOWN:
                    return new Position(x + 1, y);
                case LEFT:
                    return new Position(x, y - 1);
                case RIGHT:
                    return new Position(x, y + 1);
            }
            return null;
        }

        public void put(MapEntry elem) {
            Map.this.map[x][y] = elem;
        }
    }
    public static final EmptyField EMPTY_FIELD = new EmptyField();
    public static final Rock ROCK = new Rock();
    private MapEntry[][] map;
    Map(int n) {
        map = generate(n);
    }

    private MapEntry[][] generate(int n) {
        MapEntry[][] res = new MapEntry[n][n];
        for (int i = 0; i < n; ++i) {
            res[0][i] = res[i][0] = res[n - 1][i] = res[i][n - 1] = ROCK;
        }
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                res[i][j] = EMPTY_FIELD;
            }
        }
//        MapEntry[] fill = new MapEntry[n];
//        Arrays.fill(fill, new EmptyField());
//        Arrays.fill(res, fill.clone());
        return res;
    }

    public void delete(MapEntry character) {
        find(character).put(EMPTY_FIELD);
    }

    public MapEntry move(MapEntry character, Move move) {
        Position pos = find(character);
        Position newPos = pos.add(move);
        MapEntry field = newPos.get();
        if (field instanceof EmptyField) {
            newPos.put(character);
            pos.put(field);
            return field;
        }
        if (field instanceof Rock) {
            return field;
        }
        return field;
    }

    private Position find(MapEntry character) {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j].equals(character)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    boolean isEmpty(int x, int y) {
        return new Position(x, y).get() == EMPTY_FIELD;
    }

    void set(int x, int y, MapEntry character) {
        new Position(x, y).put(character);
    }

    public MapEntry[][] getMap() {
        return map;
    }
}
