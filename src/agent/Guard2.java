package agent;

import environment.Environment;
import environment.Square;
import assets.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * create a guard agent
 */


public class Guard2 implements Agent {

    public Vector position;
    private double direction;
    private double baseSpeed = 1.0;
    private String name;
    private Environment env;
    private double visionRadius = 5.0;
    private int justWalk = 0;
    private int countRotations = 0;
    private int[][] memory;

    private ArrayList<Square> visitedSquares = new ArrayList<>();

    public Guard2(String name,
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
        if(memory == null)
            this.memory = new int[(this.env.getGrid().getHeight()/30 + 1)][(this.env.getGrid().getWidth() / 30)];

        boolean check = true;
        Square currentSquare = null;
        Square nextSquare = null;

        while (check) {
            for (Square square : this.env.getGrid().squares) {
                if (!square.walkable) {
                    memory[(int) (square.getCoordinates().y/30)][(int) (square.getCoordinates().x/30)] = 2;
                    if (this.futurePosition().isIn(square)) {
                        System.out.println("test");
                        //this.direction += Math.PI / 2;
                        check = true;
                        break;
                        // System.out.println("The Guard tries to do an illegal move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
                    } else {
                        check = false;
                    }
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
                if (this.justWalk == 1) {
                    this.countRotations = 0;
                    //System.out.println("Here");
                    if (!nextSquare.explored && nextSquare.walkable) {
                        System.out.println("Good");
                        this.justWalk = 2;
                    } else {
                        //System.out.println("Not yet");
                        Random r = new Random();
                        if (r.nextInt(10000) == 53)
                            this.direction += Math.PI / 2;
                    }
                } else {
                    // we reach a unexplored square
                    if (this.justWalk == 2) {
                        this.direction -= Math.PI;
                        System.out.println("odsnfoöugf");
                        this.justWalk = 0;
                    }
                    if (countRotations <= 4) {
                        if (currentSquare != nextSquare) {
                            if (nextSquare.explored || !nextSquare.walkable) {
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
        memory[(int) ((findSquare(this.position).getCoordinates().y / 30))][(int) ((findSquare(this.position).getCoordinates().x / 30))] = 1;

        for (int i = 0; i < memory.length; i++)
            System.out.println(Arrays.toString(memory[i]));

        if (allIsExplored()) {
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
}