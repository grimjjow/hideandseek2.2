package environment;

import assets.Point;

public class Property extends Point {
    public boolean walkable = true;
    public boolean explored = false;
    public boolean isWall = false;
    public boolean isShaded = false;
    public boolean isTarget = false;
    public boolean isOccupied = false;

    public Property(double x, double y) {
        super(x, y);
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isShaded() {
        return isShaded;
    }

    public void setShaded(boolean shaded) {
        isShaded = shaded;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
