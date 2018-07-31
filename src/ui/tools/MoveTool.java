package ui.tools;

import model.Rectangle;
import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MoveTool extends Tool {

	private Shape rectangleToMove;
	private Point start;

    public MoveTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		rectangleToMove = null;
		start = null;
	}

    // MODIFIES: this
    // EFFECTS:  constructs a move button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Move");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
	@Override
	protected void addListener() {
		button.addActionListener(new MoveToolClickHandler());
	}

    // MODIFIES: this
    // EFFECTS:  Sets the shape at the current mouse position as the shape to move,
	//           selects and plays the shape, and initialize the starting point of
	//           the move with the current location of the MouseEvent
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		rectangleToMove = editor.getShapeInDrawing(e.getPoint());
		if (rectangleToMove != null) {
			rectangleToMove.selectAndPlay();
			start = e.getPoint();
		}
	}

    // MODIFIES: this
    // EFFECTS:  unselect the shape, and set the shape to be moved to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (rectangleToMove != null) {
			rectangleToMove.unselectAndStopPlaying();
			rectangleToMove = null;
		}
	}

    // MODIFIES: this
    // EFFECTS:  compute the change in the x and y position of the mouse, and move the shape
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (rectangleToMove != null) {
			int dx = (int) (e.getPoint().getX() - start.getX());
			int dy = (int) (e.getPoint().getY() - start.getY());
			start = e.getPoint();
			rectangleToMove.move(dx, dy);
		}
	}

	private class MoveToolClickHandler implements ActionListener {

		// EFFECTS: sets active tool to the move tool
		//          called by the framework when the tool is clicked
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(MoveTool.this);
		}
	}


}