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
                MAP_HEIGHT - Grid.SQUARE_MEASURE
        );

        this.areas.add(new Wall(
                0,
                0,
                Grid.SQUARE_MEASURE,
                MAP_HEIGHT)
        ); // left wall
        this.areas.add(new Wall(
                Grid.SQUARE_MEASURE,
                0,
                MAP_WIDTH,
                Grid.SQUARE_MEASURE)
        ); // top wall
        this.areas.add(new Wall(
                MAP_WIDTH - Grid.SQUARE_MEASURE,
                Grid.SQUARE_MEASURE,
                MAP_WIDTH,
                MAP_HEIGHT)
        ); // right wall
        this.areas.add(new Wall(
                Grid.SQUARE_MEASURE,
                MAP_HEIGHT - Grid.SQUARE_MEASURE,
                MAP_WIDTH - Grid.SQUARE_MEASURE,
                MAP_HEIGHT)
        ); // bottom wall

        this.areas.add(this.map);

        this.areas.add(new Wall(
                Grid.SQUARE_MEASURE,
                150,
                150,
                120)
        ); // bottom wall
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
