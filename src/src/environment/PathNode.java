package environment;

public class PathNode
{
    //Variables for its grid, position and if its walkable
    private Grid1 grid;
    public int x;
    public int y;
    public boolean walkable;

    //Variables for the costs
    public int gCost; //Distance from Start to This
    public int hCost; //Distance from This to End
    public int fCost; //gCost + hCost

    public PathNode cameFromNode; //Parent-node

    public PathNode(Grid1 grid, int x, int y)
    {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.walkable = true;
    }

    public void calcFCost() //Method to calculate fCost
    {
        fCost = gCost + hCost;
    }
}
