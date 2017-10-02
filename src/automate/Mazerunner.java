/*
================================================================================================================
Project - Mazerunner

This class generates a random maze based on the given dimension and probability. DFS, BFS, A* Manhattan distance 
and A* Euclidean distance algorithms are used to calculate the optimal path from start to goal.
createMaze function creates maze based on the dimension and probability
printMaze function prints the input maze
DFSMazeSearch, BFSMazeSearch, ManhatMazeSearch, EucliMazeSearch function does DFS, BFS, A* Manhattan 
and A* Euclidean searches respectively
getSoln function backtracks from goal node, the shortest path from start to goal 
This function is a copy of the Mazerunner file in src/app, but each method now returns a HashMap<String, String>
containing the details of its respective search algorithm solution on the map. 
================================================================================================================
*/

package automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Mazerunner{
	static int pathLen = 0;
	static int dimension;
	static int[][] DFSHardSolution;
	static int[][] BFSHardSolution;
	static int[][] ManhatHardSolution;
	static int[][] EucliHardSolution;
	
	/*
	 * 
	 * This method creates a new maze based on the input dimension and the probability
	 * Input parameters - dim, p
	 * Return - maze
	 * 
	 */
	public int[][] createMaze(int dim, double p){
		dimension = dim;
		int[][] maze = new int[dim][dim];
		for (int i=0; i<dim; i++){
			for (int j=0; j<dim; j++){
				if ((i==0 && j==0) || (i==dim-1 && j==dim-1)){
					maze[i][j] = 0;
					continue;
				}
				double z = Math.random();
				int occupied;
				if (z<=p){
					occupied = -1;
				} else {
					occupied = 0;
				}
				
				maze[i][j] = occupied;
			}
		}

		return maze;
	}
	
	/*
	 * 
	 * This method prints the maze passed as input parameter
	 * Input - maze to be printed
	 * 
	 */
	public static void printMaze(int[][] maze){
		if (maze == null){
			return;
		}
		for (int i = 0; i< maze.length; i++){
			for (int j = 0; j < maze[0].length; j++){
				if (maze[i][j]>=0 && maze[i][j]<10)	System.out.print(" "); //space to keep appearance clean
				System.out.print(maze[i][j]);
				if (j == maze[0].length - 1) {
					if (maze[i][j]>99){
						System.out.println();
					} else {
						System.out.println(" ");
					}
				} else {
					if (maze[i][j]<100)	System.out.print(" ");
				}
			}
		}
		System.out.println();
	}
	
	/*
	 * 
	 * This method makes a copy of the input maze
	 * Input - Maze to be copied
	 * Return - Maze Copy
	 * 
	 */
	public int[][] copyMaze(int[][] maze){
		int[][] newMaze = new int[maze.length][maze.length];
		for (int i=0; i<maze.length; i++){
			for (int j=0; j<maze[0].length; j++){
				newMaze[i][j] = maze[i][j];
			}
		}
		return newMaze;
	}
	
	/*
	 * 
	 * This method runs DFS Search on the maze passed as input parameter and returns a HashMap with time taken, nodes covered, Max Fringe size
	 * Input - Maze
	 * Output - HashMap with time taken, nodes covered, Max Fringe size
	 * 
	 */
	public HashMap<String,String> DFSMazeSearch(int[][] DFSMaze){
		long startTime = System.nanoTime();
		HashMap<String,String> dfsMap = new HashMap<String, String>();
		int dim = DFSMaze.length;
		int	maxMoves = dim*dim;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		Stack<int[]> stack = new Stack<int[]>();
		
		int i=0, j=0, currentMove=1, maxStackSize=0;
		visited[0][0] = true;
		DFSMaze[0][0] = 0;
		
		if (DFSMaze[i+1][j] == 0){
			int[] temp = new int[3];
			temp[0] = i+1;
			temp[1] = j;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			visited[i+1][j] = true;
			parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j)); 
			/* All the nodes added to the stack for exploration 
			are added to this map, so that the shortest path can be backtracked from the goal node */
		}
		if (DFSMaze[i][j+1] == 0){
			int[] temp = new int[3];
			temp[0] = i;
			temp[1] = j+1;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			visited[i][j+1] = true;
			parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !stack.isEmpty()) { // Loop till the goal node is reached or until no more nodes are available to explore
			int x, y;
			int[] current;
			
			current = stack.pop();
			x = current[0];
			y = current[1];

			
			DFSMaze[x][y] = current[2];
			
			i=x;
			j=y;
			currentMove++;
			
			if ((j-1) >= 0 && DFSMaze[i][j-1]==0 && visited[i][j-1]==false){
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j-1;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				visited[i][j-1] = true;
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && DFSMaze[i-1][j]==0 && visited[i-1][j]==false){
				int[] temp = new int[3];
				temp[0] = i-1;
				temp[1] = j;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				visited[i-1][j] = true;
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && DFSMaze[i+1][j]==0 && visited[i+1][j]==false){
				int[] temp = new int[3];
				temp[0] = i+1;
				temp[1] = j;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				visited[i+1][j] = true;
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && DFSMaze[i][j+1]==0 && visited[i][j+1]==false){
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j+1;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				visited[i][j+1] = true;
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
			if(maxStackSize < stack.size())
				maxStackSize = stack.size();
		};
		
		if(!(i==dim-1 && j==dim-1)){
			//System.out.println("No solution found.");
		} else{
			String solution = getSoln(parentMap,dim);
			//System.out.println("Solution Found :: "+solution);
			dfsMap.put("DFSMazeSearchSoln",solution); // Nodes of the shortest path from Start to Goal
			dfsMap.put("DFSMazePathLen",String.valueOf(pathLen)); // Shortest path length
		}
		//printMaze(DFSMaze);
		//System.out.println("Number of moves searched: " + currentMove);
		
		dfsMap.put("DFSMazeMaxFringe",String.valueOf(maxStackSize)); // Maximum Fringe size at any stage of the search
		long endTime = System.nanoTime();
		if(i==dim-1 && j==dim-1){
			dfsMap.put("DFSMazeSearchMoves", String.valueOf(currentMove)); // Total number of nodes explored
			dfsMap.put("DFSMazeSearchTime", String.valueOf((0.000001)*(endTime - startTime))); // Time taken for the search algorithm to run
		}
		DFSHardSolution = DFSMaze;
		return dfsMap;
	}

	/*
	 * 
	 * This method runs BFS Search on the maze passed as input parameter and returns a HashMap with time taken, nodes covered, Max Fringe size
	 * Input - Maze
	 * Output - HashMap with time taken, nodes covered, Max Fringe size
	 * 
	 */
	public HashMap<String,String> BFSMazeSearch(int[][] BFSMaze){
		long startTime = System.nanoTime();
		HashMap<String,String> bfsMap = new HashMap<String, String>();
		int dim = BFSMaze.length;
		int	maxMoves = dim*dim;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		Queue<int[]> queue = new LinkedList<int[]>();
		
		int i=0, j=0, currentMove=1, maxQueueSize=0;
		visited[0][0] = true;
		BFSMaze[0][0] = 0;
		
		if (BFSMaze[i+1][j] == 0){
			int[] temp = new int[3];
			temp[0] = i+1;
			temp[1] = j;
			temp[2] = 1; //path length to this move, first move so =1
			queue.add(temp);
			parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
		}
		if (BFSMaze[i][j+1] == 0){
			int[] temp = new int[3];
			temp[0] = i;
			temp[1] = j+1;
			temp[2] = 1; //path length to this move, first move so =1
			queue.add(temp);
			parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !queue.isEmpty()) {
			int x, y;
			int[] current;
			do {current = queue.remove();
			x = current[0];
			y = current[1];
			} while (visited[x][y]==true && !queue.isEmpty());
			
			if (queue.isEmpty() && (current[0]==i && current[1]==j)){
				break;
			}
			
			BFSMaze[x][y] = current[2];
			visited[x][y] = true;
			
			i=x;
			j=y;
			currentMove++;
			
			if ((j-1) >= 0 && BFSMaze[i][j-1]==0 && visited[i][j-1]==false){
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j-1;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && BFSMaze[i-1][j]==0 && visited[i-1][j]==false){
				int[] temp = new int[3];
				temp[0] = i-1;
				temp[1] = j;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && BFSMaze[i+1][j]==0 && visited[i+1][j]==false){
				int[] temp = new int[3];
				temp[0] = i+1;
				temp[1] = j;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && BFSMaze[i][j+1]==0 && visited[i][j+1]==false){
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j+1;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}

			if(maxQueueSize < queue.size())
				maxQueueSize = queue.size();
		};
		
		if(!(i==dim-1 && j==dim-1)){
			//System.out.println("No solution found.");
		} else{
			String solution = getSoln(parentMap,dim);
			//System.out.println("Solution Found :: "+solution);
			bfsMap.put("BFSMazeSearchSoln",solution);
			bfsMap.put("BFSMazePathLen",String.valueOf(pathLen));
		}
		//printMaze(BFSMaze);
		//System.out.println("Number of moves searched: " + currentMove);
		
		bfsMap.put("BFSMazeMaxFringe",String.valueOf(maxQueueSize));
		long endTime = System.nanoTime();
		if(i==dim-1 && j==dim-1){
			bfsMap.put("BFSMazeSearchMoves", String.valueOf(currentMove));
			bfsMap.put("BFSMazeSearchTime", String.valueOf((0.000001)*(endTime - startTime)));
		}
		BFSHardSolution = BFSMaze;
		return bfsMap;
	}
	
	/*
	 * This method runs A* manhattan Distance Search on the maze passed as input parameter 
	 *  and returns a HashMap with time taken, nodes covered, Max Fringe size
	 * Input - Maze
	 * Output - HashMap with time taken, nodes covered, Max Fringe size
	 * 
	 */
	public HashMap<String,String> ManhattanAStarSearch(int[][] manhattanMaze){
		long startTime = System.nanoTime();
		HashMap<String,String> manhatMap = new HashMap<String, String>();
		int dim = manhattanMaze.length;
		int	maxMoves = dim*dim;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim]; // Maintains a matrix that are visited
		PriorityQueue<GridDetails> queue = new PriorityQueue<GridDetails>(); // Priority Queue used to sort based on the heuristic value
		
		int i=0, j=0, currentMove=1, maxQueueSize=0;
		visited[0][0] = true;
		manhattanMaze[0][0] = 0;
		
		if (manhattanMaze[i+1][j] == 0){
			queue.add(new GridDetails(i+1,j,(dim-1-(i+1)) + (dim-1-j), 1));
			parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
		}
		if (manhattanMaze[i][j+1] == 0){
			queue.add(new GridDetails(i,j+1,(dim-1-i) + (dim-1-(j+1)), 1));
			parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !queue.isEmpty()) {
			int x, y;
			GridDetails current;
			do {current = queue.remove();
			x = current.getRow();
			y = current.getCol();
			} while (visited[x][y]==true && !queue.isEmpty());
			if (queue.isEmpty() && (x==i && y==j)){
				break;
			}
			manhattanMaze[x][y] = (int) (current.getOriginDist());
			visited[x][y] = true;
			
			i=x;
			j=y;
			currentMove++;
			//Explores the nearby cells and add them to the queue
			if ((j-1) >= 0 && manhattanMaze[i][j-1]==0 && visited[i][j-1]==false){
				queue.add(new GridDetails(i,(j-1),((dim-1)-i)+((dim-1)-(j-1)),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && manhattanMaze[i-1][j]==0 && visited[i-1][j]==false){
				queue.add(new GridDetails((i-1),j,((dim-1)-(i-1))+((dim-1)-j),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && manhattanMaze[i+1][j]==0 && visited[i+1][j]==false){
				queue.add(new GridDetails((i+1),j,((dim-1)-(i+1))+((dim-1)-j),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && manhattanMaze[i][j+1]==0 && visited[i][j+1]==false){
				queue.add(new GridDetails(i,(j+1),((dim-1)-i)+((dim-1)-(j+1)),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}

			if(maxQueueSize < queue.size())
				maxQueueSize = queue.size();
		};
		
		if(!(i==dim-1 && j==dim-1)){
			//System.out.println("No solution found.");
		} else{
			String solution = getSoln(parentMap,dim);
			//System.out.println("Solution Found :: "+solution);
			manhatMap.put("ManhatMazeSearchSoln",solution);
			manhatMap.put("ManhatMazePathLen",String.valueOf(pathLen));
		}
		//printMaze(manhattanMaze);
		//System.out.println("Number of moves searched: " + currentMove);	
		manhatMap.put("ManhatMazeMaxFringe",String.valueOf(maxQueueSize));
		long endTime = System.nanoTime();
		
		if(i==dim-1 && j==dim-1){
			manhatMap.put("ManhatMazeSearchMoves", String.valueOf(currentMove));
			manhatMap.put("ManhatMazeSearchTime", String.valueOf((0.000001)*(endTime - startTime)));
		}
		ManhatHardSolution = manhattanMaze;
		return manhatMap;
	}
	
	/*
	 * This method runs A* euclidean Distance Search on the maze passed as input parameter 
	 *  and returns a HashMap with time taken, nodes covered, Max Fringe size
	 * Input - Maze
	 * Output - HashMap with time taken, nodes covered, Max Fringe size
	 */
	public HashMap<String,String> EuclideanAStarSearch(int[][] euclideanMaze){
		long startTime = System.nanoTime();
		int dim = euclideanMaze.length;
		int	maxMoves = dim*dim;
		HashMap<String,String> eucliMap = new HashMap<String, String>();
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		PriorityQueue<GridDetails> pQueue = new PriorityQueue<GridDetails>();
		
		int i=0, j=0, currentMove=1, maxQueueSize=0;
		visited[0][0] = true;
		euclideanMaze[0][0] = 0;
		
		if (euclideanMaze[i+1][j] == 0){
			pQueue.add(new GridDetails(i+1,j,eucliDist(i+1,j,dim), 1));
			parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
		}
		if (euclideanMaze[i][j+1] == 0){
			pQueue.add(new GridDetails(i,j+1,eucliDist(i,j+1,dim), 1));
			parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !pQueue.isEmpty()) {
			int x, y;
			GridDetails current;
			do {current = pQueue.remove();
			x = current.getRow();
			y = current.getCol();
			} while (visited[x][y]==true && !pQueue.isEmpty());
			if (pQueue.isEmpty() && (x==i && y==j)){
				break;
			}
			euclideanMaze[x][y] = (int) (current.getOriginDist());
			//euclideanMaze[x][y] = currentMove;
			visited[x][y] = true;
			
			i=x;
			j=y;
			currentMove++;
			//Explores the nearby cells and add them to the queue
			if ((j-1) >= 0 && euclideanMaze[i][j-1]==0 && visited[i][j-1]==false){
				pQueue.add(new GridDetails(i,(j-1),eucliDist(i,j-1,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && euclideanMaze[i-1][j]==0 && visited[i-1][j]==false){
				pQueue.add(new GridDetails((i-1),j,eucliDist(i-1,j,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && euclideanMaze[i+1][j]==0 && visited[i+1][j]==false){
				pQueue.add(new GridDetails((i+1),j,eucliDist(i+1,j,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && euclideanMaze[i][j+1]==0 && visited[i][j+1]==false){
				pQueue.add(new GridDetails(i,(j+1),eucliDist(i,j+1,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
			if(maxQueueSize < pQueue.size())
				maxQueueSize = pQueue.size();
			
		};
		
		if(!(i==dim-1 && j==dim-1)){
			//System.out.println("No solution found.");
		} else{
			String solution = getSoln(parentMap,dim);
			//System.out.println("Solution Found :: "+solution);
			eucliMap.put("EucliMazeSearchSoln",solution);
			eucliMap.put("EucliMazePathLen",String.valueOf(pathLen));
		}
		//printMaze(euclideanMaze);
		//System.out.println("Number of moves searched: " + currentMove);
		eucliMap.put("EucliMazeMaxFringe",String.valueOf(maxQueueSize));
		long endTime = System.nanoTime();
		if(i==dim-1 && j==dim-1){
			eucliMap.put("EucliMazeSearchMoves", String.valueOf(currentMove));
			eucliMap.put("EucliMazeSearchTime", String.valueOf((0.000001)*(endTime - startTime)));
		}
		EucliHardSolution = euclideanMaze;
		return eucliMap;
	}
	
	/*
	 * 
	 * This method backtracks the shortest path from goal to start
	 * Input - parentMap - map with node-parentNode data for all the nodes explored
	 * Return - Shortest path from Start to Goal
	 * 
	 */
	public String getSoln(HashMap<String,String> parentMap, int dim){
		ArrayList<String> pathArr = new ArrayList<String>();
		String key=String.valueOf(dim-1)+"-"+String.valueOf(dim-1);
		while(!parentMap.isEmpty()){
			if(parentMap.containsKey(key)){
				String tempKey=parentMap.get(key);
				pathArr.add(tempKey);
				parentMap.remove(key);
				key=tempKey;
			} else{
				parentMap.clear();
			}
		}
		pathLen = pathArr.size();
		StringBuffer str = new StringBuffer();
		for(int a=pathArr.size()-1;a>=0;a--){
			str.append(pathArr.get(a));
			str.append(" , ");
		}
		str.append(String.valueOf(dim-1));
		str.append("-");
		str.append(String.valueOf(dim-1));
		
		return str.toString();
		
	}
	
	/*
	 * Returns true if a maze is solvable using DFS
	 */
	public boolean isSolvable(int[][] maze){
		int[][] SolutionMaze = copyMaze(maze);	//copy original maze
		int dim = maze.length;
		int maxMoves = dim*dim;
		
		boolean[][] visited = new boolean[dim][dim];	//stores whether a cell has been added to the fringe already
		Stack<int[]> stack = new Stack<int[]>();
		
		int i=0, j=0, currentMove=1;
		visited[0][0] = true;
		
		if (SolutionMaze[i+1][j] == 0){	//if [1][0] is not blocked
			int[] temp = new int[3];
			temp[0] = i+1;
			temp[1] = j;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			visited[i+1][j] = true;
		}
		if (SolutionMaze[i][j+1] == 0){	//if [0][1] is not blocked
			int[] temp = new int[3];
			temp[0] = i;
			temp[1] = j+1;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			visited[i][j+1] = true;
		}
		//search while within the boundaries of the maze, less than the maximum number of searches, and there are cells in the stack
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !stack.isEmpty()) { 
			int x, y;
			int[] current;

			current = stack.pop();	//get cell off the stack
			
			x = current[0];
			y = current[1];	
			SolutionMaze[x][y] = current[2];
			
			i=x;
			j=y;
			currentMove++;
			
			if ((j-1) >= 0 && SolutionMaze[i][j-1]==0 && visited[i][j-1]==false){ 	//add [i][j-1] to stack if not blocked and not already added
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j-1;
				temp[2] = SolutionMaze[i][j] + 1;
				stack.add(temp);
				visited[i][j-1] = true;
			}
			if ((i-1) >= 0 && SolutionMaze[i-1][j]==0 && visited[i-1][j]==false){ 	//add [i-1][j] to stack if not blocked and not already added
				int[] temp = new int[3];
				temp[0] = i-1;
				temp[1] = j;
				temp[2] = SolutionMaze[i][j] + 1;
				stack.add(temp);
				visited[i-1][j] = true;
			}
			if ((i+1) < dim && SolutionMaze[i+1][j]==0 && visited[i+1][j]==false){	//add [i+1][j] to stack if not blocked and not already added
				int[] temp = new int[3];
				temp[0] = i+1;
				temp[1] = j;
				temp[2] = SolutionMaze[i][j] + 1;
				stack.add(temp);
				visited[i+1][j] = true;
			}
			if ((j+1) < dim && SolutionMaze[i][j+1]==0 && visited[i][j+1]==false){	//add [i][j+1] to stack if not blocked and not already added
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j+1;
				temp[2] = SolutionMaze[i][j] + 1;
				stack.add(temp);
				visited[i][j+1] = true;
			}
			
		}
		
		if (SolutionMaze[dim-1][dim-1]>0) return true;
		else return false;
	
	}
	
	/*
	 * This method calculates the heuristic for the euclidean distance
	 * Input - row, col, dim
	 * Return - Euclidean distance heuristic value
	 */
	public double eucliDist(int row, int col, int dim){
		return (Math.sqrt(Math.pow((dim-row-1), 2)+Math.pow((dim-col-1), 2)));
	}
	
}