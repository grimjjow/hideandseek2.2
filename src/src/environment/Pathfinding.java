package environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pathfinding
{
    //Variables for its grid, starting node and ending node
    public Grid1 grid;
    public PathNode startNode;
    public PathNode endNode;

    //Open and closed lists
    public ArrayList<PathNode> open; //Contains PathNodes that have calculated FCost but are not checked yet
    public ArrayList<PathNode> closed; //Contains PathNodes that have been checked

    //Costs for each step
    public int MOVE_DIAGONAL_COST = 14; //sqrt(200) = approx. 14
    public int MOVE_STRAIGHT_COST = 10;

    //Pointer pointing to the node its currently on
    public PathNode current;

    public Pathfinding(int width, int height, float[] origin)
    {
        grid = new Grid1(width, height, 30f, origin);
    }

    public ArrayList<PathNode> findPath(int startX, int startY, int endX, int endY)
    {
        //Initializing start and end node
        startNode = grid.getPathNode(startX, startY);
        endNode = grid.getPathNode(endX, endY);

        //Initializing open and closed lists
        open = new ArrayList<PathNode>(); //open contains only startNode
        open.add(startNode);
        closed = new ArrayList<PathNode>(); //closed is empty

        for (int x = 0; x < grid.width; x++)
        {
            for (int y = 0; y < grid.height; y++)
            {
                //Initializing the rest of the nodes
                PathNode pathNode = grid.getPathNode(x, y);
                pathNode.gCost = Integer.MAX_VALUE;
                pathNode.calcFCost();
                pathNode.cameFromNode = null;
            }
        }

        //calculating costs of startNode
        startNode.gCost = 0;
        startNode.hCost = calculateDistanceCost(startNode, endNode);
        startNode.calcFCost();

        //While open is not empty
        while (open.size() > 0) {
            current = getSmallestFCost(); //Current is PathNode in open with smallest fCost
            if (current.x == endNode.x && current.y == endNode.y)
            {
                return CalculatePath(endNode); //If current == endNode then return path
            }
            //Removing current from open and adding it to closed
            open.remove(current);
            closed.add(current);

            //foreach neighbor of current
            for (PathNode neighbor : getNeighborList())
            {
                if (closed.contains(neighbor) || !neighbor.walkable)
                {
                    continue; //If neighbor in closed or not walkable, skip it
                }
                else if ((calculateDistanceCost(current, neighbor) + current.gCost) < neighbor.gCost) //If new gCost is lower than old gCost
                {
                    //Replace costs
                    neighbor.gCost = calculateDistanceCost(current, neighbor) + current.gCost;
                    neighbor.hCost = calculateDistanceCost(neighbor, endNode);
                    neighbor.calcFCost();

                    //Set parent node
                    neighbor.cameFromNode = current;
                    
                }
                //If neighbor is not in open, add it to open
                if (!open.contains(neighbor))
                {
                    open.add(neighbor);
                }
            }
        }
        //If this is reached, nothing was returned meaning no path was found
        System.out.println("No path found");
        return null;

    }

    public ArrayList<PathNode> CalculatePath(PathNode endNode) //Method to calculate final path
    { 
        ArrayList<PathNode> path = new ArrayList<PathNode>(); //List containing final path
        path.add(endNode);
        PathNode currentNode = endNode;
        //Traverse parent nodes while there are and add parent node to path-list
        while (currentNode.cameFromNode != null)
        {
            path.add(currentNode.cameFromNode);
            currentNode = currentNode.cameFromNode;
        }
        Collections.reverse(path); //Reversing list
        return path;
    }

    public int calculateDistanceCost(PathNode a, PathNode b) //Calculate distance between two PathNodes
    {
        int xDistance = Math.abs(a.x - b.x);
        int yDistance = Math.abs(a.y - b.y);
        int remaining = Math.abs(xDistance - yDistance);
        return MOVE_DIAGONAL_COST * Math.min(xDistance, yDistance) + MOVE_STRAIGHT_COST * remaining; //Walk straight as much as it can and then walk diagonally
    }

    public PathNode getSmallestFCost() //Returns the PathNode in open with the smalles fCost
    {
        PathNode smallestFCostNode = open.get(0);
        for (int i = 0; i < open.size(); i++)
        {
            if (open.get(i).fCost < smallestFCostNode.fCost)
            {
                smallestFCostNode = open.get(i);
            }
        }
        return smallestFCostNode;
    }

    public List<PathNode> getNeighborList() //Retruns a list of all the neighbors of current
    {
        List<PathNode> neighborList = new ArrayList<PathNode>();
        if(current.x - 1 >= 0)
        {   //Left
            neighborList.add(grid.getPathNode(current.x - 1, current.y));
            //if(current.y + 1 < grid.height)
            //{   //TopLeft
            //    neighborList.add(grid.getPathNode(current.x - 1, current.y + 1));
            //}
            //if (current.y - 1 >= 0)
            //{   //BottomLeft
            //    neighborList.add(grid.getPathNode(current.x - 1, current.y - 1));
            //}
        }
        if (current.x + 1 < grid.width)
        {   //Right
            neighborList.add(grid.getPathNode(current.x + 1, current.y));
            //if (current.y + 1 < grid.height)
            //{   //TopRight
            //    neighborList.add(grid.getPathNode(current.x + 1, current.y + 1));
            //}
            //if (current.y - 1 >= 0)
            //{   //BottomRight
            //    neighborList.add(grid.getPathNode(current.x + 1, current.y - 1));
            //}
        }
        if (current.y + 1 < grid.height)
        {   //Top
            neighborList.add(grid.getPathNode(current.x, current.y + 1));
        }
        if (current.y - 1 >= 0)
        {   //Bottom
            neighborList.add(grid.getPathNode(current.x, current.y - 1));
        }
        return neighborList;
    }
}
