package environment;

public class GameInfo { //Object class that contains all the map info
	//Variables
	String name;  				//String
	String gameFile;  			//String
	String gameMode;			//Int
	String height;				//Int
	String width;				//Int
	String scaling;				//Double
	String numGuards;			//Int
	String numIntruders;		//Int
	String baseSpeedIntruder;	//Double
	String sprintSpeedIntruder; //Double
	String baseSpeedGuard;      //Double
	String timestep;			//Double
	String targetArea;			//Int[]
	String spawnAreaIntruders;	//Int[]
	String spawnAreaGuards;		//Int[]
	String[] wall;				//Int[][]
	String[] teleport;			//Double[][]
	String[] shaded;			//Int[][]
	String[] texture;			//Int[][]
	
	//Contructor
	public GameInfo(String[] input, int wallCount, int teleportCount, int shadedCount, int textureCount) {
		//Initializing String arrays according to given length
		wall = new String[wallCount];
		teleport = new String[teleportCount];
		shaded = new String[shadedCount];
		texture= new String[textureCount];
		//Loop that assigns first 15 variables from input
		for(int i=0;i<15;i++) {
			switch (i) { 
			case 0:
				name = input[i];
				break;
	        case 1: 
	        	gameFile = input[i];
	            break; 
	        case 2: 
	        	gameMode = input[i];
	            break; 
	        case 3: 
	        	height = input[i];
	            break; 
	        case 4: 
	        	width = input[i];
	            break; 
	        case 5: 
	        	scaling = input[i];
	            break; 
	        case 6: 
	        	numGuards = input[i];
	            break; 
	        case 7: 
	        	numIntruders = input[i];
	            break; 
	        case 8: 
	        	baseSpeedIntruder = input[i];
	            break; 
	        case 9: 
	        	sprintSpeedIntruder = input[i];
	            break; 
	        case 10: 
	        	baseSpeedGuard = input[i];
	            break; 
	        case 11: 
	        	timestep = input[i];
	            break; 
	        case 12: 
	        	targetArea = input[i];
	            break; 
	        case 13: 
	        	spawnAreaIntruders = input[i];
	            break; 
	        case 14: 
	        	spawnAreaGuards = input[i];
	            break; 
	        default: 
	            break; 
	        } 
		}
		//Loops that assign String arrays from input
		for(int j=0;j<wallCount;j++) {
			wall[j] = input[j+15];
		}
		for(int j=0;j<teleportCount;j++) {
			teleport[j] = input[j+15+wallCount];
		}
		for(int j=0;j<shadedCount;j++) {
			shaded[j] = input[j+15+wallCount+teleportCount];
		}
		for(int j=0;j<textureCount;j++) {
			texture[j] = input[j+15+wallCount+teleportCount+shadedCount];
		}
	}	

