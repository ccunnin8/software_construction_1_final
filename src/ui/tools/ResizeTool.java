package ui.tools;

import model.Rectangle;
import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ResizeTool extends Tool {

	private Shape rectangleToResize;

    // EFFECTS: creates a new ResizeTool with the given editor and parent. Sets rectangleToResize to null
	public ResizeTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		rectangleToResize = null;
	}

    // MODIFIES: this
    // EFFECTS: creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Resize");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS: associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ResizeToolClickHandler());
	}

    // MODIFIES: this
	// EFFECTS:  Sets the shape at the current mouse position as the shape to resize,
	//           selects the shape and plays it
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		rectangleToResize = editor.getShapeInDrawing(e.getPoint());
		if (rectangleToResize != null) {
			rectangleToResize.selectAndPlay();
		}
	}

    // MODIFIES: this
    // EFFECTS: deselects the resized shape, sets the shape to resize to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (rectangleToResize != null) {
			rectangleToResize.unselectAndStopPlaying();
			rectangleToResize = null;
		}
    }

    // MODIFIES: this
    // EFFECTS: resizes rectangleToResize to drag release point
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (rectangleToResize != null) {
			rectangleToResize.setBounds(e.getPoint());
		}
	}

	private class ResizeToolClickHandler implements ActionListener {

		// EFFECTS: sets active tool to the resize tool
		//          called by the framework when the tool is clicked
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(ResizeTool.this);
		}
	}


}