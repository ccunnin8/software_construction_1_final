package test;

import org.junit.Assert;
import org.junit.Test;
import sound.MidiSynth;
import model.Shape;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class ShapeTest {
    protected MidiSynth synth;
    protected Shape shape1;
    protected Shape shape2;

    @Test
    public void testGetters() {
        Assert.assertEquals(1, shape2.getXCoord());
        Assert.assertEquals(3, shape2.getYCoord());
        Assert.assertEquals(5, shape2.getWidth());
        Assert.assertEquals(10, shape2.getHeight());

        Assert.assertEquals(1, shape1.getXCoord());
        Assert.assertEquals(1, shape1.getYCoord());
    }

    @Test
    public void testsetBounds() {
        // Start in Quadrant I
        Assert.assertEquals(0, shape1.getHeight());
        Assert.assertEquals(0, shape1.getWidth());

        shape1.setBounds(new Point(4, 5));

        Assert.assertEquals(4, shape1.getHeight());
        Assert.assertEquals(3, shape1.getWidth());
    }

    @Test
    public void testSetBoundsGeneral() {
        Assert.assertEquals(10, shape2.getHeight());
        Assert.assertEquals(5, shape2.getWidth());

        shape2.setBounds(new Point(4, 7));

        Assert.assertEquals(4, shape2.getHeight());
        Assert.assertEquals(3, shape2.getWidth());
    }

    @Test
    public void testSelectAndPlay() {
        shape1.setBounds(new Point(5, 4));
        assertFalse(shape1.isSelected());
        shape1.selectAndPlay();
        assertTrue(shape1.isSelected());
    }

    @Test
    public void testUnselectAndStop() {
        shape1.setBounds(new Point (10, 3));
        assertFalse(shape1.isSelected());
        shape1.unselectAndStopPlaying();
        assertFalse(shape1.isSelected());
        shape1.selectAndPlay();
        assertTrue(shape1.isSelected());
        shape1.unselectAndStopPlaying();
        assertFalse(shape1.isSelected());
    }

    @Test
    public void testcontainsX() {
        shape1.setBounds(new Point(4, 2));
        assertFalse(shape1.containsX(0));
        assertFalse(shape1.containsX(5));
        assertTrue(shape1.containsX(3));
    }


    @Test
    public void testmove() {
        shape1.setBounds(new Point(4, 2));
        Assert.assertEquals(3, shape1.getWidth());
        Assert.assertEquals(1, shape1.getHeight());

        shape1.move(3, 4);

        Assert.assertEquals(1 + 3, shape1.getXCoord());
        Assert.assertEquals(1 + 4, shape1.getYCoord());
        Assert.assertEquals(3, shape1.getWidth());
        Assert.assertEquals(1, shape1.getHeight());

    }

    @Test
    public void testmoveHugeDistance() {
        shape1.setBounds(new Point(4, 2));
        Assert.assertEquals(3, shape1.getWidth());
        Assert.assertEquals(1, shape1.getHeight());

        shape1.move(10000, 20000);

        Assert.assertEquals(1 + 10000, shape1.getXCoord());
        Assert.assertEquals(1 + 20000, shape1.getYCoord());
        Assert.assertEquals(3, shape1.getWidth());
        Assert.assertEquals(1, shape1.getHeight());
    }
}
