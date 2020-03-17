package environment;

import assets.Vector;

import java.util.ArrayList;

public class Square {

    /**
     * bottom-left angle of the square
     */
    private Vector coordinates;
    private String type;
    private Area area;
    public boolean walkable;
    public boolean explored;

    public Square(double x, double y, Area area) {
        this.coordinates = new Vector(x, y);
        this.area = area;
        this.walkable = this.area.getType().equals("Map") ||
                this.area.getType().equals("Door") ||
                this.area.getType().equals("ShadedArea");

    }

    public String toString() {
        return "" + (int) this.coordinates.x + "_" + (int) this.coordinates.y + " is a " + this.type;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        if (this.coordinates.isIn(this.area))
            this.type = area.getType();
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vector getCoordinates() {
        return coordinates;
    }



}
