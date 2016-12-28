package main.ru.spbau.mit.kravchenkoyura;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public class Game implements InputListener{
    private Map map;
    private Player player;

    @Override
    public void quit() {

    }

    @Override
    public void move(Map.Move move) {
        MapEntry result = map.move(player, move);
        if (result != null) {
            if (result instanceof Item) {
                map.delete(result);
                player.add((Item) result);
            }
            if (result instanceof Enemy) {
                Enemy enemy = (Enemy) result;
                player.getDMG(enemy.getAttack());
                enemy.getDMG(player.getAttack());
                if (enemy.isDead()) {
                    map.delete(enemy);
                }
                if (player.isDead()) {
                    map.delete(player);
                    quit();
                }
            }
        }
        turn();
        
    }

    private void turn() {

    }

    @Override
    public void itemList() {

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
        map = new Map(50);
        player = new Player();
    }

    public abstract static class HasParams {
        private int attack;
        private int hp;

        protected int getHp() {
            return hp;
        }

        protected void setHp(int hp) {
            this.hp = hp;
        }

        protected int getAttack() {
            return attack;
        }

        protected void setAttack(int attack) {
            this.attack = attack;
        }

        protected void getDMG(int n) {
            hp = hp - n;
        }

        boolean isDead() {
            return hp <= 0;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        new Input(game).start();
    }

}
