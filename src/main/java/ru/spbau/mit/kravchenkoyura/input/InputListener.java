package ru.spbau.mit.kravchenkoyura.input;

import ru.spbau.mit.kravchenkoyura.map.Map;

/**
 * Этот интерфейс обрабатывает сигналы от класса Input
 */
public interface InputListener {
    /**
     * Передвинуть игрока
     * @param move - направление
     */
    public void move(Map.Move move);
    /**
     * Отобразить список вещей
     */
    public void itemList();

    /**
     * Одеть вешь
     * @param n - номер вещи
     */
    public void equip(int n);

    /**
     * Cнять вешь
     * @param n - номер вещи
     */
    public void unequip(int n);
}
