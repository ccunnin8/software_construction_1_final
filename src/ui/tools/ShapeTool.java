package ui.tools;

import model.Oval;
import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public abstract class ShapeTool extends Tool {
    protected Shape shape;
    protected String name;

    public ShapeTool(DrawingEditor editor, JComponent parent, String name) {
        super(editor, parent);
        shape = null;
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton(name);
		button = customizeButton(button);
	}

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ShapeToolClickHandler());
	}

    // MODIFIES: this
    // EFFECTS:  a rectangle is instantiate MouseEvent occurs, and played and
    //           added to the editor's drawing
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		shape = new Oval(e.getPoint(), editor.getMidiSynth());
		shape.selectAndPlay();
		shape.setBounds(e.getPoint());
		editor.addToDrawing(shape);
	}

    // MODIFIES: this
    // EFFECTS:  unselects this rectangle, and sets it to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
        shape.unselectAndStopPlaying();
	    shape = null;
	}

    // MODIFIES: this
    // EFFECTS:  sets the bounds of thes rectangle to where the mouse is dragged to
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		shape.setBounds(e.getPoint());
	}

    private class ShapeToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the rectangle tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.setActiveTool(ShapeTool.this);
        }
    }
}
