package app;
import java.util.Comparator;
import java.util.PriorityQueue;
import app.GridDetails;


public class Mazerunner{
	
	static int[][] maze;
	static int maxMoves = 0; //upperbound, should not execute more than this number of moves, otherwise stuck in infinite loop
	
	public static void main(String args[]){
		int dimension = 10;
		double p = 0.25;
		
		createMaze(dimension, p);
		System.out.println("Original Maze: ");
		printMaze(maze);
		
		long startTime = System.nanoTime();
		ManhattanAStarSearch();
		long endTime = System.nanoTime();
		System.out.println("Manhattan A* Search Took " + (0.000001)*(endTime - startTime) + " ms"); 
		System.out.println();
		
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
	
	public static void ManhattanAStarSearch(){
		int[][] manhattanMaze = copyMaze(maze);
		int dim = maze.length;
		boolean[][] notVisited = new boolean[dim][dim];
		PriorityQueue<GridDetails> queue = new PriorityQueue<GridDetails>();
		
		int i=0, j=0, currentMove=1;
		notVisited[0][0] = true;
		manhattanMaze[0][0] = 0;
		
		if (manhattanMaze[1][0] == 0){
			queue.add(new GridDetails(1,0,8+9,0));
		}
		if (manhattanMaze[0][1] == 0){
			queue.add(new GridDetails(0,1,8+9,0));
		}
		
		while (!(i == (dim-1) && j == (dim-1)) && currentMove<maxMoves && !queue.isEmpty()) {
			GridDetails current = queue.remove();
			int x = current.getRow();
			int y = current.getCol();
			manhattanMaze[x][y] = current.getOriginDist() + 1;
			notVisited[x][y] = true;
			
			i=x;
			j=y;
			currentMove++;
			
			if ((j-1) >= 0 && manhattanMaze[i][j-1]==0 && notVisited[i][j-1]==false){
				//System.out.println(i + ", " + (j-1));
				queue.add(new GridDetails(i,(j-1),((dim-1)-i)+((dim-1)-(j-1)),manhattanMaze[i][j]));
			}
			if ((i-1) >= 0 && manhattanMaze[i-1][j]==0 && notVisited[i-1][j]==false){
				//System.out.println((i-1) + ", " + (j));
				queue.add(new GridDetails((i-1),j,((dim-1)-(i-1))+((dim-1)-j),manhattanMaze[i][j]));
			}
			if ((i+1) < dim && manhattanMaze[i+1][j]==0 && notVisited[i+1][j]==false){
				//System.out.println((i+1) + ", " + j);
				queue.add(new GridDetails((i+1),j,((dim-1)-(i+1))+((dim-1)-j),manhattanMaze[i][j]));
			}
			if ((j+1) < dim && manhattanMaze[i][j+1]==0 && notVisited[i][j+1]==false){
				//System.out.println(i + ", " + (j+1));
				queue.add(new GridDetails(i,(j+1),((dim-1)-i)+((dim-1)-(j+1)),manhattanMaze[i][j]));
			}
			
		};
		
		System.out.println("A*: Manhattan Distance Solution: ");
		if(!(i==dim-1 && j==dim-1)){
			System.out.println("No solution found.");
		}
		printMaze(manhattanMaze);
		System.out.println("Number of moves searched: " + currentMove);
	}
	
}
