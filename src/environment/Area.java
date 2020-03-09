package environment;

import assets.Vector;

import java.util.ArrayList;

public abstract class Area {

    public final double leftB, rightB, topB, bottomB;

    public String type;

    Area(double leftB,
         double topB,
         double rightB,
         double bottomB) {
        this.leftB = Math.min(leftB, rightB);
        this.rightB = Math.max(leftB, rightB);
        //BUG HERE!!! OOPSIEE make tempBoundaries, see Reader class for correct implementation
        this.topB = Math.max(topB, bottomB);
        this.bottomB = Math.min(bottomB, topB);
        this.type = "" + (this.getClass().getSimpleName());
    }
    //needs an getBoundaries method to be called from Reader!
    public double getWidth() {
        return rightB - leftB;
    }

    public double getHeight() {
        return bottomB - topB;
    }

    public String getType() {
        return this.type;
    }

    public Vector[] getAngles() {
        return new Vector[]{new Vector(leftB, topB), new Vector(rightB, bottomB)};
    }

    public double getTopB() {
        return this.topB;
    }

    public double getBottomB() {
        return this.bottomB;
    }

    public double getLeftB() {
        return this.leftB;
    }

    public double getRightB() {
        return this.rightB;
    }

    public ArrayList<Square> createSquares() {
        ArrayList<Square> squares = new ArrayList<>();
        for (int i = (int)leftB; i < rightB; i+=Grid.SQUARE_MEASURE) {
            for (int j = (int)topB; j < bottomB; j+=Grid.SQUARE_MEASURE) {
                Square square = new Square(i, j, this);
                square.setType(this.type);
                squares.add(square);
            }
        }
        return squares;
    }
}
