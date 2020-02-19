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
    	
    	Reader a = new Reader();
    	a.readBufferedFile("C:\\Users\\Rudyw\\OneDrive\\Bureaublad\\hideandseek2.2-master\\src\\test.txt");
    	
    	 /* File file = new File("C:\\Users\\Rudyw\\OneDrive\\Bureaublad\\hideandseek2.2-master\\src\\test.txt"); 
    	  
    	  @SuppressWarnings("resource")
    	  BufferedReader br = new BufferedReader(new FileReader(file)); 
    	  
    	  String st = null; 
    	  String[] array = new String[1000];
    	  int x = 0;
    	  int wallCount = 0;
    	  int teleportCount = 0;
    	  int shadedCount = 0;
    	  int textureCount = 0;
    	  while ((st = br.readLine()) != null) {
    		  if (st.contains("wall")){
    			  wallCount++;
    		  }
    		  if (st.contains("teleport")){
    			  teleportCount++;
    		  }
    		  if (st.contains("shaded")){
    			  shadedCount++;
    		  }
    		  if (st.contains("texture")){
    			  textureCount++;
    		  }
    		 //System.out.println(st.substring(st.lastIndexOf("=") + 2)); 
    		 array[x] = st.substring(st.lastIndexOf("=") + 2);
    		 x++;
    	  }
    	  
    	  GameInfo a = new GameInfo(array,wallCount,teleportCount,shadedCount,textureCount);
    	  System.out.println(a.getNumIntruders() + " " + Arrays.toString(a.convertTargetArea()));
    	  
    	  */
    	/*  
        FunctionalEnvironment se = new FunctionalEnvironment();
        GraphicalEnvironment ge = new GraphicalEnvironment(se);
        Thread t1 = new Thread(se);
        Thread t2 = new Thread(ge);
        t1.start();
        t2.start();
        */
    }
}
