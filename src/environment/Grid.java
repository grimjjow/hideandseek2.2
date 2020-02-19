package environment;

import agent.Agent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    public ArrayList<Square> squares = new ArrayList<>();

    private Environment env;
    public static final int SQUARE_MEASURE = 30;

    public Grid(Environment env) {
        for (Area area : env.getAreas()) {
            this.squares.addAll(area.createSquares());
        }
        System.out.println(this.squares.toString());
        this.env = env;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        for (Square square : this.squares) {
            square.setType();
            for (Agent agent : this.env.getAgents()) {
                if (agent.getPosition().isIn(square)) {
                    square.setType("Agent");
                    square.explored = true;
                }
            }
            Rectangle rectangle = new Rectangle(
                    (int) square.getCoordinates().x,
                    (int) square.getCoordinates().y,
                    SQUARE_MEASURE,
                    SQUARE_MEASURE
            );
            g2.setPaint(Color.black);
            g2.draw(rectangle);
            if (square.explored) {
                g2.setPaint(Color.gray);
                g2.fill(rectangle);
            }

            switch (square.getType()) {
                case "Map":
                    if (!square.explored) {
                        g2.setPaint(Color.white);
                        g2.fill(rectangle);
                    }
                    break;
                case "Wall":
                    g2.setPaint(Color.darkGray);
                    g2.fill(rectangle);
                    break;
                case "Agent":
                    g2.setPaint(Color.red);
                    g2.fill(rectangle);
                    break;
            }
        }
    }
}
