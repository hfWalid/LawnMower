package org.mowitnow.lawnmower.domain;

import org.junit.jupiter.api.Test;
import org.mowitnow.lawnmower.domain.entity.Lawn;
import org.mowitnow.lawnmower.domain.entity.Mower;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LawnTest {

    @Test
    public void testLawnConstructor() {
        Lawn lawn = new Lawn(5, 5);
        assertEquals(5, lawn.getWidth());
        assertEquals(5, lawn.getHeight());
        assertEquals(0, lawn.getMowers().size());
    }

    @Test
    public void testAddMower() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(1, 2, 'N');
        lawn.addMower(mower);
        assertEquals(1, lawn.getMowers().size());
        assertEquals(mower, lawn.getMowers().get(0));
    }
}

