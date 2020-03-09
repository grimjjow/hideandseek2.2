import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import environment.GameInfo;

public class Reader2 {
	
	GameInfo g; //Object that holds all the game information
	public File file; //Input file
	
	String st = null; //String that will contain read line
    String[] array = new String[1000]; //Array to save substrings
    int x = 0; //Index for array
    
    //Counters for variables that can have multiples
    int wallCount = 0;
    int teleportCount = 0;
    int shadedCount = 0;
    int textureCount = 0;
	
	
	public Reader2(String fileName) throws IOException {
		//Creating file
	    file = new File(fileName);
	
	    //Creating BufferedReader
	    @SuppressWarnings("resource")
	    BufferedReader br = new BufferedReader(new FileReader(file));
	
	    //Reading through the file
	    while ((st = br.readLine()) != null) {
	        //Adding to the counters
	        if (st.contains("wall")) {
	            wallCount++;
	        }
	        if (st.contains("teleport")) {
	            teleportCount++;
	        }
	        if (st.contains("shaded")) {
	            shadedCount++;
	        }
	        if (st.contains("texture")) {
	            textureCount++;
	        }
	        //Adding every substring after the equal sign of each line to the array
	        array[x] = st.substring(st.lastIndexOf("=") + 2);
	        //Incrementing index
	        x++;
	    }
	    //Constructing game information
	    g = new GameInfo(array, wallCount, teleportCount, shadedCount, textureCount);
	}
}
