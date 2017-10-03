/*
================================================================================================================
Project - Mazerunner

This class generates a random maze based on the given dimension and probability. DFS, BFS, A* Manhattan distance 
and A* Euclidean distance algorithms are used to calculate the optimal path from start to goal.
searchAutomate function automates the search run to calculate the average time taken for each algorithm for 
different dimensions and probability.   
geneticMaze function generates the hard maze for each algorithm to solve based on the genetic algorithm. 
================================================================================================================
*/

package automate;

import java.util.HashMap;
import java.util.Random;

public class GeneticAlgorithm {
	
	static int dimension = 50; //Dimension of the maze
	static double prob = 0.25; //Probability with which the maze is to be generated
	
	/* 	="pathLen" to maximize length of solution,
	*	="nodes" to maximize number of nodes explored,
	*	="fringe" to maximize largest size of fringe
	*/
	static String heuristic = "pathLen"; 
	
	
	public static void main(String args[]){
		
		//searchAutomate(); //To run the search algorithms multiple times
		geneticMaze("DFS");
		geneticMaze("BFS");
		geneticMaze("Manhat");
		geneticMaze("Eucli");
	}
	
	/*
	 * 
	 * This method runs multiple times based on the numOfTimes parameter
	 * Each algorithm is run multiple times and the results are printed
	 * Output - Number of moves made and the time taken for each algorithm
	 * 
	 */
	public static void searchAutomate(){
		
		int numOfTimes = 50; /* Number of times the test to be run for each algorithm -> this will ignore the "No soln case". 
						So actual algorithm might run more times but returns "numOfTimes" number of solutions */
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
		String[] dfsFringe = new String[numOfTimes];
		String[] bfsFringe = new String[numOfTimes];
		String[] manhatFringe = new String[numOfTimes];
		String[] eucliFringe = new String[numOfTimes];
		
		
		Mazerunner maze = new Mazerunner();
		mazeGrid = new int[dimension][dimension];
		mazeGrid = maze.createMaze(dimension, prob);
		// Runs DFS Algorithm for "NoOfTimes" solutions
		for(int i=0;i<numOfTimes;) { 
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.DFSMazeSearch(mazeGrid);
			if(searchResults.containsKey("DFSMazeSearchTime") && searchResults.containsKey("DFSMazeSearchMoves") && searchResults.containsKey("DFSMazeMaxFringe")) {  // This condn will ignore the "No Solution found" case
				dfsTime[i] = searchResults.get("DFSMazeSearchTime");
				dfsMoves[i] = searchResults.get("DFSMazeSearchMoves");
				dfsFringe[i] = searchResults.get("DFSMazeMaxFringe");
				i++;
			}
		}
		// Runs BFS Algorithm for "NoOfTimes" solutions
		for(int i=0;i<numOfTimes;) { 
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.BFSMazeSearch(mazeGrid);
			if(searchResults.containsKey("BFSMazeSearchTime") && searchResults.containsKey("BFSMazeSearchMoves") && searchResults.containsKey("BFSMazeMaxFringe")) { // This condn will ignore the "No Solution found" case
				bfsTime[i] = searchResults.get("BFSMazeSearchTime");
				bfsMoves[i] = searchResults.get("BFSMazeSearchMoves");
				bfsFringe[i] = searchResults.get("BFSMazeMaxFringe");
				i++;
			}
		}
		// Runs ManhattanA* Algorithm for "NoOfTimes" solutions
		for(int i=0;i<numOfTimes;) { 
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.ManhattanAStarSearch(mazeGrid);
			if(searchResults.containsKey("ManhatMazeSearchTime") && searchResults.containsKey("ManhatMazeSearchMoves") && searchResults.containsKey("ManhatMazeMaxFringe")) { // This condn will ignore the "No Solution found" case
				manhatTime[i] = searchResults.get("ManhatMazeSearchTime");
				manhatMoves[i] = searchResults.get("ManhatMazeSearchMoves");
				manhatFringe[i] = searchResults.get("ManhatMazeMaxFringe");
				i++;
			}
		}
		// Runs EuclideanA* Algorithm for "NoOfTimes" solutions
		for(int i=0;i<numOfTimes;) { 
			mazeGrid = maze.createMaze(dimension, prob);
			searchResults = maze.EuclideanAStarSearch(mazeGrid);
			if(searchResults.containsKey("EucliMazeSearchTime") && searchResults.containsKey("EucliMazeSearchMoves") && searchResults.containsKey("EucliMazeMaxFringe")) {  // This condn will ignore the "No Solution found" case
				eucliTime[i] = searchResults.get("EucliMazeSearchTime");
				eucliMoves[i] = searchResults.get("EucliMazeSearchMoves");
				eucliFringe[i] = searchResults.get("EucliMazeMaxFringe");
				i++;
			}
		}
		// Loops the array and prints TimeTaken and Number of Moves for each Algorithm
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
		
		System.out.println("DFS MaxFringe :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(dfsFringe[i]+" , ");
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
		
		System.out.println("BFS MaxFringe :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(bfsFringe[i]+" , ");
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
		
		System.out.println("Manhat MaxFringe :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(manhatFringe[i]+" , ");
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
		System.out.println();
		
		System.out.println("Eucli MaxFringe :: ");
		for(int i=0;i<numOfTimes;i++){
			System.out.print(eucliFringe[i]+" , ");
		}
		System.out.println();
		//System.out.println(searchResults);
	}
	
	/*
	 * 
	 * This method uses Genetic Algorithm to arrive at a Hard Maze for the algorithm to solve.
	 * Weightage given to create the hard maze is: 0.25*Shortest path length + 0.25*Number of nodes explored + 0.5*Maximum Fringe size
	 * Input - searchAlg - For which algorithm the hard maze is to be generated
	 * Output - Hard Maze, Time taken to solve the maze, Number of nodes covered, Max Fringe Size, Length of the shortest path from start to goal
	 * 
	 */
	public static void geneticMaze(String searchAlg){
		long startTime = System.nanoTime();

		
		int alg = 0;
		prob = 0.30;
		if (searchAlg.equals("DFS")) alg = 1;
		else if (searchAlg.equals("BFS")) alg = 2;
		else if (searchAlg.equals("Manhat")) alg = 3;
		else if (searchAlg.equals("Eucli")) alg = 4;
		
		int[][] maze1 = new int[dimension][dimension];
		int[][] maze1Copy = new int[dimension][dimension];
		int[][] maze2 = new int[dimension][dimension];
		int[][] maze2Copy = new int[dimension][dimension];
		
		HashMap<String,String> firstSearch = new HashMap<String,String>();
		HashMap<String,String> secondSearch = new HashMap<String,String>();
		
		Mazerunner maze = new Mazerunner();
		
		int solvableMaze = 0; //tracker to re-attempt genetic algorithm if maze is unsolvable
		int totalAttempts = 0; //total number of attempts at finding a hard solution
		

		/* For the first time, the 2 mazes taken for joining will be newly created. 
					After the first run, the mutated maze will be taken as maze1 and new maze as maze2 */
		while (solvableMaze == 0 && totalAttempts<1000) {
			boolean firstLoop = true; 
			int loop = 0;
			
			while(loop <= 500) {
				if(firstLoop)
				{
					maze1 = maze.createMaze(dimension,prob);
					maze1Copy = maze.copyMaze(maze1);
					maze2 = maze.createMaze(dimension,prob);
					maze2Copy = maze.copyMaze(maze2);
					
					switch (alg) {
						case 1: 
							firstSearch = maze.DFSMazeSearch(maze1Copy);
							secondSearch = maze.DFSMazeSearch(maze2Copy);
							// Search algorithm is run for both the mazes and if "No Solution" is found for either 1 of them, than a new set of mazes are generated
							if(firstSearch.containsKey("DFSMazeSearchTime") && firstSearch.containsKey("DFSMazeSearchMoves") && firstSearch.containsKey("DFSMazeMaxFringe")
									&& secondSearch.containsKey("DFSMazeSearchTime") && secondSearch.containsKey("DFSMazeSearchMoves") && secondSearch.containsKey("DFSMazeMaxFringe")) {
								maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("DFSMazePathLen"),firstSearch.get("DFSMazeSearchMoves"),firstSearch.get("DFSMazeMaxFringe")),alg);
						
								if (!maze.isSolvable(maze1)) maze1=maze1Copy;
								
								firstLoop = false;
							}
							break;
						case 2:	
							firstSearch = maze.BFSMazeSearch(maze1Copy);
							secondSearch = maze.BFSMazeSearch(maze2Copy);
							if(firstSearch.containsKey("BFSMazeSearchTime") && firstSearch.containsKey("BFSMazeSearchMoves") 
									&& secondSearch.containsKey("BFSMazeSearchTime") && secondSearch.containsKey("BFSMazeSearchMoves")) {
								maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("BFSMazePathLen"),firstSearch.get("BFSMazeSearchMoves"),firstSearch.get("BFSMazeMaxFringe")),alg);
								if (!maze.isSolvable(maze1)) maze1=maze1Copy;
								
								firstLoop = false;
							}
							break;
						case 3:	
							firstSearch = maze.ManhattanAStarSearch(maze1Copy);
							secondSearch = maze.ManhattanAStarSearch(maze2Copy);
							if(firstSearch.containsKey("ManhatMazeSearchTime") && firstSearch.containsKey("ManhatMazeSearchMoves") 
									&& secondSearch.containsKey("ManhatMazeSearchTime") && secondSearch.containsKey("ManhatMazeSearchMoves")) {
								maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("ManhatMazePathLen"),firstSearch.get("ManhatMazeSearchMoves"),firstSearch.get("ManhatMazeMaxFringe")),alg);
								if (!maze.isSolvable(maze1)) maze1=maze1Copy;
								
								firstLoop = false;
							}
							break;
						case 4:	
							firstSearch = maze.EuclideanAStarSearch(maze1Copy);
							secondSearch = maze.EuclideanAStarSearch(maze2Copy);
							if(firstSearch.containsKey("EucliMazeSearchTime") && firstSearch.containsKey("EucliMazeSearchMoves") 
									&& secondSearch.containsKey("EucliMazeSearchTime") && secondSearch.containsKey("EucliMazeSearchMoves")) {
								maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("EucliMazePathLen"),firstSearch.get("EucliMazeSearchMoves"),firstSearch.get("EucliMazeMaxFringe")),alg);
								if (!maze.isSolvable(maze1)) maze1=maze1Copy;
								
								firstLoop = false;
							}
							break;
					}
					
				} else {
					maze2 = maze.createMaze(dimension, prob);
					maze2Copy = maze.copyMaze(maze2);
					maze1Copy = maze.copyMaze(maze1);
					/*
					 * maze1 will be the mutated maze returned from joinMazeDiagonal function
					 * buildMazeHeuristic calculates the heuristic based on the weighted probability
					 */
					switch (alg) {
						case 1:
							maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("DFSMazePathLen"),firstSearch.get("DFSMazeSearchMoves"),firstSearch.get("DFSMazeMaxFringe")),alg);
							if (!maze.isSolvable(maze1)) maze1=maze1Copy;
							break;
						case 2:
							maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("BFSMazePathLen"),firstSearch.get("BFSMazeSearchMoves"),firstSearch.get("BFSMazeMaxFringe")),alg);
							if (!maze.isSolvable(maze1)) maze1=maze1Copy;
							break;
						case 3:
							maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("ManhatMazePathLen"),firstSearch.get("ManhatMazeSearchMoves"),firstSearch.get("ManhatMazeMaxFringe")),alg);
							if (!maze.isSolvable(maze1)) maze1=maze1Copy;
							break;
						case 4:
							maze1 = joinMazeDiagonal(maze1,maze2,buildMazeHeuristic(firstSearch.get("EucliMazePathLen"),firstSearch.get("EucliMazeSearchMoves"),firstSearch.get("EucliMazeMaxFringe")),alg);
							if (!maze.isSolvable(maze1)) maze1=maze1Copy;
							break;
					}
					loop++;
				}
			}
			totalAttempts++;
			switch (alg){
				case 1:
					if (maze.DFSHardSolution[maze.dimension-1][maze.dimension-1]>0) solvableMaze = 1;
//					else {
//						System.out.println("DFS Attempt number: " + totalAttempts + ". Unsolvable maze produced. Retrying");
//					}
					break;
				case 2:
					if (maze.BFSHardSolution[maze.dimension-1][maze.dimension-1]>0) solvableMaze = 1;
//					else {
//						System.out.println("BFS Attempt number: " + totalAttempts + ". Unsolvable maze produced. Retrying");
//					}
					break;
				case 3:
					if (maze.ManhatHardSolution[maze.dimension-1][maze.dimension-1]>0) solvableMaze = 1;
//					else {
//						System.out.println("Manhattan Attempt number: " + totalAttempts + ". Unsolvable maze produced. Retrying");
//					}
					break;
				case 4:
					if (maze.EucliHardSolution[maze.dimension-1][maze.dimension-1]>0) solvableMaze = 1;
//					else {
//						System.out.println("Eucli Attempt number: " + totalAttempts + ". Unsolvable maze produced. Retrying");
//					}
					break;
			}
		}
		
		long endTime = System.nanoTime();
		//System.out.println("Attempts: " + totalAttempts);
		switch (alg) {
			case 1:
				System.out.println("****************** DFS Hard Maze Found ******************");
				maze.printMaze(maze1);
				System.out.println("****************** DFS Hard Maze Found ******************");
				System.out.println("***************** DFS Hard Maze Solution ******************");
				maze.printMaze(maze.DFSHardSolution);
				System.out.println("***************** DFS Hard Maze Solution ******************");
				System.out.println("Time taken for Genetic Algorithm ::: "+String.valueOf((0.000001)*(endTime - startTime))+"ms");
				firstSearch = maze.DFSMazeSearch(maze1);
				System.out.println("Path for harder Maze is ::: "+firstSearch.get("DFSMazeSearchSoln"));
				//System.out.println("Time taken for harder Maze is ::: "+firstSearch.get("DFSMazeSearchTime"));
				System.out.println("Number of Moves for harder Maze is ::: "+firstSearch.get("DFSMazeSearchMoves"));
				System.out.println("Maximum Fringe Size for harder Maze is ::: "+firstSearch.get("DFSMazeMaxFringe"));
				System.out.println("Shortest Path Length for harder Maze is ::: "+firstSearch.get("DFSMazePathLen"));
				break;
			case 2:
				System.out.println("****************** BFS Hard Maze Found ******************");
				maze.printMaze(maze1);
				System.out.println("****************** BFS Hard Maze Found ******************");
				System.out.println("***************** BFS Hard Maze Solution ******************");
				maze.printMaze(maze.BFSHardSolution);
				System.out.println("***************** BFS Hard Maze Solution ******************");
				System.out.println("Time taken for Genetic Algorithm ::: "+String.valueOf((0.000001)*(endTime - startTime))+"ms");
				firstSearch = maze.BFSMazeSearch(maze1);
				System.out.println("Path for harder Maze is ::: "+firstSearch.get("BFSMazeSearchSoln"));
				//System.out.println("Time taken for harder Maze is ::: "+firstSearch.get("BFSMazeSearchTime"));
				System.out.println("Number of Moves for harder Maze is ::: "+firstSearch.get("BFSMazeSearchMoves"));
				System.out.println("Maximum Fringe Size for harder Maze is ::: "+firstSearch.get("BFSMazeMaxFringe"));
				System.out.println("Shortest Path Length for harder Maze is ::: "+firstSearch.get("BFSMazePathLen"));
				break;
			case 3: 
				System.out.println("****************** Manhattan A* Hard Maze Found ******************");
				maze.printMaze(maze1);
				System.out.println("****************** Manhattan A* Hard Maze Found ******************");
				System.out.println("***************** Manhattan A* Hard Maze Solution ******************");
				maze.printMaze(maze.ManhatHardSolution);
				System.out.println("***************** Manhattan A* Hard Maze Solution ******************");
				System.out.println("Time taken for Genetic Algorithm ::: "+String.valueOf((0.000001)*(endTime - startTime))+"ms");
				firstSearch = maze.ManhattanAStarSearch(maze1);
				System.out.println("Path for harder Maze is ::: "+firstSearch.get("ManhatMazeSearchSoln"));
				//System.out.println("Time taken for harder Maze is ::: "+firstSearch.get("ManhatMazeSearchTime"));
				System.out.println("Number of Moves for harder Maze is ::: "+firstSearch.get("ManhatMazeSearchMoves"));
				System.out.println("Maximum Fringe Size for harder Maze is ::: "+firstSearch.get("ManhatMazeMaxFringe"));
				System.out.println("Shortest Path Length for harder Maze is ::: "+firstSearch.get("ManhatMazePathLen"));
				break;
			case 4:
				System.out.println("****************** Euclidean Hard Maze Found ******************");
				maze.printMaze(maze1);
				System.out.println("****************** Euclidean Hard Maze Found ******************");
				System.out.println("***************** Euclidean A* Hard Maze Solution ******************");
				maze.printMaze(maze.EucliHardSolution);
				System.out.println("***************** Euclidean A* Hard Maze Solution ******************");
				System.out.println("Time taken for Genetic Algorithm ::: "+String.valueOf((0.000001)*(endTime - startTime))+"ms");
				firstSearch = maze.EuclideanAStarSearch(maze1);
				System.out.println("Path for harder Maze is ::: "+firstSearch.get("EucliMazeSearchSoln"));
				//System.out.println("Time taken for harder Maze is ::: "+firstSearch.get("EucliMazeSearchTime"));
				System.out.println("Number of Moves for harder Maze is ::: "+firstSearch.get("EucliMazeSearchMoves"));
				System.out.println("Maximum Fringe Size for harder Maze is ::: "+firstSearch.get("EucliMazeMaxFringe"));
				System.out.println("Shortest Path Length for harder Maze is ::: "+firstSearch.get("EucliMazePathLen"));
				break;
		}
	}
	
	/*
	 * 
	 * This method joins the 2 input mazes diagonally. Top diagonal of maze1 and bottom diagonal of maze2
	 * Input Parameters - mazeOne, mazeTwo & alg
	 * Returns - MutatedMaze if its solvable and harder than maze1, else returns maze1
	 * 
	 */
	public static int[][] joinMazeDiagonal(int[][] mazeOne,int[][] mazeTwo,double heuristic, int alg){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for (int i=0; i<dimension; i++){
			for (int j=0; j<dimension; j++){
				if (i+j+2<=dimension){
					mutatedMaze[i][j] = mazeOne[i][j];
				} else {
					mutatedMaze[i][j] = mazeTwo[i][j];
				}
			}
		}

		// Randomly generates a value for row and column and flip the value for the corresponding cell in the mutated maze
		for (int count = 0; count<dimension/2; count++){
			Random rand = new Random();
			int i = rand.nextInt(dimension-1);
			int j = rand.nextInt(dimension-1);
			if (!(i==0&&j==0) && !(i==dimension-1&&j==dimension-1)){
				if(mutatedMaze[i][j] == 0)
					mutatedMaze[i][j] = -1;
				else
					mutatedMaze[i][j] = 0;
			}
			mutatedMaze[0][0] = 0;
			mutatedMaze[dimension-1][dimension-1] = 0;
		}
		
		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		
		switch(alg) {
			case 1:
				searchResult = maze.DFSMazeSearch(mutatedMazeCopy);  // Calls the search algorithm to checks if the resultant mutated maze is solvable else returns maze1
				if(!(searchResult.containsKey("DFSMazeSearchTime") && searchResult.containsKey("DFSMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("DFSMazePathLen"),searchResult.get("DFSMazeSearchMoves"),searchResult.get("DFSMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne; // If mutated maze is not solvable or not harder, then return maze1 with (0,0) as -1
				} else {
					return mutatedMaze;
				}
			case 2:
				searchResult = maze.BFSMazeSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("BFSMazeSearchTime") && searchResult.containsKey("BFSMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("BFSMazePathLen"),searchResult.get("BFSMazeSearchMoves"),searchResult.get("BFSMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
			case 3:
				searchResult = maze.ManhattanAStarSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("ManhatMazeSearchTime") && searchResult.containsKey("ManhatMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("ManhatMazePathLen"),searchResult.get("ManhatMazeSearchMoves"),searchResult.get("ManhatMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
			case 4:
				searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
		}
		
		return null;
	}
	
	/*
	 * 
	 * This method joins the 2 input mazes horizontally. Top half of maze1 and bottom half of maze2
	 * Input Parameters - mazeOne, mazeTwo & alg
	 * Returns - MutatedMaze if its solvable and harder than maze1, else returns maze1
	 * 
	 */
	public static int[][] joinMazeHorizontal(int[][] mazeOne,int[][] mazeTwo,double heuristic,int alg){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for(int i=0;i<(dimension/2);i++) { // top half of maze1
			for(int j=0; j<dimension; j++) {
				mutatedMaze[i][j] = mazeOne[i][j];
			}
		}
		for(int i=(dimension/2);i<dimension;i++) { // bottom half of maze2
			for(int j=0; j<dimension;j++) {
				mutatedMaze[i][j] = mazeTwo[i][j];
			}
		}
		
		for (int count = 0; count<dimension/2; count++){
			Random rand = new Random();
			int i = rand.nextInt(dimension-1);
			int j = rand.nextInt(dimension-1);
			if (!(i==0&&j==0) && !(i==dimension-1&&j==dimension-1)){
				if(mutatedMaze[i][j] == 0)
					mutatedMaze[i][j] = -1;
				else
					mutatedMaze[i][j] = 0;
			}
			mutatedMaze[0][0] = 0;
			mutatedMaze[dimension-1][dimension-1] = 0;
		}
		// Randomly generates a value for row and column and flip the value for the corresponding cell in the mutated maze
		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		
		switch(alg) {
			case 1:
				searchResult = maze.DFSMazeSearch(mutatedMazeCopy);  // Calls the search algorithm to checks if the resultant mutated maze is solvable else returns maze1
				if(!(searchResult.containsKey("DFSMazeSearchTime") && searchResult.containsKey("DFSMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("DFSMazePathLen"),searchResult.get("DFSMazeSearchMoves"),searchResult.get("DFSMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne; // If mutated maze is not solvable or not harder, then return maze1 with(0,0) as -1
				} else {
					return mutatedMaze;
				}
			case 2:
				searchResult = maze.BFSMazeSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("BFSMazeSearchTime") && searchResult.containsKey("BFSMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("BFSMazePathLen"),searchResult.get("BFSMazeSearchMoves"),searchResult.get("BFSMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
			case 3:
				searchResult = maze.ManhattanAStarSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("ManhatMazeSearchTime") && searchResult.containsKey("ManhatMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("ManhatMazePathLen"),searchResult.get("ManhatMazeSearchMoves"),searchResult.get("ManhatMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
			case 4:
				searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
				if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves")) 
						|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
				mazeOne[0][0] = -1;
				return mazeOne;
				} else {
					return mutatedMaze;
				}
			}	
	
		return null;
	}
	
	/*
	 * 
	 * This method joins the 2 input mazes vertically. Left half of maze1 and right half of maze2
	 * Input Parameters - mazeOne, mazeTwo & alg
	 * Returns - MutatedMaze if its solvable and harder than maze1, else returns maze1
	 * 
	 */
	public static int[][] joinMazeVertical(int[][] mazeOne,int[][] mazeTwo,double heuristic, int alg){
		int[][] mutatedMaze = new int[dimension][dimension];
		int[][] mutatedMazeCopy = new int[dimension][dimension];
		HashMap<String,String> searchResult = new HashMap<String,String>();
		
		for(int i=0;i<dimension;i++) {
			for(int j=0; j<(dimension/2); j++) { // Left half of maze1
				mutatedMaze[i][j] = mazeOne[i][j];
			}
		}
		for(int i=0;i<dimension;i++) {
			for(int j=(dimension/2); j<dimension;j++) { // Right half of maze2
				mutatedMaze[i][j] = mazeTwo[i][j];
			}
		}
		// Randomly generates a value for row and column and flip the value for the corresponding cell in the mutated maze
		for (int count = 0; count<dimension/2; count++){
			Random rand = new Random();
			int i = rand.nextInt(dimension-1);
			int j = rand.nextInt(dimension-1);
			if (!(i==0&&j==0) && !(i==dimension-1&&j==dimension-1)){
				if(mutatedMaze[i][j] == 0)
					mutatedMaze[i][j] = -1;
				else
					mutatedMaze[i][j] = 0;
			}
			mutatedMaze[0][0] = 0;
			mutatedMaze[dimension-1][dimension-1] = 0;
			
		}

		Mazerunner maze = new Mazerunner();
		mutatedMazeCopy = maze.copyMaze(mutatedMaze);
		switch(alg) {
		case 1:
			searchResult = maze.DFSMazeSearch(mutatedMazeCopy);  // Calls the search algorithm to checks if the resultant mutated maze is solvable else returns maze1
			if(!(searchResult.containsKey("DFSMazeSearchTime") && searchResult.containsKey("DFSMazeSearchMoves")) 
					|| heuristic > buildMazeHeuristic(searchResult.get("DFSMazePathLen"),searchResult.get("DFSMazeSearchMoves"),searchResult.get("DFSMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne; // If mutated maze is not solvable or not harder, then return maze1 with(0,0) as -1
			} else {
				return mutatedMaze;
			}
		case 2:
			searchResult = maze.BFSMazeSearch(mutatedMazeCopy);
			if(!(searchResult.containsKey("BFSMazeSearchTime") && searchResult.containsKey("BFSMazeSearchMoves")) 
					|| heuristic > buildMazeHeuristic(searchResult.get("BFSMazePathLen"),searchResult.get("BFSMazeSearchMoves"),searchResult.get("BFSMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
			} else {
				return mutatedMaze;
			}
		case 3:
			searchResult = maze.ManhattanAStarSearch(mutatedMazeCopy);
			if(!(searchResult.containsKey("ManhatMazeSearchTime") && searchResult.containsKey("ManhatMazeSearchMoves")) 
					|| heuristic > buildMazeHeuristic(searchResult.get("ManhatMazePathLen"),searchResult.get("ManhatMazeSearchMoves"),searchResult.get("ManhatMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
			} else {
				return mutatedMaze;
			}
		case 4:
			searchResult = maze.EuclideanAStarSearch(mutatedMazeCopy);
			if(!(searchResult.containsKey("EucliMazeSearchTime") && searchResult.containsKey("EucliMazeSearchMoves")) 
					|| heuristic > buildMazeHeuristic(searchResult.get("EucliMazePathLen"),searchResult.get("EucliMazeSearchMoves"),searchResult.get("EucliMazeMaxFringe"))) {
			mazeOne[0][0] = -1;
			return mazeOne;
			} else {
				return mutatedMaze;
			}
		}
	
		return null;
	}
	
	/*
	 * 
	 * This method returns the desired Heuristic we are examining
	 * Input Parameters - pathLen, nodes & fringe
	 * Returns - Final heuristic value selected
	 * 
	 */
	public static double buildMazeHeuristic(String pathLen, String nodes, String fringe){
		//double heuristic = (0.25*Double.parseDouble(pathLen))+(0.25*Double.parseDouble(nodes))+(0.5*Double.parseDouble(fringe));
		double heuristicDouble=0.0;
		if (heuristic.equals("pathLen"))		heuristicDouble = Double.parseDouble(pathLen);
		else if (heuristic.equals("nodes")) 	heuristicDouble = Double.parseDouble(nodes);
		else if (heuristic.equals("fringe"))	heuristicDouble = Double.parseDouble(fringe);
		return heuristicDouble;
	}
}