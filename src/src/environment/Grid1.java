package environment;

public class Grid1
{
    //Basic grid variables
    public int width;
    public int height;
    public float cellSize;
    private float[] originPosition = new float[3];

    //2D array containing grid info
    public PathNode[][] gridArray;

    public Grid1(int width, int height, float cellSize, float[] originPosition)
    {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.originPosition = originPosition;

        gridArray = new PathNode[width][height]; //Initializing gridArray with PathNode

        for (int x = 0; x < gridArray.length; x++)
        {
            for (int y = 0; y < gridArray[0].length; y++)
            {
                gridArray[x][y] = new PathNode(this,x,y); //Filling gridarray with PathNodes
            }
        }

    }

    public float[] getWorldPosition(int x, int y) //Returns Vector3 with position
    {
        return new float[] {x*cellSize+originPosition[0], y*cellSize+originPosition[1]};
    }

    public int getX(float[] worldPosition) //Returns X and Y with position
    {
        return (int)((worldPosition[0]-originPosition[0]) / cellSize);
    }
    public int getY(float[] worldPosition) //Returns X and Y with position
    {
        return (int)((worldPosition[1]-originPosition[1]) / cellSize);
    }

    public void setPathNode(int x, int y, PathNode value) //Set a PathNode using X and Y
    {
        if (x >= 0 && y >= 0 && x < width && y < height)
        {
            gridArray[x][y] = value;
        }
    }

    public void setPathNode(float[] worldPosition, PathNode value) //Set a PathNode using Vector3
    {
        int x, y;
        x = getX(worldPosition);
        y = getY(worldPosition);
        setPathNode(x, y, value);
    }

    public PathNode getPathNode(int x, int y) //Get a PathNode using X and Y
    {
        if (x >= 0 && y >= 0 && x < width && y < height)
        {
            return gridArray[x][y];
        }
        else
        {
            return null;
        }
    }

    public PathNode getPathNode(float[] worldPosition) //Get a PathNode using Vector 3
    {
        int x, y;
        x = getX(worldPosition);
        y = getY(worldPosition);
        if (x >= 0 && y >= 0 && x < width && y < height)
        {
            return gridArray[x][y];
        }
        else
        {
            return null;
        }
    }
}