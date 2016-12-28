package main.ru.spbau.mit.kravchenkoyura.characters;

/**
 * Created by YuryKravchenko on 28/12/2016.
 */
public abstract class HasParams {
    private int attack;
    private int hp;

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public void getDMG(int n) {
        hp = hp - n;
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
