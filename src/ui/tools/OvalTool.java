package ui.tools;

import model.Oval;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class OvalTool extends ShapeTool
{
    public OvalTool(DrawingEditor editor, JComponent parent) {
        super(editor, parent, "Oval");
    }

    // MODIFIES: this
    // EFFECTS:  a oval is instantiate MouseEvent occurs, and played and
    //           added to the editor's drawing
    @Override
    public void mousePressedInDrawingArea(MouseEvent e) {
        shape = new Oval(e.getPoint(), editor.getMidiSynth());
        shape.selectAndPlay();
        shape.setBounds(e.getPoint());
        editor.addToDrawing(shape);
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Oval");
        button = customizeButton(button);
    }
}
