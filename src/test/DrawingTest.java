package test;

import model.Drawing;
import model.Rectangle;
import model.Shape;
import org.junit.Before;
import org.junit.Test;
import sound.MidiSynth;

import java.awt.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public abstract class DrawingTest {

    protected Drawing testDrawing;
    protected Shape shape1, shape2;


    @Test
    public void testGetters() {
        assertEquals(0, testDrawing.getShapes().size());
        testDrawing.setPlayLineColumn(5);
        assertEquals(5, testDrawing.getPlayLineColumn());
    }

    @Test
    public void testaddShape() {
        assertEquals(0, testDrawing.getShapes().size());
        testDrawing.addShape(shape1);
        assertEquals(1, testDrawing.getShapes().size());
        assertTrue(testDrawing.containsShape(shape1));
        testDrawing.addShape(shape2);
        assertEquals(2, testDrawing.getShapes().size());
        assertTrue(testDrawing.containsShape(shape1) && testDrawing.containsShape(shape2));
    }

    @Test
    public void testremoveShape() {
        assertEquals(0, testDrawing.getShapes().size());
        testDrawing.addShape(shape1);
        assertTrue(testDrawing.containsShape(shape1));
        testDrawing.removeShape(shape1);
        assertFalse(testDrawing.containsShape(shape1));
        assertEquals(0, testDrawing.getShapes().size());
    }

    @Test
    public void testAddShapeStress() {
        MidiSynth synth = new MidiSynth();
        synth.open();
        int j = 0;
        for (int i = 0; i < 500000; i++) {
            testDrawing.addShape(new Rectangle(new Point(i, j), synth));
            j++;
        }
        assertEquals(500000, testDrawing.getShapes().size());
    }

    @Test
    public void addBigShape() {
        MidiSynth synth = new MidiSynth();
        synth.open();
        Rectangle bigRectangle = new Rectangle(new Point(1, 1),synth);
        bigRectangle.setBounds(new Point(1000000, 2000000));
        testDrawing.addShape(bigRectangle);
        assertTrue(testDrawing.containsShape(bigRectangle));
    }

    @Test
    public void addBigShapeStress() {
        MidiSynth synth = new MidiSynth();
        synth.open();
        int j = 0;

        for (int i = 0; i < 500000; i++) {
            Rectangle bigRectangle = new Rectangle(new Point(1, 1),synth);
            bigRectangle.setBounds(new Point(i * 10000, j * 200000));
            testDrawing.addShape(bigRectangle);
            j++;
        }
        assertEquals(500000, testDrawing.getShapes().size());
    }

    @Test
    public void testGetShapesAtPoint() { }


}