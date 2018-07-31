package ui.tools;


import model.Oval;
import model.Rectangle;
import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ShapeTool extends Tool {

	private Shape rectangle;

    public ShapeTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		rectangle = null;
	}

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Rectangle");
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
		rectangle = new Oval(e.getPoint(), editor.getMidiSynth());
		rectangle.selectAndPlay();
		rectangle.setBounds(e.getPoint());
		editor.addToDrawing(rectangle);
	}

	// MODIFIES: this
    // EFFECTS:  unselects this rectangle, and sets it to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
        rectangle.unselectAndStopPlaying();
	    rectangle = null;
	}

	// MODIFIES: this
    // EFFECTS:  sets the bounds of thes rectangle to where the mouse is dragged to
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		rectangle.setBounds(e.getPoint());
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