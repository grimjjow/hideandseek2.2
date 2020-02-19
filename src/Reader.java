import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import environment.GameInfo;

public class Reader extends Buffer {
    static String FILE_NAME = "C:\\Temp\\input.txt";

    public Reader() {
        super();
    }

    public Reader(File file) {
        super(file);
    }

    public Reader(String FILE_NAME){
        this.FILE_NAME = FILE_NAME;
    }

    void readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)){
            while (scanner.hasNextLine()){
                //process each line in some way
                log(scanner.nextLine());
            }
        }
    }

    void readBufferedFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line = null;
            while ((line = reader.readLine()) != null) {
                //process each line in some way
                log(line);
            }
        }
        //Creating file
        File file = new File(fileName); 
  	  
        //Creating second BufferedReader
        @SuppressWarnings("resource")
	  	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  	  
        //Creating variables
	  	  String st = null; 
	  	  String[] array = new String[1000]; //Array to save substrings
	  	  int x = 0; //Index for array
	  	  //Counters for variables that can have multiples
	  	  int wallCount = 0;
	  	  int teleportCount = 0;
	  	  int shadedCount = 0;
	  	  int textureCount = 0;
	  	  //Reading through the file
	  	  while ((st = br.readLine()) != null) {
	  		  //Adding to the counters
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
	  		  //Adding every substring after the equal sign of each line to the array
	  		 array[x] = st.substring(st.lastIndexOf("=") + 2);
	  		 //Incrementing index
	  		 x++;
	  	  }
	  	  
	  	  //Test
	  	  GameInfo a = new GameInfo(array,wallCount,teleportCount,shadedCount,textureCount);
	  	  System.out.println(a.getBaseSpeedIntruder() + " " + Arrays.toString(a.convertTargetArea()));
	  	  
    }
}
