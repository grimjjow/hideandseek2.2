package agent;

import assets.Vector;

public interface Agent {

    /**
     * whatever algorithm you choose to calculate next move will affect this method
     * this method should update current position of the agent
     */
    void updatePosition();


    /**
     *
     * @return getX coordinate of the agent
     */
    double getX();

    /**
     *
     * @return getY coordinate of the agent
     */
    double getY();

    /**
     *
     * @return current position of the agent
     */
    Vector getPosition();

    /**
     *
     * @return name of the agent(not necessary thing, but might come in handy)
     */
    String getName();

}
