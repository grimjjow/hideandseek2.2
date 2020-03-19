package environment;

import agent.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GraphicalEnvironment extends JFrame implements Environment, Runnable {

    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Area> areas = new ArrayList<>();
    private Environment env;
    private Grid grid;
    //add functionality
    private JPanel buttonsPanel = new JPanel();
    private JButton butPause = new JButton("Pause");
    private JButton butRestart = new JButton("Restart");
    //create boolean to pause, Starts false
    //Boolean Paused = false;


    public GraphicalEnvironment(Environment env) {
        this.env = env;
        this.grid = new Grid(this.env);
        this.env.setGrid(this.grid);
        add(this.grid);

        //add buttons to panel
        buttonsPanel.add(butPause);
        buttonsPanel.add(butRestart);
        add(buttonsPanel, BorderLayout.WEST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAP_WIDTH, MAP_HEIGHT + 20);

        setLocationByPlatform(true);
        setVisible(true);
    }
    //TODO implement pauze thread using wait notify logic
    /*
    private void setActionlistener(){
        butPauze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Paused ==false){Paused = true;
                System.out.println("Paused = true");}
                else {
                    Paused = false;
                    System.out.println("Paused = false");
                }
            }
        });
    }
     */

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
