package test;

import model.Oval;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OvalToolTest extends ShapeTest {

    @Before
    public void setup(){
        synth = new MidiSynth();
        synth.open();
        shape1 = new Oval(new Point(1, 1), synth);
        shape2 = new Oval(1, 3 , 5, 10);

    }

    @Test
    public void testcontainsPoint() {
        Oval shape1 = new Oval(new Point(1,1),synth);
        shape1.setBounds(new Point(5, 5));
        assertFalse(shape1.contains(new Point(5, 5)));
        assertFalse(shape1.contains(new Point(0, 0)));
        assertTrue(shape1.contains(new Point(4, 4)));
        assertFalse(shape1.contains(new Point(4, 1)));
        assertFalse(shape1.contains(new Point(1, 1)));
        assertFalse(shape1.contains(new Point(5, 7)));
        assertFalse(shape1.contains(new Point(8, 8)));
        assertFalse(shape1.contains(new Point(1, 1)));
    }
}
