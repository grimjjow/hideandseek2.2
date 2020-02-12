import environment.GraphicalEnvironment;
import environment.FunctionalEnvironment;

public class RunSimulation {

    public static void main(String[] args) {
        FunctionalEnvironment se = new FunctionalEnvironment();
        GraphicalEnvironment ge = new GraphicalEnvironment(se);
        Thread t1 = new Thread(se);
        Thread t2 = new Thread(ge);
        t1.start();
        t2.start();
    }
}
