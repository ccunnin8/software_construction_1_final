package model;

import sound.MidiSynth;

import java.awt.*;

public abstract class Shape {
    protected static final Color PLAYING_COLOR = new Color(230, 9, 21);
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean selected;
    protected MidiSynth midiSynth;
    protected int instrument;
    protected int playLineCoord;

    public Shape(int h, int y, int w, int x) {
        height = h;
        this.y = y;
        width = w;
        this.x = x;
    }

    // getters
    public int getWidth() { return width; }

    public int getXCoord() { return x; }

    public int getYCoord() { return y; }

    public int getHeight() { return height; }

    public boolean isSelected() { return selected; }

    // setters
    public void setPlayLineCoord(int playLineCoord) {
        this.playLineCoord = playLineCoord;
    }

    // EFFECTS: return true iff the given x value is within the bounds of the Shape
    public boolean containsX(int x) {
        return (this.x <= x) && (x <= this.x + width);
    }

    // EFFECTS: return true iff the given y value is within the bounds of the Shape
    public boolean containsY(int y) {
        return (this.y <= y) && (y <= this.y + height);
    }


    // REQUIRES: the x,y coordinates of the Point are larger than the x,y coordinates of the shape
    // MODIFIES: this
    // EFFECTS:  sets the bottom right corner of this Shape to the given Point
    public void setBounds(Point bottomRight) {
        width = bottomRight.x - x;
        height = bottomRight.y - y;
    }


    // MODIFIES: this
    // EFFECTS:  adds dx to the shape's x coordinate, and dy to the shape's y coordinate.
    //           If the sound associated with the new y-coordinate is different, play the new sound
    public void move(int dx, int dy) {
        boolean noteChanges;
        noteChanges = (coordToNote(y) != coordToNote(y + dy));
        if (noteChanges) {
            stopPlaying();
        }
        x += dx;
        y += dy;
        if (noteChanges) {
            play();
        }
    }

    // MODIFIES: this
    // EFFECTS:  selects this Shape, plays associated sound
    public void selectAndPlay() {
        if (!selected) {
            selected = true;
            play();
        }
    }

    // MODIFIES: this
    // EFFECTS:  unselects this Shape, stops playing associated sound
    public void unselectAndStopPlaying() {
        if (selected) {
            selected = false;
            stopPlaying();
        }
    }

    // EFFECTS: starts playing this Shape, where sound is dependent on the area/coordinates of the Shape
    private void play() {
        int volume = areaToVelocity(width * height);
        midiSynth.play(instrument, coordToNote(y), volume);
    }

    // EFFECTS: stops playing this Shaoe
    private void stopPlaying() {
        midiSynth.stop(instrument, coordToNote(y));
    }

    // EFFECTS: return a velocity based on the area of a Shape
    //          The only meaningful velocities are between 0 and 127
    //          Velocities less than 60 are too quiet to be heard
    private int areaToVelocity(int area) {
        return Math.max(60, Math.min(127, area / 30));
    }

    // EFFECTS: maps a given integer to a valid associated note
    private int coordToNote(int y) {
        return 70 - y / 12;
    }

    // EFFECTS: draws the shape
    public abstract void draw(Graphics g);

    // EFFECTS: returns true if point is within shape
    public abstract boolean contains(Point p);
}
