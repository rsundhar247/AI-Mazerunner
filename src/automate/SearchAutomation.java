package automate;

import java.util.HashMap;

public class SearchAutomation {
	
	public static void main(String args[]){
		int dimension = 15;
		int numOfTimes = 50;
		
		
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
		
		for(int i=0;i<numOfTimes;) {
			searchResults = maze.gridSearch(dimension);

			if(searchResults.containsKey("DFSMazeSearchTime")) {
				dfsTime[i] = searchResults.get("DFSMazeSearchTime");
				dfsMoves[i] = searchResults.get("DFSMazeSearchMoves");
				bfsTime[i] = searchResults.get("BFSMazeSearchTime");
				bfsMoves[i] = searchResults.get("BFSMazeSearchMoves");
				manhatTime[i] = searchResults.get("ManhatMazeSearchTime");
				manhatMoves[i] = searchResults.get("ManhatMazeSearchMoves");
				eucliTime[i] = searchResults.get("EucliMazeSearchTime");
				eucliMoves[i] = searchResults.get("EucliMazeSearchMoves");
				i++;
			}
		}
		
		System.out.println("DFS Time :: ");
		for(int i=0;i<dfsTime.length;i++){
			System.out.print(dfsTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("DFS Moves :: ");
		for(int i=0;i<dfsMoves.length;i++){
			System.out.print(dfsMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("BFS Time :: ");
		for(int i=0;i<bfsTime.length;i++){
			System.out.print(bfsTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("BFS Moves :: ");
		for(int i=0;i<bfsMoves.length;i++){
			System.out.print(bfsMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Manhat Time :: ");
		for(int i=0;i<manhatTime.length;i++){
			System.out.print(manhatTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Manhat Moves :: ");
		for(int i=0;i<manhatMoves.length;i++){
			System.out.print(manhatMoves[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Eucli Times :: ");
		for(int i=0;i<eucliTime.length;i++){
			System.out.print(eucliTime[i]+" , ");
		}
		System.out.println();
		
		System.out.println("Eucli Moves :: ");
		for(int i=0;i<eucliMoves.length;i++){
			System.out.print(eucliMoves[i]+" , ");
		}
		//System.out.println(searchResults);
	}
}