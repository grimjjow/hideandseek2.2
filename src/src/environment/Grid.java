package environment;

import agent.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
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
            g2.setColor(Color.red);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 7));
            //g2.drawString( "(" + (int)square.getCoordinates().x + "," + (int)square.getCoordinates().y + ")", (int)square.getCoordinates().x, (int)square.getCoordinates().y);
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
            Ellipse2D.Double circle = new Ellipse2D.Double(
                    (int) square.getCoordinates().x,
                    (int) square.getCoordinates().y,
                    SQUARE_MEASURE,SQUARE_MEASURE
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
                    //Ellipse2D.Double agent = new Ellipse2D.Double(1,1);
                    g2.setPaint(Color.red);
                    g2.fill(circle);
                    break;
                case "Door":
                    g2.setPaint(Color.ORANGE);
                    g2.fill(rectangle);
                    break;
                case "SentryTower":
                    g2.setPaint(Color.BLUE);
                    g2.fill(circle);
                    break;
            }
        }
    }
}