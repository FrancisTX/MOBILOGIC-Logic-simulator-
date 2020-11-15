package org.ecs160.a2.Widgets;

public abstract class Movable {
    private int x,y;
    public Movable(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
