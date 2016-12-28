package ru.spbau.mit.kravchenkoyura.characters;

import ru.spbau.mit.kravchenkoyura.characters.Item;
import ru.spbau.mit.kravchenkoyura.characters.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by YuryKravchenko on 28/12/2016.
 */
public class PlayerTest {

    @Test
    public void testEquip() throws Exception {
        Player player = new Player(3, 10);
        assertEquals(player.getEquipped().size(), 0);
        assertEquals(player.getInventory().size(), 0);
        player.add(new Item(1, 1));
        player.add(new Item(2, 2));
        player.add(new Item(3, 3));
        assertEquals(player.getInventory().size(), 3);
        player.equip(0);
        assertEquals(player.getEquipped().size(), 1);
        assertEquals(player.getInventory().size(), 2);
        player.equip(0);
        assertEquals(player.getEquipped().size(), 2);
        assertEquals(player.getInventory().size(), 1);
        player.equip(0);
        assertEquals(player.getEquipped().size(), 2);
        assertEquals(player.getInventory().size(), 1);
        player.unequip(0);
        assertEquals(player.getEquipped().size(), 1);
        assertEquals(player.getInventory().size(), 2);
        player.unequip(0);
        assertEquals(player.getEquipped().size(), 0);
        assertEquals(player.getInventory().size(), 3);
    }

    @Test
    public void testGetAttackGetHp() throws Exception {
        Player player = new Player(3, 10);
        assertEquals(player.getAttack(), 3);
        player.add(new Item(1, 1));
        player.equip(0);
        assertEquals(player.getAttack(), 4);
        player.unequip(0);
        assertEquals(player.getAttack(), 3);
    }

    @Test
    public void testGetHp() throws Exception {
        Player player = new Player(3, 10);
        assertEquals(player.getHp(), 10);
        player.add(new Item(1, 1));
        player.equip(0);
        assertEquals(player.getHp(), 11);
        player.unequip(0);
        assertEquals(player.getHp(), 10);
    }

}