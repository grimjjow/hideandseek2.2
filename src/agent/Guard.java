package agent;

import environment.Environment;
import environment.Square;
import assets.Vector;

import java.util.ArrayList;

/**
 *
 * create a guard agent
 */


public class Guard implements Agent {

    public Vector position;
    private double direction;
    private double baseSpeed = 1.0;
    private String name;
    private Environment env;
    private double visionRadius = 5.0;

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
        for (Square square : this.env.getGrid().squares)
            if (!square.walkable)
                if (this.futurePosition().isIn(square))
                    this.direction += Math.PI * 2 * Math.random();

        this.position = this.futurePosition();
        //System.out.println("guard has move x " + position.x + " and y " + position.y);
        this.findNeighbours();
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
    // get the square, where the guard is in
    public Square getSquare(){

        Square temp = null;
        double rest = 0;

        double x = Math.floor(position.x);
        double y = Math.floor(position.y);

        rest = x % 30;
        x = x - rest;

        rest = y % 30;
        y = y - rest;

        for (Square square : this.env.getGrid().squares) {

            if(square.getCoordinates().x  == x && square.getCoordinates().y == y){
                temp = square;
            }
        }
        System.out.println("\nCoordinates of square: x " + temp.getCoordinates().x + " y " +  temp.getCoordinates().y);
        return temp;
    }

    public ArrayList<Square> findNeighbours(){

        ArrayList<Square> neighbours = new ArrayList<>();
        Square guardSquare = getSquare();

        ArrayList<Vector> coordNeighbours = new ArrayList<>();

        // neighbour left top
        Vector vec1 = new Vector(guardSquare.getCoordinates().x-30, guardSquare.getCoordinates().y-30);

        // neighbour left middle
        Vector vec2 = new Vector(guardSquare.getCoordinates().x-30,  guardSquare.getCoordinates().y);

        // neighbour left bottom
        Vector vec3 = new Vector(guardSquare.getCoordinates().x-30,  guardSquare.getCoordinates().y+30);

        // neighbour middle top
        Vector vec4 = new Vector(guardSquare.getCoordinates().x,  guardSquare.getCoordinates().y-30);

        // neighbour middle bottom
        Vector vec5 = new Vector(guardSquare.getCoordinates().x,  guardSquare.getCoordinates().y+30);

        // neighbour right top
        Vector vec6 = new Vector(guardSquare.getCoordinates().x+30,  guardSquare.getCoordinates().y-30);

        // neighbour right middle
        Vector vec7 = new Vector(guardSquare.getCoordinates().x+30,  guardSquare.getCoordinates().y);

        // neighbour right bottom
        Vector vec8 = new Vector(guardSquare.getCoordinates().x+30,  guardSquare.getCoordinates().y+30);

        // adding to the list
        coordNeighbours.add(vec1);
        coordNeighbours.add(vec2);
        coordNeighbours.add(vec3);
        coordNeighbours.add(vec4);
        coordNeighbours.add(vec5);
        coordNeighbours.add(vec6);
        coordNeighbours.add(vec7);
        coordNeighbours.add(vec8);


        // go through all squares
            // and find the right suqare,saefbslkhfn
        for (Square square : this.env.getGrid().squares) {

            for (Vector vector : coordNeighbours) {

                if (square.getCoordinates().x == vector.x && square.getCoordinates().y == vector.y) {

                    neighbours.add(square);
                    System.out.print(" X: " + square.getCoordinates().x + " y: " + square.getCoordinates().y + " ");
                }
            }

        }


/*

            int n1 = this.env.getGrid().squares.indexOf(guardSquare)-1;
        Square ns1 = this.env.getGrid().squares.get(n1);
        neighbours.add(ns1);

        int n2 = this.env.getGrid().squares.indexOf(guardSquare)+1;
        Square ns2 = this.env.getGrid().squares.get(n2);
        neighbours.add(ns2);

        int n3 = this.env.getGrid().squares.indexOf(guardSquare)-22;
        Square ns3 = this.env.getGrid().squares.get(n3);
        neighbours.add(ns3);

        int n4 = this.env.getGrid().squares.indexOf(guardSquare)+22;
        Square ns4 = this.env.getGrid().squares.get(n4);
        neighbours.add(ns4);

        int n5 = this.env.getGrid().squares.indexOf(guardSquare)+23;
        Square ns5 = this.env.getGrid().squares.get(n5);
        neighbours.add(ns5);

        int n6 = this.env.getGrid().squares.indexOf(guardSquare)+21;
        Square ns6 = this.env.getGrid().squares.get(n6);
        neighbours.add(ns6);

        int n7 = this.env.getGrid().squares.indexOf(guardSquare)-23;
        Square ns7 = this.env.getGrid().squares.get(n7);
        neighbours.add(ns7);

        int n8 = this.env.getGrid().squares.indexOf(guardSquare)-21;
        Square ns8 = this.env.getGrid().squares.get(n8);
        neighbours.add(ns8);

        System.out.println("Size of Neighbours: " + neighbours.size());
        System.out.print(" Index: n1 " + n1);
        System.out.print(" Index: n2 " + n2);
        System.out.print(" Index: n3 " + n3);
        System.out.print(" Index: n4 " + n4);
        System.out.print(" Index: n5 " + n5);
        System.out.print(" Index: n6 " + n6);
        System.out.print(" Index: n7 " + n7);
        System.out.print(" Index: n8 " + n8 + " ");
*/
        return neighbours;

    }


    /**
     *  further functionalities:
     *              -   vision
     *              -   give a sound
     *              -   marker placement
     *              -   sprint
     *              -   speed
     *              -   turn
     *
     *
     *  path finding:
     *              -   A*
     */
}
