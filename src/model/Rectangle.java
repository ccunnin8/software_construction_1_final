package model;


import sound.MidiSynth;

import java.awt.*;


public class Rectangle extends Shape {

    public Rectangle(Point topLeft, MidiSynth midiSynth) {
        this((int) topLeft.getX(), (int) topLeft.getY(), 0, 0);  // note to students: calls the other constructor!
        selected = false;
        this.midiSynth = midiSynth;
        instrument = 0;
        playLineCoord = 0;
    }

    public Rectangle(int x, int y, int w, int h) {
        super(h, y, w, x);
    }

    // EFFECTS: draws this Rectangle on the SimpleDrawingPlayer, if the retangle is selected, rectangle is filled in
    //          else, rectangle is unfilled (white)
    public void draw(Graphics g) {
        Color save = g.getColor();
        if (selected) {
            g.setColor(PLAYING_COLOR);
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x, y, width, height);
        g.setColor(save);
        g.drawRect(x, y, width, height);

        if (playLineCoord > 0 && playLineCoord < width) {
            g.setColor(Color.red);
            g.drawLine(x + playLineCoord, y, x + playLineCoord, y + height);
            g.setColor(save);
        }
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Rectangle
    public boolean contains(Point point) {
        int point_x = point.x;
        int point_y = point.y;

        return containsX(point_x) && containsY(point_y);
    }


}