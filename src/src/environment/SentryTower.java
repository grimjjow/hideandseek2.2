package environment;

public class SentryTower extends Area {
    private boolean Occupied = false;
    public SentryTower(double leftB,
                       double topB,
                       double rightB,
                       double bottomB,
                       boolean Occupied){
        super(leftB, topB, rightB, bottomB);
        this.Occupied = Occupied;
    }
    public boolean GetOccupied(){
        return Occupied;
    }
    //Occupie Sentrytower
    public void Occupie(){
        Occupied = true;
    }
    //TODO add Sentrytower functionality
}
