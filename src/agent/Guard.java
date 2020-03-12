package agent;

import assets.Vector;
import environment.Environment;
import environment.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * create a guard agent
 */


public class Guard implements Agent {

    public Vector position;
    private double direction;
    private double baseSpeed = 1.0;
    private String name;
    private Environment env;
    private double visionRadius = 5.0;
    private int[][] memory;
    private int statecounter = 0;
    private int[] cornerCoords = new int[2];

    private ArrayList<Square> visitedSquares = new ArrayList<>();

    public Guard(String name,
                 Vector position,
                 double direction,
                 double visionRadius,
                 Environment env) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.visionRadius = visionRadius;
        this.env = env;
    }

    public Vector futurePosition() {
        return new Vector(
                this.position.x + Math.cos(this.direction) * this.baseSpeed * env.TIMESTEP,
                this.position.y + Math.sin(this.direction) * this.baseSpeed * env.TIMESTEP
        );
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void updatePosition() {

        switch (statecounter) {
            case 0:
                System.out.println("Just spawned");
                this.cornerCoords = computerNearestCorner();
                statecounter = 1;
                goingToCorner();
                break;
            case 1:
                System.out.println("Go to corner");
                //System.out.println("Direction: "+ this.direction);
                System.out.println("(x, y): "+ (int) this.position.x/30 + ", " + (int) this.position.y/30);
                System.out.println("(x, y): "+ cornerCoords[0] + ", " + cornerCoords[1]);
                if(goingToCorner())
                    statecounter = 2;

                break;
        }

        this.position = this.futurePosition();
    }

    public double getX() {
        return this.position.x;
    }

    public double getY() {
        return this.position.y;
    }

    public String getName() {
        return name;
    }

    public double getDirection() {
        return direction;
    }

    /**
     * @param position
     * @return Square, which has this position
     */

    public Square findSquare(Vector position) {

        Square correctSquare = null;

        double x = 0;
        double y = 0;

        double rest = 0;

        x = Math.floor(position.x);
        rest = x % 30;
        x = x - rest;

        y = Math.floor(position.y);
        rest = y % 30;
        y = y - rest;


        for (Square square : this.env.getGrid().squares) {

            if (square.getCoordinates().x == x && square.getCoordinates().y == y) {
                correctSquare = square;
            }
        }

        return correctSquare;
    }


    /**
     * removes duplicates from the list of squares
     */
    public ArrayList<Square> deleteDoubles(ArrayList<Square> squares) {

        ArrayList<Square> newSquares = new ArrayList<>();

        for (Square square : squares) {

            // If this element is not present in newList
            // then add it
            if (!newSquares.contains(square)) {

                newSquares.add(square);
            }
        }

        // return the new list
        return newSquares;
    }

    /**
     * checks if everything is explored
     */
    public boolean allIsExplored() {

        boolean allCovered = true;

        for (Square square : this.env.getGrid().squares) {

            if (!square.explored) {
                return false;
            }
        }
        return allCovered;
    }

    public int[] computerNearestCorner(){

       int[] coords = new int[2];

        // our position
       int x =  (int)getPosition().x/30;
       int y =  (int)getPosition().y/30;

       int middleX = (this.env.getGrid().getWidth()/30)/2;
       int middleY = (this.env.getGrid().getHeight()/30)/2;

       // find corner
       if(x>middleX && y>middleY){
           coords = new int[]{22, 22};
       }
       else if(x>middleX && y<middleY){
           coords = new int[]{22,1};
       }
       else if(x<middleX && y>middleY){
           coords = new int[]{1, 22};
       }
       else{
           coords = new int[]{1, 1};
       }

       return coords;
    }

    /**
     * moves to closest corner
     *
     */
    public boolean goingToCorner() {

        if((int) position.x/30 != this.cornerCoords[0]){
            if(position.x/30<this.cornerCoords[0]){
                this.direction = 0;
            }
            else{
                this.direction = Math.PI;
            }
            return false;
        }

        if((int) position.y/30 != this.cornerCoords[1]){
            if(position.y/30<this.cornerCoords[1]){
                this.direction = Math.PI/2;
            }
            else{
                this.direction = 3*Math.PI/2;
            }
            return false;
        }
        return true;
    }
}
