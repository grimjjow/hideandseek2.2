import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import assets.Point;
import environment.AbstractMap;
import environment.GameInfo;
import environment.Property;

public class Reader extends Buffer {
    static String FILE_NAME = "C:\\Temp\\input.txt";
    public GameInfo g;

    public Reader() {
        super();
    }

    public Reader(File file) {
        super(file);
    }

    public Reader(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    void readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                //process each line in some way
                log(scanner.nextLine());
            }
        }
    }

    void readBufferedFile(String fileName) throws IOException {
        /*Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                //process each line in some way
                log(line);
            }
        }*/
        
        Reader2 firstReader = new Reader2(fileName);
        g = firstReader.g;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //New Part
        AbstractMap m = new AbstractMap();
        m.scaleGrid(g.getHeight(), g.getWidth(), g.getScaling());
        List<Point> points = m.createPoints();
        List<Property> properties = m.createProperty();

        List<Property> propertiesAdded = addProperty(properties, g);
        m.addMap(points,propertiesAdded);
        //To inspect the properties of datapoints, pass the key parameters to the map and get property
        Point pg = new Point();
        System.out.println("--Inspection: \n");
        for (Point p : points){
            if (p.getX() < 20 && p.getY() < 40){
                pg = p;
                Object object = m.getMap().get(pg);
                Property pr = ((Property) object);
                System.out.println("\n Data-point: x= " + pr.getX() + " y= " + pr.getY() + " isWall= " + pr.isWall + " isShaded= " + pr.isShaded);
            }

        }


    }
        //Add properties to empty property list
    List<Property> addProperty(List<Property> points, GameInfo g) {
        List<Property> propertyList = points;
        double x;
        double y;
       propertyList = addWall(points,g.getWall());
       propertyList = addShaded(points, g.getShaded());
    return propertyList;
    }
    
    //REGION NOT USED
    int[] convertString(String s) {

        String str = s;


        //Take out all crap in the string
        String[] crap = {"(", ")", ",", ";"};
        for (String replace : crap) {
            str = str.replace(replace, " ").trim();
        }
        // This replaces any multiple spaces with a single space
        while (str.contains("  ")) {
            str = str.replace("  ", " ");
        }

        //trim values and convert an int array
        String[] values = str.split(" ");
        int[] intVal = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            String strVal = values[i];

            intVal[i] = Integer.parseInt(strVal.trim());
        }
            /*
            for (int value : intVal) {
                System.out.println(value);
            }
*/
        return intVal;

    }
    //ENDREGION NOT USED
    
    List<Property> addWall(List<Property> points, String[] wall) {  //TODO
    for (int index = 0; index<wall.length; index++) {
        int[][] intWall = g.convertWall();
        //treat a wall like a grid, check array form in covertString method.
        int leftBTemp= intWall[index][0];
        int topBTemp = intWall[index][1];
        int rightBTemp = intWall[index][2];
        int bottomBTemp = intWall[index][3];

           int leftB = Math.min(leftBTemp, rightBTemp);
           int rightB = Math.max(leftBTemp, rightBTemp);
           int topB = Math.max(topBTemp, bottomBTemp);
           int bottomB = Math.min(bottomBTemp, topBTemp);



            for (Property p : points) {
                if (p.getX() >=leftB && p.getX() <=rightB && p.getY() <= topB && p.getY() >= bottomB)  {
                        p.setWall(true);
                        //System.out.println("\nWall added! Position: x = " + p.getX() + " y = " + p.getY());
                } else p.setWall(false);
            }

    }
        return points;
    }
    List<Property> addShaded(List<Property> points, String[] shaded) {
	        for (int index = 0; index<shaded.length; index++) {
	            //int[] intShaded = convertString(shaded[index]);
	            int[][] intShaded = g.convertShaded();
	            //treat a shade like a grid, check array form in covertString method.
	            int leftBTemp= intShaded[index][0];
	            int topBTemp = intShaded[index][1];
	            int rightBTemp = intShaded[index][2];
	            int bottomBTemp = intShaded[index][3];
	
	            int leftB = Math.min(leftBTemp, rightBTemp);
	            int rightB = Math.max(leftBTemp, rightBTemp);
	            int topB = Math.max(topBTemp, bottomBTemp);
	            int bottomB = Math.min(bottomBTemp, topBTemp);
	
	
	            for (Property p : points) {
	                if (p.getX() >=leftB && p.getX() <=rightB && p.getY() <= topB && p.getY() >= bottomB)  {
	                    p.setShaded(true);
	                    //System.out.println("\nShade added! Position: x = " + p.getX() + " y = " + p.getY());
	                } else p.setShaded(false);
	            }
	
	        }
        return points;
    }
}