	//Getters and Setters && Converters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGameFile() {
		return gameFile;
	}

	public void setGameFile(String gameFile) {
		this.gameFile = gameFile;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
	//String to int
	public int convertGameMode() {
		int result = Integer.parseInt(gameMode);
		return result;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	//String to int
	public int convertHeight() {
		int result = Integer.parseInt(height);
		return result;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	//String to int
	public int convertWidth() {
		int result = Integer.parseInt(width);
		return result;
	}
	
	public String getScaling() {
		return scaling;
	}

	public void setScaling(String scaling) {
		this.scaling = scaling;
	}
	
	//String to int
	public int convertScaling() {
		int result = Integer.parseInt(scaling);
		return result;
	}

	public String getNumGuards() {
		return numGuards;
	}

	public void setNumGuards(String numGuards) {
		this.numGuards = numGuards;
	}
	
	//String to int
	public int convertNumGuards() {
		int result = Integer.parseInt(numGuards);
		return result;
	}

	public String getNumIntruders() {
		return numIntruders;
	}

	public void setNumIntruders(String numIntruders) {
		this.numIntruders = numIntruders;
	}
	
	//String to int
	public int convertNumIntruders() {
		int result = Integer.parseInt(numIntruders);
		return result;
	}

	public String getBaseSpeedIntruder() {
		return baseSpeedIntruder;
	}

	public void setBaseSpeedIntruder(String baseSpeedIntruder) {
		this.baseSpeedIntruder = baseSpeedIntruder;
	}
	
	//String to double
	public double convertBaseSpeedIntruder() {
		double result = Double.parseDouble(numIntruders);
		return result;
	}

	public String getSprintSpeedIntruder() {
		return sprintSpeedIntruder;
	}

	public void setSprintSpeedIntruder(String sprintSpeedIntruder) {
		this.sprintSpeedIntruder = sprintSpeedIntruder;
	}
	
	//String to double
	public double convertSprintSpeedIntruder() {
		double result = Double.parseDouble(sprintSpeedIntruder);
		return result;
	}
	
	public String getBaseSpeedGuard() {
		return baseSpeedGuard;
	}

	public void setBaseSpeedGuard(String baseSpeedGuard) {
		this.baseSpeedGuard = baseSpeedGuard;
	}
	
	//String to double
	public double convertBaseSpeedGuard() {
		double result = Double.parseDouble(baseSpeedGuard);
		return result;
	}

	public String getTimestep() {
		return timestep;
	}

	public void setTimestep(String timestep) {
		this.timestep = timestep;
	}
	
	//String to double
	public double convertTimestep() {
		double result = Double.parseDouble(timestep);
		return result;
	}

	public String getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
	
	//String to int[]
	public int[] convertTargetArea() {
		int[] result = new int[4];
		String[] splitString = targetArea.split("\\s+");
		for(int i=0;i<4;i++) {
			result[i] = Integer.parseInt(splitString[i]);
		}
		return result;
	}

	public String getSpawnAreaIntruders() {
		return spawnAreaIntruders;
	}

	public void setSpawnAreaIntruders(String spawnAreaIntruders) {
		this.spawnAreaIntruders = spawnAreaIntruders;
	}
	
	//String to int[]
	public int[] convertSpawnAreaIntruders() {
		int[] result = new int[4];
		String[] splitString = spawnAreaIntruders.split("\\s+");
		for(int i=0;i<4;i++) {
			result[i] = Integer.parseInt(splitString[i]);
		}
		return result;
	}

	public String getSpawnAreaGuards() {
		return spawnAreaGuards;
	}

	public void setSpawnAreaGuards(String spawnAreaGuards) {
		this.spawnAreaGuards = spawnAreaGuards;
	}
	
	//String to int[]
	public int[] convertSpawnAreaGuards() {
		int[] result = new int[4];
		String[] splitString = spawnAreaGuards.split("\\s+");
		for(int i=0;i<4;i++) {
			result[i] = Integer.parseInt(splitString[i]);
		}
		return result;
	}

	public String[] getWall() {
		return wall;
	}

	public void setWall(String[] wall) {
		this.wall = wall;
	}
	
	//String to int[][]
	public int[][] convertWall() {
		int[][] result = new int[wall.length][4];
		for(int k=0;k<wall.length;k++) {
			String[] splitString = wall[k].split("\\s+");
			for(int i=0;i<4;i++) {
				result[k][i] = Integer.parseInt(splitString[i]);
			}
		}
		return result;
	}

	public String[] getTeleport() {
		return teleport;
	}

	public void setTeleport(String[] teleport) {
		this.teleport = teleport;
	}
	
	//String to double[][]
	public double[][] convertTeleport(){
		double[][] result = new double[teleport.length][7];
		for(int k=0;k<teleport.length;k++) {
			String[] splitString = teleport[k].split("\\s+");
			for(int i=0;i<7;i++) {
				result[k][i] = Double.parseDouble(splitString[i]);
			}
		}
		return result;
	}

	public String[] getShaded() {
		return shaded;
	}

	public void setShaded(String[] shaded) {
		this.shaded = shaded;
	}
	
	//String to int[][]
	public int[][] convertShaded(){
		int[][] result = new int[shaded.length][4];
		for(int k=0;k<shaded.length;k++) {
			String[] splitString = shaded[k].split("\\s+");
			for(int i=0;i<4;i++) {
				result[k][i] = Integer.parseInt(splitString[i]);
			}
		}
		return result;
	}

	public String[] getTexture() {
		return texture;
	}

	public void setTexture(String[] texture) {
		this.texture = texture;
	}
	
	//String to int[][]
	public int[][] convertTexture(){
		int[][] result = new int[texture.length][6];
		for(int k=0;k<texture.length;k++) {
			String[] splitString = texture[k].split("\\s+");
			for(int i=0;i<6;i++) {
				result[k][i] = Integer.parseInt(splitString[i]);
			}
		}
		return result;
	}
}
