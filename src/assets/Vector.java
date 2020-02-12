package assets;

import environment.Area;
import environment.Grid;
import environment.Square;

public class Vector {

    public final double x, y;

    public static final Vector ZERO = new Vector(0, 0);

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(double[] arr) {
        this.x = arr[0];
        this.y = arr[1];
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }

    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }

    public Vector vectorTo(Vector other) {
        return new Vector(other.x - this.x, other.y - this.y);
    }

    public Vector times(double n) {
        return new Vector(this.x * n, this.y * n);
    }

    public double magnitude() {
        return Math.pow(x * x + y * y, 0.5);
    }

    public Vector direction() {
        double mag = this.magnitude();
        if (mag == 0)
            throw new ArithmeticException("no direction for zero vector");
        return new Vector(x / this.magnitude(), y / this.magnitude());
    }

    public double dotProduct(Vector other) {
        return x * other.x + y * other.y;
    }

    public double angleBetween(Vector other) {
        if (dotProduct(other) / (magnitude() * other.magnitude()) > 1.0) return 1.0;
        else if (dotProduct(other) / (magnitude() * other.magnitude()) < -1.0) return -1.0;
        assert Math.abs(dotProduct(other) / (magnitude() * other.magnitude())) <= 1.0;
        return Math.acos(dotProduct(other) / (magnitude() * other.magnitude()));
    }

    @Override
    public String toString() {
        return ("getX: " + x + "; getY: " + y);
    }

    public double[] toArray() {
        return new double[] {x, y};
    }

    public boolean isIn(Area area) {
        return (this.y >= area.getTopB()) && (this.y <= area.getBottomB()) && (this.x >= area.getLeftB()) && (this.x <= area.getRightB());
    }

    public boolean isIn(Square square) {
        return (this.y >= square.getCoordinates().y &&
                (this.y <= square.getCoordinates().y + Grid.SQUARE_MEASURE) &&
                (this.x >= square.getCoordinates().x) &&
                (this.x <= square.getCoordinates().x + Grid.SQUARE_MEASURE));
    }

    public static void main(String[] args) {
        Vector v1 = new Vector(0.0, 1.0);
        Vector v2 = new Vector(1.0, 0.0);
        System.out.println(Algebra.multiplyMatrixByVector(Algebra.instRotTrZ(90.0), v1));
    }

}
