package app;
import java.util.Arrays;

public class Mazerunner{
	
	static int[][] maze;
	static int maxMoves = 0; //upperbound for our algorithm, should not execute more than this number of moves, otherwise stuck in loop
	
	public static void main(String args[]){
		int dimension = 10;
		double p = 0.25;
		maxMoves = dimension*dimension;
		
		createMaze(dimension, p);
		printMaze(maze);
		
		ManhattanAStarSearch();
		printMaze(maze);
		
	}
	public static void createMaze(int dim, double p){
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
				if(maze[i][j] != -1){
					System.out.print(" "); //space to keep appearance clean
				}
				if (j == maze[0].length - 1) {
					System.out.println(maze[i][j]);
				} else {
					System.out.print(maze[i][j]);
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
		manhattanMaze[0][0] = 5;
		
		printMaze(manhattanMaze);
	}
	
}
