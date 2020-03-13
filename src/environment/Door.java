package environment;

public class Door extends Area{
    boolean locked;
    boolean closed;

    public Door(double leftB,
                double topB,
                double rightB,
                double bottomB,
                boolean locked,
                boolean closed) {
        super(leftB,topB,rightB,bottomB);
        this.locked = locked;
        this.closed = closed;
    }
    //TODO slow agent down when approaching a door
    /*
    Return the state to know if it is walkable,
    Returns boolean, If true then it is walkable, door is open and unlocked
    else returns false and is not walkable
     */
    public boolean getState(){
        return !locked && !closed;
    }
    // Interact with door to open or close it
    public void InteractDoor(){
        if(closed && locked){
            System.out.println("Door is locked, Cannot open!");
        }
        else if(closed){
            closed = false;
        }
        else if(!closed){
            closed = true;
        }
    }
}
