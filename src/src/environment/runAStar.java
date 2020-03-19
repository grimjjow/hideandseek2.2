package environment;

import java.util.ArrayList;

import agent.Guard;

public class runAStar
{
    //Variables for pathfinding
    public Pathfinding AStar; //Creating pathfinding object
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int width;
    public int height;
    public float[] origin = new float[] {0,0,0};

    public runAStar()
    {
    	width = Environment.MAP_WIDTH;
    	height = Environment.MAP_HEIGHT;
    	
        AStar = new Pathfinding(width, height, origin); //Initializing pathfinding object
    }
    
    public ArrayList<PathNode> getPath(double coorX, double coorY, double coorX2, double coorY2) {
    	float[] position = new float[] {(float) coorX,(float) coorY};
        float[] position2 = new float[] {(float) coorX2,(float) coorY2};
        startX = AStar.grid.getX(position);
        startY = AStar.grid.getY(position);
        endX = AStar.grid.getX(position2);
        endY = AStar.grid.getY(position2); 
    	
    	ArrayList<PathNode> path = AStar.findPath(startX, startY, endX, endY); //List containing final path
    	return path;
    }
}