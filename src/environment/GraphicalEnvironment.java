package environment;

import agent.Agent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicalEnvironment extends JFrame implements Environment, Runnable {

    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Area> areas = new ArrayList<>();
    private Environment env;
    private Grid grid;

    public GraphicalEnvironment(Environment env) {
        this.env = env;
        this.grid = new Grid(this.env);
        this.env.setGrid(this.grid);
        add(this.grid);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAP_WIDTH, MAP_HEIGHT + 20);

        setLocationByPlatform(true);
        setVisible(true);
    }

    @Override
    public void updateState() {
        this.agents = env.getAgents();
        this.areas = env.getAreas();
        revalidate();
        repaint();
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
    public void run() {
        do {
            updateState();
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);
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
