package main.ru.spbau.mit.kravchenkoyura;

import java.util.Arrays;

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
                    return new Position(x, y + 1);
                case DOWN:
                    return new Position(x, y - 1);
                case LEFT:
                    return new Position(x - 1, y);
                case RIGHT:
                    return new Position(x + 1, y);
            }
            return null;
        }

        public void put(MapEntry elem) {
            Map.this.map[x][y] = elem;
        }
    }
    private static final EmptyField EMPTY_FIELD = new EmptyField();
    private static final Rock ROCK = new Rock();
    private MapEntry[][] map;
    Map(int n) {
        map = generate(n);
    }

    private MapEntry[][] generate(int n) {
        MapEntry[][] res = new MapEntry[n][n];
        MapEntry[] fill = new MapEntry[n];
        Arrays.fill(fill, new EmptyField());
        Arrays.fill(res, fill);
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
            return null;
        }
        if (field instanceof Rock) {
            return null;
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
}
