package ru.spbau.mit.kravchenkoyura.characters;

/**
 * Общий класс для того, у чего есть параметры (атака и хп)
 */
public abstract class HasParams {
    private int attack;
    private int hp;

    public HasParams(int attack, int hp) {
        this.attack = attack;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public void dealDMG(int n) {
        hp = hp - n;
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
