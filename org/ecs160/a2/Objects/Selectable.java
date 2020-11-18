package org.ecs160.a2.Objects;
import com.codename1.ui.Graphics;

public abstract class Selectable {
    protected int x, y, width, height;
    protected boolean selectStatus;

    public Selectable(int x, int y, int width, int height) {
        // set coordinates and width and height for the hit box
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.selectStatus = false;
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract void draw(Graphics g);

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}

    public boolean hitBoxHas(int x, int y) {
        int bottom = this.y + height;
        int right = this.x + width;
        int left = this.x;
        int top = this.y;
        return (left <= x && x < right) && (top <= y && y <= bottom);
    }

    public void flipSelected() {
        this.selectStatus = !this.selectStatus;
    }
}
