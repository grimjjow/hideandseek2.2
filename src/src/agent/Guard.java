package agent;

import environment.Environment;
import environment.PathNode;
import environment.Square;
import environment.runAStar;
import assets.Vector;

import java.util.ArrayList;
import java.util.Random;

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
    private int justWalk = 0;
    private int countRotations = 0;
    
    //Variables for AStar
    private double AStarStartX;
    private double AStarStartY;
    private double AStarEndX;
    private double AStarEndY;
    private ArrayList<PathNode> path;
    public int checkCount = 0;

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

    public Vector futurePosition(double direction) {
        return new Vector(
                this.position.x + Math.cos(direction) * this.baseSpeed * env.TIMESTEP,
                this.position.y + Math.sin(direction) * this.baseSpeed * env.TIMESTEP
        );
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void updatePosition() {
        boolean check = true;
        Square currentSquare = null;
        Square nextSquare = null;
        
        runAStar A = new runAStar();

		for (Square square : this.env.getGrid().squares) { //Check all squares
            if (!square.walkable) { //If walkable
            	PathNode pathNode = A.AStar.grid.getPathNode((int)square.getCoordinates().getX(), (int)square.getCoordinates().getY());
                pathNode.walkable = false;
            }
        }

        while(check) {
            for (Square square : this.env.getGrid().squares) {
                if (!square.walkable)
                    if (this.futurePosition().isIn(square)) {
                        this.direction += Math.PI / 2;
                        /*if(checkCount >= 4) { //If surroundings have been checked 4 or more times
                        	for (Square squareBoi : this.env.getGrid().squares) { //Check all squares
                                if (squareBoi.walkable) { //If walkable
                                	if(!visitedSquares.contains(squareBoi)) { //If not visited yet
                                		AStarEndX = squareBoi.getCoordinates().getX(); // Set end coordinates for AStar
                                		AStarEndY = squareBoi.getCoordinates().getY();
                                		break;
                                	}
                                }
                            }
                        	AStarStartX = square.getCoordinates().getX();
                        	AStarStartY = square.getCoordinates().getY();
                        	path = A.getPath(AStarStartX,AStarStartY,AStarEndX,AStarEndY);
                        	System.out.println(AStarStartX + " " + AStarStartY + " " + AStarEndX + " " + AStarEndY);
                        	for(int p = 0;p<path.size();p++) {
                        		double direct = 0.5*Math.PI;
                        		if(p>1) {
                        			if(path.get(p).x > path.get(p-1).x) { //right
                        				direct = 0;
                        			}
                        			else if(path.get(p).x < path.get(p-1).x) { //left
                        				direct = Math.PI;
                        			}
                        			else if(path.get(p).y > path.get(p-1).y) { //up
                        				direct = 1.5*Math.PI;
                        			}
                        			else if(path.get(p).y < path.get(p-1).y) { //down
                        				direct = 0.5*Math.PI;
                        			}
                        		}
                        		this.position = this.futurePosition(direct);
                        		System.out.println(path.get(p).x + ", " + path.get(p).y);
                        	}
                        	checkCount = 0;
                        }*/
                        checkCount++;
                        check = true;
                        break;
                        // System.out.println("The Guard tries to do an illegal move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
                    } else {
                    	//checkCount = 0;
                        check = false;
                    }
                /*if (square.explored) {
                    if (this.futurePosition().isIn(square)) {
                        Random r = new Random();
                        if (r.nextBoolean()) {
                            this.direction += Math.PI / 2 * Math.pow(-1, Math.round(Math.random()));
                            check = true;
                            break;
                        }
                        // System.out.println("The Guard tries to do an illegal move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
                    } else {
                        check = false;
                    }
                }*/
                currentSquare = findSquare(getPosition());
                nextSquare = findSquare(this.futurePosition());
                if(this.justWalk == 1) {
                    this.countRotations = 0;
                    //System.out.println("Here");
                    if(!nextSquare.explored && nextSquare.walkable) {
                        System.out.println("Good");
                        this.justWalk = 2;
                    } else {
                        //System.out.println("Not yet");
                        Random r = new Random();
                        if(r.nextInt(10000) == 53)
                            this.direction += Math.PI / 2;
                    }
                } else {
                    // we reach a unexplored square
                    if(this.justWalk == 2) {
                        this.direction -= Math.PI;
                        System.out.println("odsnfoöugf");
                        this.justWalk = 0;
                    }
                    if (countRotations <= 4) {
                        if (currentSquare != nextSquare) {
                            if (nextSquare.explored) {
                                this.direction += Math.PI / 2;
                                countRotations++;
                                check = true;
                                break;
                            } else {
                                this.countRotations = 0;
                                check = false;
                            }
                        }
                    } else {
                        this.justWalk = 1;
                    }
                }
                /*if (currentSquare != nextSquare) {
                    if (nextSquare.explored) {
                        this.direction += Math.PI / 2;
                        countRotations++;
                        System.out.println("counter: " + countRotations);
                        check = true;
                        break;
                    } else {
                        check = false;
                    }
                    // TODO: if all squares are visited: solve! -- no idea yet (worst case: random only not into a wall)
                }
                    /*if (countRotations >= 4) {
                        System.out.println("it goes into if");
                        Square temp = findSquare(this.futurePosition());
                        while(temp.explored) {

                            this.direction += Math.PI / 2 * Math.pow(-1, Math.round(Math.random()));
                            if(!findSquare(this.futurePosition()).explored){
                                break;
                            }

                        }
                }*/
            }
        }

        // create a list that stores visited squares
        visitedSquares.add(nextSquare);
        visitedSquares = deleteDoubles(visitedSquares);
        
        this.position = this.futurePosition();

        if(allIsExplored()){
            System.out.println("THE SPACE IS EXPLORED");
        }
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
     *
     * @param position
     * @return Square, which has this position
     */

    public Square findSquare(Vector position){

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


        for(Square square : this.env.getGrid().squares){

            if(square.getCoordinates().x == x && square.getCoordinates().y == y){
                correctSquare = square;
            }
        }

        return correctSquare;
    }


    /**
     *
     * removes duplicates from the list of squares
     */
    public ArrayList<Square> deleteDoubles(ArrayList<Square> squares){

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
     *
     * checks if everything is explored
     */
    public boolean allIsExplored(){

        boolean allCovered = true;

        for(Square square: this.env.getGrid().squares){

            if(!square.explored){
                return false;
            }
        }
        return allCovered;
    }
}
