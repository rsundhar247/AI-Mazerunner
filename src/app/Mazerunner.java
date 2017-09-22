package app;

public class Mazerunner{
	
	static int[][] maze;
	public static void main(String args[]){
		int dimension = 10;
		double p = 0.25;
		
		createMaze(dimension, p);
		printMaze();
		
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
	
	public static void printMaze(){
		if (maze == null){
			return;
		}
		for (int i = 0; i< maze.length; i++){
			for (int j = 0; j < maze[0].length; j++){
				if(maze[i][j] == 0 || maze[i][j] == 1){
					System.out.print(" ");				//space to keep appearance clean
				}
				if (j == maze[0].length - 1) {
					System.out.println(maze[i][j]);
				} else {
					System.out.print(maze[i][j]);
				}
			}
		}
	}
	
}
