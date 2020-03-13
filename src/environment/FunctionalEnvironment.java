package environment;

import agent.Agent;
import agent.Guard;
import assets.Vector;

import java.util.ArrayList;

public class FunctionalEnvironment implements Environment, Runnable {

    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Area> areas = new ArrayList<>();
    private Grid grid;

    private Map map;

    public FunctionalEnvironment() {
        setup();
        spawnGuards(this.map, 1);
    }

    @Override
    public void updateState() {
        for (Agent agent : agents) {

            agent.updatePosition();
        }
    }

    public void spawnGuards(Area area, int n) {
        for (int i = 0; i < n; i++) {
            this.agents.add(new Guard(
                    Integer.toString(i + 1),
                    new Vector(
                            area.getLeftB() + area.getWidth() * Math.random(),
                            area.getTopB() + area.getHeight() * Math.random()),
                    0,
                    5.0,
                    this)
            );
        }
    }

    /**
     * method creates all areas, such as walls, doors, windows etc. and map itself
     * problem: (1,1) belongs to (0,0) & (1,1) square
     * TODO: fix
     */
    public void setup() {
        this.map = new Map(
                Grid.SQUARE_MEASURE,
                Grid.SQUARE_MEASURE,
                MAP_WIDTH - Grid.SQUARE_MEASURE,
                MAP_HEIGHT +20- Grid.SQUARE_MEASURE
        );

        this.areas.add(new Wall(
                0,
                0,
                Grid.SQUARE_MEASURE,
                MAP_HEIGHT+20)
        ); // left wall
        this.areas.add(new Wall(
                Grid.SQUARE_MEASURE,
                0,
                MAP_WIDTH,
                Grid.SQUARE_MEASURE)
        ); // top wall
        this.areas.add(new Wall(
                MAP_WIDTH-10,
                Grid.SQUARE_MEASURE,
                MAP_WIDTH + 20,
                MAP_HEIGHT+20)
        ); // right wall
        this.areas.add(new Wall(
                Grid.SQUARE_MEASURE,
                MAP_HEIGHT+20 - Grid.SQUARE_MEASURE,
                MAP_WIDTH - Grid.SQUARE_MEASURE,
                MAP_HEIGHT+20)
        ); // bottom wall


        this.areas.add(this.map);
        //testhouse
        this.areas.add(new Door(90,90,90+Grid.SQUARE_MEASURE,90+Grid.SQUARE_MEASURE,
                true,true));
        this.areas.add(new Wall(120,90,300,120));
        this.areas.add(new Door(300,90,300+Grid.SQUARE_MEASURE,90+Grid.SQUARE_MEASURE,
                false,false));
        this.areas.add(new Wall(60,0,90,120));
        this.areas.add(new Wall(330,0,360,120));

        //testdoor if it is walkable
        //this.areas.add(new Door(30,300,60,330,false,false));

        //test SentryTower
        this.areas.add(new SentryTower(330,330,360,360,false));
    }

    @Override
    public void run() {
        do {
            this.updateState();
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    @Override
    public ArrayList<Area> getAreas() {
        return areas;
    }

    @Override
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
