package app;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import app.GridDetails;
import java.util.Stack;
import java.util.Queue;



public class Mazerunner{
	
	static int[][] maze;
	static int maxMoves = 0; //upperbound, should not execute more than this number of moves, otherwise stuck in infinite loop
	
	public static void main(String args[]){
		int dimension = 15;
		double p = 0.3;
		
		createMaze(dimension, p);
		System.out.println("Original Maze: ");
		printMaze(maze);
		
		System.out.println();
		System.out.println();
		
		System.out.println("################################## DFS Search ##################################");
		long startTime = System.nanoTime();
		DFSMazeSearch();
		long endTime = System.nanoTime();
		System.out.println("Manhattan A* Search Took " + (0.000001)*(endTime - startTime) + " ms"); 
		System.out.println("################################## DFS Search ##################################");
		
		System.out.println();
		System.out.println();
		
		System.out.println("################################## BFS Search ##################################");
		startTime = System.nanoTime();
		BFSMazeSearch();
		endTime = System.nanoTime();
		System.out.println("Manhattan A* Search Took " + (0.000001)*(endTime - startTime) + " ms"); 
		System.out.println("################################## BFS Search ##################################");
		
		System.out.println();
		System.out.println();
		
		System.out.println("################################## Manhattan A* Search ##################################");
		startTime = System.nanoTime();
		ManhattanAStarSearch();
		endTime = System.nanoTime();
		System.out.println("Manhattan A* Search Took " + (0.000001)*(endTime - startTime) + " ms"); 
		System.out.println("################################## Manhattan A* Search ##################################");
		
		System.out.println();
		System.out.println();
		
		System.out.println("################################## Euclidean A* Search ##################################");
		startTime = System.nanoTime();
		EuclideanAStarSearch();
		endTime = System.nanoTime();
		System.out.println("Euclidean A* Search Took " + (0.000001)*(endTime - startTime) + " ms"); 
		System.out.println("################################## Euclidean A* Search ##################################");
		
	}
	public static void createMaze(int dim, double p){
		maxMoves = dim*dim;
		maze = new int[dim][dim];
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
	}
	
	public static void printMaze(int[][] maze){
		if (maze == null){
			return;
		}
		for (int i = 0; i< maze.length; i++){
			for (int j = 0; j < maze[0].length; j++){
				if (maze[i][j]>=0)	System.out.print(" "); //space to keep appearance clean
				if (j == maze[0].length - 1) {
					if(maze[i][j]>=0 && maze[i][j]<10){
						System.out.println(maze[i][j] + " ");
					} else {
						System.out.println(maze[i][j]);						
					}
				} else {
					if(maze[i][j]<10){
						System.out.print(maze[i][j] + " ");
					} else {
						System.out.print(maze[i][j]);						
					}
				}
			}
		}
		System.out.println();
	}
	
	public static int[][] copyMaze(int[][] maze){
		int[][] newMaze = new int[maze.length][maze.length];
		for (int i=0; i<maze.length; i++){
			for (int j=0; j<maze[0].length; j++){
				newMaze[i][j] = maze[i][j];
			}
		}
		return newMaze;
	}
	
