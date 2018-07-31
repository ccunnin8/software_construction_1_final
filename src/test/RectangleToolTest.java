package test;


import model.Rectangle;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static org.junit.Assert.*;

public class RectangleToolTest extends ShapeTest {



    @Before
    public void setUp() {
        synth = new MidiSynth();
        synth.open();
        shape1 = new Rectangle(new Point(1, 1), synth);
        shape2 = new Rectangle(1, 3 , 5, 10);
    }

    @Test
    public void testcontainsPoint() {
        Rectangle shape1 = new Rectangle(new Point(1,1),synth);
        shape1.setBounds(new Point(5, 5));
        assertTrue(shape1.contains(new Point(5, 5)));
        assertFalse(shape1.contains(new Point(0, 0)));
        assertTrue(shape1.contains(new Point(4, 4)));
        assertTrue(shape1.contains(new Point(4, 1)));
        assertTrue(shape1.contains(new Point(1, 1)));
        assertFalse(shape1.contains(new Point(5, 7)));
        assertFalse(shape1.contains(new Point(8, 8)));
        assertTrue(shape1.contains(new Point(1, 1)));
    }

}