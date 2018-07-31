package test;

import model.Rectangle;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static org.junit.Assert.*;

public class RectangleTest {

    private Rectangle testRectangle1, testRectangle2;
    private MidiSynth synth;

    @Before
    public void setUp() {
        synth = new MidiSynth();
        synth.open();
        testRectangle1 = new Rectangle(new Point(1, 1), synth);
        testRectangle2 = new Rectangle(1, 3 , 5, 10);
    }

    @Test
    public void testGetters() {
        assertEquals(1, testRectangle2.getXCoord());
        assertEquals(3, testRectangle2.getYCoord());
        assertEquals(5, testRectangle2.getWidth());
        assertEquals(10, testRectangle2.getHeight());

        assertEquals(1, testRectangle1.getXCoord());
        assertEquals(1, testRectangle1.getYCoord());
    }

    @Test
    public void testsetBounds() {
        // Start in Quadrant I
        assertEquals(0, testRectangle1.getHeight());
        assertEquals(0, testRectangle1.getWidth());

        testRectangle1.setBounds(new Point(4, 5));

        assertEquals(4, testRectangle1.getHeight());
        assertEquals(3, testRectangle1.getWidth());
    }

    @Test
    public void testSetBoundsGeneral() {
        assertEquals(10, testRectangle2.getHeight());
        assertEquals(5, testRectangle2.getWidth());

        testRectangle2.setBounds(new Point(4, 7));

        assertEquals(4, testRectangle2.getHeight());
        assertEquals(3, testRectangle2.getWidth());
    }

    @Test
    public void testSelectAndPlay() {
        testRectangle1.setBounds(new Point(5, 4));
        assertFalse(testRectangle1.isSelected());
        testRectangle1.selectAndPlay();
        assertTrue(testRectangle1.isSelected());
    }

    @Test
    public void testUnselectAndStop() {
        testRectangle1.setBounds(new Point (10, 3));
        assertFalse(testRectangle1.isSelected());
        testRectangle1.unselectAndStopPlaying();
        assertFalse(testRectangle1.isSelected());
        testRectangle1.selectAndPlay();
        assertTrue(testRectangle1.isSelected());
        testRectangle1.unselectAndStopPlaying();
        assertFalse(testRectangle1.isSelected());
    }

    @Test
    public void testcontainsX() {
        testRectangle1.setBounds(new Point(4, 2));
        assertFalse(testRectangle1.containsX(0));
        assertFalse(testRectangle1.containsX(5));
        assertTrue(testRectangle1.containsX(3));
    }

    @Test
    public void testcontainsPoint() {
        testRectangle1.setBounds(new Point(5, 5));
        assertTrue(testRectangle1.contains(new Point(5, 5)));
        assertFalse(testRectangle1.contains(new Point(0, 0)));
        assertTrue(testRectangle1.contains(new Point(4, 4)));
        assertTrue(testRectangle1.contains(new Point(4, 1)));
        assertTrue(testRectangle1.contains(new Point(1, 1)));
        assertFalse(testRectangle1.contains(new Point(5, 7)));
        assertFalse(testRectangle1.contains(new Point(8, 8)));
        assertTrue(testRectangle1.contains(new Point(1, 1)));
    }

    @Test
    public void testmove() {
        testRectangle1.setBounds(new Point(4, 2));
        assertEquals(3, testRectangle1.getWidth());
        assertEquals(1, testRectangle1.getHeight());

        testRectangle1.move(3, 4);

        assertEquals(1 + 3, testRectangle1.getXCoord());
        assertEquals(1 + 4, testRectangle1.getYCoord());
        assertEquals(3, testRectangle1.getWidth());
        assertEquals(1, testRectangle1.getHeight());

    }

    @Test
    public void testmoveHugeDistance() {
        testRectangle1.setBounds(new Point(4, 2));
        assertEquals(3, testRectangle1.getWidth());
        assertEquals(1, testRectangle1.getHeight());

        testRectangle1.move(10000, 20000);

        assertEquals(1 + 10000, testRectangle1.getXCoord());
        assertEquals(1 + 20000, testRectangle1.getYCoord());
        assertEquals(3, testRectangle1.getWidth());
        assertEquals(1, testRectangle1.getHeight());
    }


}