package test;

import model.Drawing;
import model.Oval;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class OvalTest extends DrawingTest {

    @Before
    public void setUp() {
        MidiSynth synth = new MidiSynth();
        synth.open();
        testDrawing = new Drawing();
        shape1 = new Oval(new Point(1, 1), synth);
        shape2 = new Oval(1, 3 , 5, 10);
    }

    //This test doesn't really test whether or not a point is in the oval but 'that is outside the scope of this course'
    @Test
    public void testGetShapesAtPoint() {
        shape1.setBounds(new Point(4, 2));
        testDrawing.addShape(shape1);
        assertEquals(null, testDrawing.getShapesAtPoint(new Point(5, 3)));
        assertEquals(null, testDrawing.getShapesAtPoint(new Point(1, 4)));
    }
}
