package ui.tools;

import model.Rectangle;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class RectangleTool extends ShapeTool {

    public RectangleTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent,"Rectangle");
    }

    // MODIFIES: this
    // EFFECTS:  a rectangle is instantiate MouseEvent occurs, and played and
    //           added to the editor's drawing
    @Override
    public void mousePressedInDrawingArea(MouseEvent e) {
        shape = new Rectangle(e.getPoint(), editor.getMidiSynth());
        shape.selectAndPlay();
        shape.setBounds(e.getPoint());
        editor.addToDrawing(shape);
    }

}