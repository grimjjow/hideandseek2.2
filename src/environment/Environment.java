package environment;

import agent.Agent;

import java.util.ArrayList;

public interface Environment {

    double TIMESTEP = 10.0;
    int MAP_WIDTH = 720;
    int MAP_HEIGHT = 720;
    int WAIT = 30;

    /**
     *
     * update state of the environment:
     * all agents positions
     */
    void updateState();


    /**
     *
     * @return list of areas stored in the environment
     */
    ArrayList<Area> getAreas();


    /**
     *
     * @return list  of agents stored in the environment
     */
    ArrayList<Agent> getAgents();


    /**
     * calls this.updateState(); and whatever else you want
     */
    void run();


    /**
     *
     * @return returns current grid
     */
    Grid getGrid();

    /**
     * sets grid
     */
    void setGrid(Grid grid);



}
