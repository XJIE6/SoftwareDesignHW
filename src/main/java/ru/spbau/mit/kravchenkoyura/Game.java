package ru.spbau.mit.kravchenkoyura;

import ru.spbau.mit.kravchenkoyura.input.Input;
import ru.spbau.mit.kravchenkoyura.input.InputListener;
import ru.spbau.mit.kravchenkoyura.characters.*;
import ru.spbau.mit.kravchenkoyura.map.Map;
import ru.spbau.mit.kravchenkoyura.map.MapEntry;
import ru.spbau.mit.kravchenkoyura.printer.Printer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Главный класс
 */
public class Game implements InputListener {
    private Map map;
    private Player player;
    private Printer printer;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> toRemove;
    private static final int GAME_SIZE = 15;
    private boolean ENDED = false;


    @Override
    public void move(Map.Move move) {
        if (ENDED) {
            return;
        }
        MapEntry result = map.move(player, move);
        if (!(result instanceof EmptyField || result instanceof Rock)) {
            if (result instanceof Item) {
                map.delete(result);
                player.add((Item) result);
            }
            if (result instanceof Enemy) {
                fight((Enemy) result);
            }
        }
        for(Enemy enemy : toRemove) {
            enemies.remove(enemy);
        }
        turn();
        if (ENDED) {
            printer.print();
            return;
        }
        print();
    }

    private void print(){
        printer.print(map.getMap());
        printer.print(player.getHp(), player.getAttack());
    }

    /**
     * Обрабатывает обмен ударами игрока и врага
     */

    private void fight(Enemy enemy) {
        player.dealDMG(enemy.getAttack());
        enemy.dealDMG(player.getAttack());
        if (enemy.isDead()) {
            map.delete(enemy);
            toRemove.add(enemy);
        }
        if (player.isDead()) {
            map.delete(player);
            ENDED = true;
        }
    }

    /**
     * Двигает врагов
     */
    private void turn() {
        for (Enemy enemy: enemies) {
            MapEntry result = map.move(enemy, enemy.move());
            if (result.equals(player)) {
                fight(enemy);
            }
        }
    }

    @Override
    public void itemList() {
        printer.print(player.getEquipped(), player.getInventory());
    }

    @Override
    public void equip(int number) {
        player.equip(number);
    }

    @Override
    public void unequip(int number) {
        player.unequip(number);
    }

    Game() {
        map = new Map(GAME_SIZE);
        player = new Player(2, 20);
        printer = new Printer();
        enemies = new ArrayList<Enemy>();
        toRemove = new ArrayList<Enemy>();
        generateMap();
        print();
    }

    private void generateMap() {
        Random random = new Random();
        int rockCount = random.nextInt(GAME_SIZE) + GAME_SIZE / 2;
        for (int i = 0; i < rockCount; ++i) {
            map.set(random.nextInt(GAME_SIZE - 2) + 1, random.nextInt(GAME_SIZE - 2) + 1, Map.ROCK);
        }
        int itemCount = random.nextInt(GAME_SIZE) + GAME_SIZE / 2;
        for (int i = 0; i < itemCount; ++i) {
            map.set(random.nextInt(GAME_SIZE - 2) + 1, random.nextInt(GAME_SIZE - 2) + 1, new Item(random.nextInt(3) + 1, random.nextInt(3) + 1));
        }
        int enemyCount = random.nextInt(GAME_SIZE) + GAME_SIZE / 2;
        for (int i = 0; i < enemyCount; ++i) {
            int x, y;
            while (true) {
                x = random.nextInt(GAME_SIZE - 2) + 1;
                y = random.nextInt(GAME_SIZE - 2) + 1;
                if (map.isEmpty(x, y)) {
                    break;
                }
            }
            Enemy enemy =  new Enemy(random.nextInt(5) + 1, random.nextInt(10) + 1);
            map.set(x, y, enemy);
            enemies.add(enemy);
        }
        map.set(GAME_SIZE / 2, GAME_SIZE / 2, player);
    }

    public static void main(String[] args) {
        Game game = new Game();
        new Input(game).start();
    }

}
