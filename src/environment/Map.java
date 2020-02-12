package environment;

/**
 *
 * refers to everything except obstacles
 */

public class Map extends Area {

    public String areaType;

    public Map(double leftB,
               double topB,
               double rightB,
               double bottomB) {
        super(leftB, topB, rightB, bottomB);
    }

}