	public static void DFSMazeSearch(){
		int[][] DFSMaze = copyMaze(maze);
		int dim = maze.length;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		Stack<int[]> stack = new Stack<int[]>();
		
		int i=0, j=0, currentMove=1;
		visited[0][0] = true;
		DFSMaze[0][0] = 0;
		
		if (DFSMaze[i+1][j] == 0){
			int[] temp = new int[3];
			temp[0] = i+1;
			temp[1] = j;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
		}
		if (DFSMaze[i][j+1] == 0){
			int[] temp = new int[3];
			temp[0] = i;
			temp[1] = j+1;
			temp[2] = 1; //path length to this move, first move so =1
			stack.push(temp);
			parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !stack.isEmpty()) {
			int x, y, length;
			int[] current;
			do {current = stack.pop();
			x = current[0];
			y = current[1];
			length = current[2];
			} while (visited[x][y]==true && !stack.isEmpty());
			
			if (stack.isEmpty() && (current[0]==i && current[1]==j)){
				break;
			}
			
			DFSMaze[x][y] = current[2];
			visited[x][y] = true;
			
			i=x;
			j=y;
			currentMove++;
			
			if ((j-1) >= 0 && DFSMaze[i][j-1]==0 && visited[i][j-1]==false){
				//System.out.println(i + ", " + (j-1));
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j-1;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && DFSMaze[i-1][j]==0 && visited[i-1][j]==false){
				//System.out.println((i-1) + ", " + (j));
				int[] temp = new int[3];
				temp[0] = i-1;
				temp[1] = j;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && DFSMaze[i+1][j]==0 && visited[i+1][j]==false){
				//System.out.println((i+1) + ", " + j);
				int[] temp = new int[3];
				temp[0] = i+1;
				temp[1] = j;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && DFSMaze[i][j+1]==0 && visited[i][j+1]==false){
				//System.out.println(i + ", " + (j+1));
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j+1;
				temp[2] = DFSMaze[i][j] + 1;
				stack.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
		};
		
		if(!(i==dim-1 && j==dim-1)){
			System.out.println("No solution found.");
		} else{
			printSoln(parentMap,dim);
		}
		printMaze(DFSMaze);
		System.out.println("Number of moves searched: " + currentMove);
		
	}

	public static void BFSMazeSearch(){
		int[][] BFSMaze = copyMaze(maze);
		int dim = maze.length;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		Queue<int[]> queue = new LinkedList<int[]>();
		
		int i=0, j=0, currentMove=1;
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
			int x, y, length;
			int[] current;
			do {current = queue.remove();
			x = current[0];
			y = current[1];
			length = current[2];
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
				//System.out.println(i + ", " + (j-1));
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j-1;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && BFSMaze[i-1][j]==0 && visited[i-1][j]==false){
				//System.out.println((i-1) + ", " + (j));
				int[] temp = new int[3];
				temp[0] = i-1;
				temp[1] = j;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && BFSMaze[i+1][j]==0 && visited[i+1][j]==false){
				//System.out.println((i+1) + ", " + j);
				int[] temp = new int[3];
				temp[0] = i+1;
				temp[1] = j;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && BFSMaze[i][j+1]==0 && visited[i][j+1]==false){
				//System.out.println(i + ", " + (j+1));
				int[] temp = new int[3];
				temp[0] = i;
				temp[1] = j+1;
				temp[2] = BFSMaze[i][j] + 1;
				queue.add(temp);
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
		};
		
		if(!(i==dim-1 && j==dim-1)){
			System.out.println("No solution found.");
		} else{
			printSoln(parentMap,dim);
		}
		printMaze(BFSMaze);
		System.out.println("Number of moves searched: " + currentMove);
		
	}
	
	public static void ManhattanAStarSearch(){
		int[][] manhattanMaze = copyMaze(maze);
		int dim = maze.length;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		PriorityQueue<GridDetails> queue = new PriorityQueue<GridDetails>();
		
		int i=0, j=0, currentMove=1;
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
			
			if ((j-1) >= 0 && manhattanMaze[i][j-1]==0 && visited[i][j-1]==false){
				//System.out.println(i + ", " + (j-1));
				queue.add(new GridDetails(i,(j-1),((dim-1)-i)+((dim-1)-(j-1)),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && manhattanMaze[i-1][j]==0 && visited[i-1][j]==false){
				//System.out.println((i-1) + ", " + (j));
				queue.add(new GridDetails((i-1),j,((dim-1)-(i-1))+((dim-1)-j),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && manhattanMaze[i+1][j]==0 && visited[i+1][j]==false){
				//System.out.println((i+1) + ", " + j);
				queue.add(new GridDetails((i+1),j,((dim-1)-(i+1))+((dim-1)-j),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && manhattanMaze[i][j+1]==0 && visited[i][j+1]==false){
				//System.out.println(i + ", " + (j+1));
				queue.add(new GridDetails(i,(j+1),((dim-1)-i)+((dim-1)-(j+1)),manhattanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
		};
		
		if(!(i==dim-1 && j==dim-1)){
			System.out.println("No solution found.");
		} else{
			printSoln(parentMap,dim);
		}
		printMaze(manhattanMaze);
		System.out.println("Number of moves searched: " + currentMove);
	}
	
	public static void EuclideanAStarSearch(){
		int[][] euclideanMaze = copyMaze(maze);
		int dim = maze.length;
		HashMap<String,String> parentMap = new HashMap<String, String>();
		boolean[][] visited = new boolean[dim][dim];
		PriorityQueue<GridDetails> pQueue = new PriorityQueue<GridDetails>();
		
		int i=0, j=0, currentMove=1;
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
			
			if ((j-1) >= 0 && euclideanMaze[i][j-1]==0 && visited[i][j-1]==false){
				//System.out.println(i + ", " + (j-1));
				pQueue.add(new GridDetails(i,(j-1),eucliDist(i,j-1,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j-1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i-1) >= 0 && euclideanMaze[i-1][j]==0 && visited[i-1][j]==false){
				//System.out.println((i-1) + ", " + (j));
				pQueue.add(new GridDetails((i-1),j,eucliDist(i-1,j,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i-1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((i+1) < dim && euclideanMaze[i+1][j]==0 && visited[i+1][j]==false){
				//System.out.println((i+1) + ", " + j);
				pQueue.add(new GridDetails((i+1),j,eucliDist(i+1,j,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i+1)+"-"+String.valueOf(j), String.valueOf(i)+"-"+String.valueOf(j));
			}
			if ((j+1) < dim && euclideanMaze[i][j+1]==0 && visited[i][j+1]==false){
				//System.out.println(i + ", " + (j+1));
				pQueue.add(new GridDetails(i,(j+1),eucliDist(i,j+1,dim),euclideanMaze[i][j] + 1));
				parentMap.put(String.valueOf(i)+"-"+String.valueOf(j+1), String.valueOf(i)+"-"+String.valueOf(j));
			}
			
		};
		
		//System.out.println(i + ", " + j);
		//System.out.println("" + pQueue.isEmpty());
		if(!(i==dim-1 && j==dim-1)){
			System.out.println("No solution found.");
		} else{
			printSoln(parentMap,dim);
		}
		printMaze(euclideanMaze);
		System.out.println("Number of moves searched: " + currentMove);
		
	}
	
	public static void printSoln(HashMap<String,String> parentMap, int dim){
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
		
		System.out.println();
		System.out.println("************* Path from Start to Goal *************");
		System.out.println();
		for(int a=pathArr.size()-1;a>=0;a--){
			System.out.print(pathArr.get(a)+" , ");
		}
		System.out.println(String.valueOf(dim-1)+"-"+String.valueOf(dim-1));
		System.out.println();
		System.out.println("************* Path from Start to Goal *************");
		System.out.println();
	}
	
	public static double eucliDist(int row, int col, int dim){
		return (Math.sqrt(Math.pow((dim-row-1), 2)+Math.pow((dim-col-1), 2)));
	}
	
}
