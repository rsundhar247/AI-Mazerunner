package automate;

import java.util.HashMap;
import java.util.Random;

public class SearchAutomation {
	
	static int dimension = 15;
	static double prob = 0.35;
	
	public static void main(String args[]){
		searchAutomate();
		geneticMaze();
	}
	
	public static void searchAutomate(){
		
		int numOfTimes = 50;
		int[][] mazeGrid;
		
		HashMap <String,String> searchResults = new HashMap<String,String>();
		String[] dfsTime = new String[numOfTimes];
		String[] bfsTime = new String[numOfTimes];
		String[] manhatTime = new String[numOfTimes];
		String[] eucliTime = new String[numOfTimes];
		String[] dfsMoves = new String[numOfTimes];
		String[] bfsMoves = new String[numOfTimes];
		String[] manhatMoves = new String[numOfTimes];
		String[] eucliMoves = new String[numOfTimes];
		
		Mazerunner maze = new Mazerunner();
		mazeGrid = new int[dimension][dimension];
		mazeGrid = maze.createMaze(dimension, prob);
		
		for(int i=0;i<numOfTimes;) {
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.DFSMazeSearch(mazeGrid);
			if(searchResults.containsKey("DFSMazeSearchTime") && searchResults.containsKey("DFSMazeSearchMoves")) {
				dfsTime[i] = searchResults.get("DFSMazeSearchTime");
				dfsMoves[i] = searchResults.get("DFSMazeSearchMoves");
				i++;
			}
		}
		
		for(int i=0;i<numOfTimes;) {
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.BFSMazeSearch(mazeGrid);
			if(searchResults.containsKey("BFSMazeSearchTime") && searchResults.containsKey("BFSMazeSearchMoves")) {
				bfsTime[i] = searchResults.get("BFSMazeSearchTime");
				bfsMoves[i] = searchResults.get("BFSMazeSearchMoves");
				i++;
			}
		}
		
		for(int i=0;i<numOfTimes;) {
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.ManhattanAStarSearch(mazeGrid);
			if(searchResults.containsKey("ManhatMazeSearchTime") && searchResults.containsKey("ManhatMazeSearchMoves")) {
				manhatTime[i] = searchResults.get("ManhatMazeSearchTime");
				manhatMoves[i] = searchResults.get("ManhatMazeSearchMoves");
				i++;
			}
		}
		
		for(int i=0;i<numOfTimes;) {
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.EuclideanAStarSearch(mazeGrid);
			if(searchResults.containsKey("EucliMazeSearchTime") && searchResults.containsKey("EucliMazeSearchMoves")) {
				eucliTime[i] = searchResults.get("EucliMazeSearchTime");
				eucliMoves[i] = searchResults.get("EucliMazeSearchMoves");
				i++;
			}
		}
		
		System.out.println("DFS Time :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(dfsTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("DFS Moves :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(dfsMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("BFS Time :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(bfsTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("BFS Moves :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(bfsMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Manhat Time :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(manhatTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Manhat Moves :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(manhatMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Eucli Times :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(eucliTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Eucli Moves :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(eucliMoves[i]+" , ");
		}
		//System.out.println(searchResults);
	}
	
	public static void geneticMaze(){
		long startTime = System.nanoTime();
		int[][] maze1 = new int[dimension][dimension];
		int[][] maze1Copy = new int[dimension][dimension];
		int[][] maze2 = new int[dimension][dimension];
		int[][] maze2Copy = new int[dimension][dimension];
		HashMap<String,String> firstSearch = new HashMap<String,String>();
		HashMap<String,String> secondSearch = new HashMap<String,String>();
		
		Mazerunner maze = new Mazerunner();
		
		int loop = 0;
		boolean firstLoop = true;
		while(loop <= 500) {
			if(firstLoop)
			{
				maze1 = maze.createMaze(dimension,prob);
				maze1Copy = maze.copyMaze(maze1);
				firstSearch = maze.EuclideanAStarSearch(maze1Copy);
				maze2 = maze.createMaze(dimension,prob);
				maze2Copy = maze.copyMaze(maze2);
				secondSearch = maze.EuclideanAStarSearch(maze2Copy);
				if(firstSearch.containsKey("EucliMazeSearchTime") && firstSearch.containsKey("EucliMazeSearchMoves") 
								&& secondSearch.containsKey("EucliMazeSearchTime") && secondSearch.containsKey("EucliMazeSearchMoves")) {
					maze1 = joinMazeDiagonal(maze1,maze2,Double.parseDouble(firstSearch.get("EucliMazeSearchTime")));
					
					if(maze1[0][0] != -1)
						firstLoop = false;
				}
			} else {
				maze2 = maze.createMaze(dimension, prob);
				maze2Copy = maze.copyMaze(maze2);
				maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("EucliMazePathLen"),firstSearch.get("EucliMazeSearchMoves"),firstSearch.get("EucliMazeMaxFringe")));
				if(maze1[0][0] == -1) {
					maze1[0][0] = 0;
					maze1 = joinMazeHorizontal(maze1,maze2,buildMazeHeuristic(firstSearch.get("EucliMazePathLen"),firstSearch.get("EucliMazeSearchMoves"),firstSearch.get("EucliMazeMaxFringe")));
					if(maze1[0][0] == -1) {
						maze1[0][0] = 0;
						maze1 = joinMazeVertical(maze1,maze2,buildMazeHeuristic(firstSearch.get("EucliMazePathLen"),firstSearch.get("EucliMazeSearchMoves"),firstSearch.get("EucliMazeMaxFringe")));
						
						if(maze1[0][0] == -1){
							maze1[0][0] = 0;
							loop++;
						}
					}
				}
				maze1[0][0] = 0;
			}
		}
		long endTime = System.nanoTime();
		System.out.println("****************** Hard Maze Found ******************");
		maze.printMaze(maze1);
		System.out.println("****************** Hard Maze Found ******************");
		System.out.println("Time taken for Genetic Algorithm ::: "+String.valueOf((0.000001)*(endTime - startTime))+"ms");
		firstSearch = maze.EuclideanAStarSearch(maze1);
		System.out.println("Path for harder Maze is ::: "+firstSearch.get("EucliMazeSearchSoln"));
		System.out.println("Time taken for harder Maze is ::: "+firstSearch.get("EucliMazeSearchTime"));
		System.out.println("Number of Moves for harder Maze is ::: "+firstSearch.get("EucliMazeSearchMoves"));
		System.out.println("Maximum Fringe Size for harder Maze is ::: "+firstSearch.get("EucliMazeMaxFringe"));
		System.out.println("Shortest Path Length for harder Maze is ::: "+firstSearch.get("EucliMazePathLen"));
	}
	
	public static int[][] joinMazeDiagonal(int[][] mazeOne,int[][] mazeTwo,double heuristic){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for(int i=0;i<dimension;i++) {
			for(int j=i; j<dimension; j++) {
				mutatedMaze[i][j] = mazeOne[i][j];
			}
		}
		for(int i=0;i<dimension;i++) {
			for(int j=0; j<i;j++) {
				mutatedMaze[i][j] = mazeTwo[i][j];
			}
		}
		
		Random rand = new Random();
		int i = rand.nextInt(dimension-1);
		int j = rand.nextInt(dimension-1);
		if(mutatedMaze[i][j] == 0)
			mutatedMaze[i][j] = -1;
		else
			mutatedMaze[i][j] = 0;
		

		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
		
		if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves")) 
					|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
		} else {
			return mutatedMaze;
			
		}
	}
	
	public static int[][] joinMazeHorizontal(int[][] mazeOne,int[][] mazeTwo,double heuristic){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for(int i=0;i<3;i++) {
			for(int j=0; j<dimension; j++) {
				mutatedMaze[i][j] = mazeOne[i][j];
			}
		}
		for(int i=3;i<dimension;i++) {
			for(int j=0; j<dimension;j++) {
				mutatedMaze[i][j] = mazeTwo[i][j];
			}
		}
		
		Random rand = new Random();
		int i = rand.nextInt(dimension-1);
		int j = rand.nextInt(dimension-1);
		if(mutatedMaze[i][j] == 0)
			mutatedMaze[i][j] = -1;
		else
			mutatedMaze[i][j] = 0;

		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
		
		if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves"))
				|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
		} else {
			return mutatedMaze;
			
		}
	}
	
	public static int[][] joinMazeVertical(int[][] mazeOne,int[][] mazeTwo,double heuristic){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for(int i=0;i<dimension;i++) {
			for(int j=0; j<3; j++) {
				mutatedMaze[i][j] = mazeOne[i][j];
			}
		}
		for(int i=0;i<dimension;i++) {
			for(int j=3; j<dimension;j++) {
				mutatedMaze[i][j] = mazeTwo[i][j];
			}
		}
		
		Random rand = new Random();
		int i = rand.nextInt(dimension-1);
		int j = rand.nextInt(dimension-1);
		if(mutatedMaze[i][j] == 0)
			mutatedMaze[i][j] = -1;
		else
			mutatedMaze[i][j] = 0;

		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
		
		if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves"))
						|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
		} else {
			return mutatedMaze;
			
		}
	}
	
	public static double buildMazeHeuristic(String pathLen, String nodes, String fringe){
		double heuristic = (0.25*Double.parseDouble(pathLen))+(0.25*Double.parseDouble(nodes))+(0.25*Double.parseDouble(fringe));
		return heuristic;
	}
}