package agent;

import environment.Environment;
import environment.Square;
import assets.Vector;

import java.util.Random;

/**
 *
 * create a guard agent
 */


public class Guard implements Agent {

    public Vector position;
    private double direction;
    private double baseSpeed = 1.0;
    private String name;
    private Environment env;
    private double visionRadius = 5.0;

    public Guard(String name,
                 Vector position,
                 double direction,
                 double visionRadius,
                 Environment env) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.visionRadius = visionRadius;
        this.env = env;
    }

    public Vector futurePosition() {
        return new Vector(
                this.position.x + Math.cos(this.direction) * this.baseSpeed * env.TIMESTEP,
                this.position.y + Math.sin(this.direction) * this.baseSpeed * env.TIMESTEP
        );
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void updatePosition() {
        boolean check = true;

        while(check) {
            for (Square square : this.env.getGrid().squares) {
                if (!square.walkable)
                    if (this.futurePosition().isIn(square)) {
                        this.direction += Math.PI / 2;
                        check = true;
                        break;
                        // System.out.println("The Guard tries to do an illegal move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
                    } else {
                        check = false;
                    }
                /*if (square.explored) {
                    if (this.futurePosition().isIn(square)) {
                        Random r = new Random();
                        if (r.nextBoolean()) {
                            this.direction += Math.PI / 2 * Math.pow(-1, Math.round(Math.random()));
                            check = true;
                            break;
                        }
                        // System.out.println("The Guard tries to do an illegal move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
                    } else {
                        check = false;
                    }
                }*/
            }
        }

        System.out.println("The Guard has move: x -> " + this.futurePosition().x + " y -> " + this.futurePosition().y);
        this.position = this.futurePosition();
    }

    public double getX() {
        return this.position.x;
    }

    public double getY() {
        return this.position.y;
    }

    public String getName() {
        return name;
    }

    public double getDirection() {
        return direction;
    }

}
