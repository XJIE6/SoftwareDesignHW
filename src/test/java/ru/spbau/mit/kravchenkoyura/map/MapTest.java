package ru.spbau.mit.kravchenkoyura.map;

import org.junit.Test;
import ru.spbau.mit.kravchenkoyura.characters.Rock;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;


/**
 * Created by YuryKravchenko on 28/12/2016.
 */
public class MapTest {

    @Test
    public void testDelete() throws Exception {
        Map map = new Map(30);
        Rock r = new Rock();
        map.set(10, 10, r);
        map.delete(r);
        assertTrue(map.isEmpty(10, 10));
    }

    @Test
    public void testMove() throws Exception {
        Map map = new Map(30);
        Rock r1 = new Rock();
        Rock r2 = new Rock();
        map.set(10, 10, r1);
        map.set(10, 11, r2);
        assertEquals(map.move(r1, Map.Move.RIGHT), r2);
        assertEquals(map.move(r1, Map.Move.LEFT), Map.EMPTY_FIELD);
    }

    @Test
    public void testIsEmpty() throws Exception {
        Map map = new Map(30);
        assertTrue(map.isEmpty(10, 10));
        Rock r = new Rock();
        map.set(10, 10, r);
        map.move(r, Map.Move.RIGHT);
        assertTrue(map.isEmpty(10, 10));
    }

    @Test
    public void testSet() throws Exception {
        Map map = new Map(30);
        Rock r = new Rock();
        map.set(10, 10, r);
        assertFalse(map.isEmpty(10, 10));
    }
    
}