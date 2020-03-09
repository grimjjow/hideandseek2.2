package assets;

//Main data point object to store in hashmap
public class Point {
    private double x;
    private double y;
/*
        @param x coordinate, y coordinate

 */
    public Point(double x, double y) {
        //TODO add checking for real number exception
        this.x = x;
        this.y = y;
    }

    public String getPoint(){
        String s = "Point: x = " + x + " y = " + y;
        return s;
    }

    public Point() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
