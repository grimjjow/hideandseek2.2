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
    private boolean left;
    private int counter = 0;

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
        if(memory == null)
            this.memory = new int[(this.env.getGrid().getHeight()/30 + 1)][(this.env.getGrid().getWidth() / 30)];

        // initalizing border walls as 2
        for( int i =0; i<memory.length;i++){
           memory[i][0] = 2;
           memory[i][23] = 2;
        }
        for(int j = 0; j<memory[0].length;j++){
            memory[0][j] = 2;
            memory[23][j] = 2;
        }

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
            case 2:
                // find direction
                findDirection();
                statecounter = 3;
                break;

            case 3:
                Square currentSquare = findSquare(getPosition());
                Square nextSquare = findSquare(this.futurePosition());

                if(spaceExplorer() && currentSquare!=nextSquare){
                   statecounter = 3;
                }
                else{
                    statecounter = 2;
                }
                if ((cornerCoords[0] == 1 && cornerCoords[1] == 1 && currentSquare.getCoordinates().x / 30 == 1 && currentSquare.getCoordinates().y / 30 == 22)
                        || (cornerCoords[0] == 22 && cornerCoords[1] == 1 && currentSquare.getCoordinates().x / 30 == 22 && currentSquare.getCoordinates().y / 30 == 22)
                        || (cornerCoords[0] == 1 && cornerCoords[1] == 22 && currentSquare.getCoordinates().x / 30 == 1 && currentSquare.getCoordinates().y / 30 == 1)
                        || (cornerCoords[0] == 22 && cornerCoords[1] == 22 && currentSquare.getCoordinates().x / 30 == 22 && currentSquare.getCoordinates().y / 30 == 1)){

                    boolean check = false;

                    for (int i = 0; i < memory.length; i++) {
                        for (int j = 0; j < memory[i].length; j++) {

                            if (memory[i][j] == 0) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {
                            break;
                        }
                    }
                    if (check) {
                        statecounter = 4;
                    } else {
                        statecounter = 5;
                    }
               }
                break;

            case 4:
                break;

            case 5:
                System.out.println("Space explored.");
                this.baseSpeed = 0;
                break;
        }

        this.position = this.futurePosition();
        memory[(int) ((findSquare(this.position).getCoordinates().y / 30))][(int) ((findSquare(this.position).getCoordinates().x / 30))] = 1;

        for (int i = 0; i < memory.length; i++)
            System.out.println(Arrays.toString(memory[i]));
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

    /**
     *
     * @return int[] of the coordinates of the nearest corner
     */

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
                left = false;
            }
            else{
                this.direction = Math.PI;
                left = true;
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
        System.out.println("corner reached");
        return true;
    }

    public void findDirection(){

        // left corner
        if(left){
            this.direction = 0;
        } else{
            this.direction = Math.PI;
        }

    }

    public void moveUpDown(){

        // bottom corner
        if(this.cornerCoords[1] == 22){
            this.direction = 3*Math.PI/2;
        } else{
            this.direction = Math.PI/2;
        }
    }

    public boolean spaceExplorer(){

        Square temp = findSquare(this.futurePosition());

        if(!temp.walkable || this.direction == 3*Math.PI/2 || this.direction == Math.PI/2){

            if(!temp.walkable){
                memory[(int) (temp.getCoordinates().y/30)][(int) (temp.getCoordinates().x/30)] = 2;
            }

            if(left){
                left = false;
            }
            else{
                left = true;
            }
            // bottom corner
            if(this.cornerCoords[1] == 22){
                this.direction = 3*Math.PI/2;
            } else{
                this.direction = Math.PI/2;
            }
            return true;
        }

        return false;
    }
}
