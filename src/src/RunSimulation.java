import environment.GraphicalEnvironment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import environment.FunctionalEnvironment;
import environment.GameInfo;

public class RunSimulation {

    public static void main(String[] args) throws IOException {
    	
    	//Reader a = new Reader();
    	//a.readBufferedFile("C:\\\\Users\\\\Rudyw\\\\OneDrive\\\\Bureaublad\\\\hideandseek2.2-master\\\\src\\\\test.txt");

    	
        FunctionalEnvironment se = new FunctionalEnvironment();
        GraphicalEnvironment ge = new GraphicalEnvironment(se);
        Thread t1 = new Thread(se);
        Thread t2 = new Thread(ge);
        t1.start();
        t2.start();
        
    }
}
