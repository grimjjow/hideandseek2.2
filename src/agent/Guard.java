package agent;

import assets.Vector;
import environment.Environment;
import environment.Square;

import java.util.ArrayList;

/**
 * create a guard agent
 */


public class Guard implements Agent {

    // agent attributes
    public Vector position;
    private double direction;
    private double baseSpeed = 1.0;
    private String name;
    private Environment env;
    private double viewAngle;
    private double viewRange;
    private int[][] memory;

    private int statecounter = 0;
    private int[] cornerCoords = new int[2];

    // helping booleans
    private boolean left = true;
    private boolean hitWall2 = false;

    ArrayList<Square> visibleSquares = new ArrayList<>();

    public Guard(String name,
                 Vector position,
                 double direction,
                 double viewAngle,
                 double viewRange,
                 Environment env) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.env = env;
        this.viewAngle = viewAngle;
        this.viewRange = viewRange;
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
        if (memory == null)
            this.memory = new int[(this.env.getGrid().getHeight() / 30 + 1)][(this.env.getGrid().getWidth() / 30)];

        //System.out.println("state: " + statecounter);
        switch (statecounter) {
            case 0:
                System.out.println("Just spawned");
                statecounter = 1;
                goingToCorner();
                break;
            case 1:
                if (goingToCorner())
                    statecounter = 2;

                //System.out.println("(x, y): "+ (int) this.position.x/30 + ", " + (int) this.position.y/30);
                //System.out.println("(x, y): "+ cornerCoords[0] + ", " + cornerCoords[1]);
                break;
            case 2:

                findDirection();
                left = !left;
                statecounter = 3;
                break;

            case 3:
                Square currentSquare = findSquare(getPosition());
                Square nextSquare = findSquare(this.futurePosition());
                left = !left;
                if (spaceExplorer(nextSquare) && currentSquare == nextSquare) {
                    statecounter = 2;
                } else {
                    statecounter = 3;
                }
                break;
        }
        vectorsOfVision();
        System.exit(0);
        this.position = this.futurePosition();
        //memory[(int) ((findSquare(this.position).getCoordinates().y / 30))][(int) ((findSquare(this.position).getCoordinates().x / 30))] = 1;

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
     * moves to closest corner
     */
    public boolean goingToCorner() {

        if (!findSquare(this.futurePosition()).walkable && !hitWall2) {
            hitWall2 = true;
        } else if (!findSquare(this.futurePosition()).walkable && hitWall2) {
            cornerCoords[0] = (int) getX() / 30;
            cornerCoords[1] = (int) getY() / 30;
            this.direction = 0;
            return true;
        }
        if (!hitWall2) {
            this.direction = Math.PI;
        } else {
            this.direction = Math.PI / 2;
        }
        return false;
    }

    /**
     * changes the direction
     */

    public void findDirection() {

        // left corner
        if (left) {
            this.direction = Math.PI;
        } else {
            this.direction = 0;
        }

    }

    /**
     *
     * @param temp
     * @return explores the space until a wall is hitten
     */

    public boolean spaceExplorer(Square temp) {

        if (!temp.walkable || this.direction == 3 * Math.PI / 2 || this.direction == Math.PI / 2) {
            /*if(!temp.walkable){
                //memory[(int) (temp.getCoordinates().y/30)][(int) (temp.getCoordinates().x/30)] = 2;
            }*/
            left = !left;
            this.direction = 3 * Math.PI/2;

            return true;
        }
        return false;
    }


    public void vectorsOfVision() {

        int agentX = (int) getPosition().x / 30;
        int agentY = (int) getPosition().y / 30;
        double agentDirection = getDirection();
        // of line y = slope*x+intercept
        double slope;
        double intercept;


        for (double i = -(viewAngle/2); i < viewAngle/2; i++) {

            // compute the lines (vectors)
            // get endpoints of the 45 vectors
            double targetX = agentX+viewRange*Math.cos(agentDirection+Math.toRadians(i));
            double targetY = agentY+viewRange*Math.sin(agentDirection+Math.toRadians(i));
            slope = (targetY-agentY)/(targetX-agentX);
            intercept = targetY-(slope*targetX);

            ArrayList<Square> vectorSquare = new ArrayList<>();

            System.out.println("target x: " + targetX +  ", target Y: " + targetY);

            for(Square square : this.env.getGrid().squares) {

                double squareX = square.getCoordinates().x / 30;
                double squareY = square.getCoordinates().y / 30;

                // is on the line
                if (Math.round(squareX * slope + intercept) == squareY) {

                    if(!visibleSquares.contains(square)) {
                        if (agentX < targetX) {
                            if (agentY < targetY) {
                                //check if agentX <= squareX <= targetX and agentY <= squareY <= targetY
                                if (squareX >= agentX && squareX <= targetX && squareY >= agentY && squareY <= targetY) {
                                    vectorSquare.add(square);
                                }
                            } else {
                                //check if agentX <= squareX <= targetX and targetY <= squareY <= agentY
                                if (squareX >= agentX && squareX <= targetX && squareY <= agentY && squareY >= targetY) {
                                    vectorSquare.add(square);
                                }
                            }
                        } else {
                            if (agentY < targetY) {
                                //check if targetX <= squareX <= agentX and agentY <= squareY <= targetY
                                if (squareX <= agentX && squareX >= targetX && squareY >= agentY && squareY <= targetY) {
                                    vectorSquare.add(square);
                                }
                            } else {
                                //check if targetX <= squareX <= agentX and targetY <= squareY <= agentY
                                if (squareX <= agentX && squareX >= targetX && squareY <= agentY && squareY >= targetY) {
                                    vectorSquare.add(square);
                                }
                            }
                        }
                    }
                }
            }
            // iterate over all squares and remove everything behind walls
            for(Square square : vectorSquare){
                double squareX = 0;
                double squareY = 0;
                if(square.getType().equals("Wall")){

                    for(Square square2 : vectorSquare) {
                        double square2X = 0;
                        double square2Y = 0;
                        if (agentX < squareX) {
                            if (agentY < squareY) {
                                //check if agentX <= squareX <= targetX and agentY <= squareY <= targetY
                                if (!(square2X >= agentX && square2X <= squareX && square2Y >= agentY && square2Y <= squareY)) {
                                    vectorSquare.remove(square2);
                                }
                            } else {
                                //check if agentX <= squareX <= targetX and targetY <= squareY <= agentY
                                if (!(square2X >= agentX && square2X <= squareX && square2Y <= agentY && square2Y >= squareY)){
                                    vectorSquare.remove(square2);
                                }
                            }
                        } else {
                            if (agentY < squareY) {
                                //check if targetX <= squareX <= agentX and agentY <= squareY <= targetY
                                if (!(square2X <= agentX && square2X >= squareX && square2Y >= agentY && square2Y <= squareY)) {
                                    vectorSquare.remove(square2);
                                }
                            } else {
                                //check if targetX <= squareX <= agentX and targetY <= squareY <= agentY
                                if (!(square2X <= agentX && square2X >= squareX && square2Y <= agentY && square2Y >= squareY)) {
                                    vectorSquare.remove(square2);
                                }
                            }
                        }
                    }
                }
            }

            visibleSquares.addAll(vectorSquare);
            System.out.println("size of list: " + visibleSquares.size());
            for(Square square : visibleSquares){
                System.out.println("Square: " + square.toString());
            }

            System.out.println("agent x: " + agentX + ", agent y: " + agentY);
            System.out.println("the direction : " + agentDirection);

        }


    }
}







