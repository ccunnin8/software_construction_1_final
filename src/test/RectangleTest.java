package test;

import model.Drawing;
import model.Rectangle;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class RectangleTest extends DrawingTest {

    @Before
    public void setUp() {
        MidiSynth synth = new MidiSynth();
        synth.open();
        testDrawing = new Drawing();
        shape1 = new Rectangle(new Point(1, 1), synth);
        shape2 = new Rectangle(1, 3 , 5, 10);
    }

    @Test
    public void testGetShapesAtPoint() {
        shape1.setBounds(new Point(4, 2));
        testDrawing.addShape(shape1);
        assertEquals(shape1, testDrawing.getShapesAtPoint(new Point(1, 1)));
        assertEquals(null, testDrawing.getShapesAtPoint(new Point(1, 4)));
    }
}
