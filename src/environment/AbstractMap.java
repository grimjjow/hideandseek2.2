package environment;

import assets.Algebra;
import assets.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbstractMap {
    private double area;
    private double height;
    private double width;
    private double areaScaled;
    private double scaling;
    ArrayList<Point> points;
    ArrayList<Property> properties;
    private static HashMap<Point, Property> hash = new HashMap<Point, Property>();

    public void scaleGrid(String height, String width, String scaling){
        area = Algebra.area(Integer.parseInt(height), Integer.parseInt(width)) ;
        this.scaling = Double.parseDouble(scaling);

        areaScaled = area / this.scaling;
        this.width = Integer.parseInt(width);
        this.height = Integer.parseInt(height);
        //System.out.println("height = " +height+ " width = " + width);
    }

    public List<Point> createPoints(){
      double x;
      double y;
      points = new ArrayList<Point>();
      for (int i=0; i<=height; i++) {
          x = i;

          for (int j = 0; j<=width; j++){
              y = j;

          Point point = new Point(x, y);
             //System.out.println(point.getPoint());
          points.add(point);
      }
      }
         return points;
    }
    public List<Property> createProperty(){

        properties = new ArrayList<Property>();
        for (Point p : points){
            Property property = new Property(p.getX(),p.getY());
            properties.add(property);
        }

        return properties;
    }



    public AbstractMap() {
    };
    public AbstractMap(ArrayList<Point> points, ArrayList<Property> properties) {
        System.out.println("Initial list of elements: "+points);
        /*hm.put(100,"Amit");
        hm.put(101,"Vijay");
        hm.put(102,"Rahul");*/
    };
    public void addMap(List<Point> points, List<Property> properties){
        for (Point p : points) {
            for (Property g: properties){
              if (p.getX() == g.getX() && p.getY() == g.getY()) {
                  hash.put(p, g);
              }
        }
        }
    }
    public HashMap<Point, Property> getMap() {
        return hash;
    }

}
