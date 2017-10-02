package app;

/*
 * Comparable Class for a cell in a maze that stores a calculated heuristic distance to the destination
 * as well as the true distance to the cell from the origin
 */
public class GridDetails implements Comparable<GridDetails> {

	private int row;
	private int col;
	private double heuristicDist;
	private double originDist;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}	
	public double getHeuristicDist() {
		return heuristicDist;
	}
	public void setHeuristicDist(double heuristicDist) {
		this.heuristicDist = heuristicDist;
	}
	public double getOriginDist() {
		return originDist;
	}
	public void setOriginDist(double originDist) {
		this.originDist = originDist;
	}
	
	public GridDetails(int r, int c, double e, double o){
		setRow(r);
		setCol(c);
		setHeuristicDist(e);
		setOriginDist(o);
	}
	
	public boolean equals(GridDetails o){
		return (this.getHeuristicDist()+this.getOriginDist()) == (o.getHeuristicDist()+o.getOriginDist());
	}
	
	public int compareTo(GridDetails o){
		if(this.equals(o))
			return 0;
		else if((getHeuristicDist()+getOriginDist()) > (o.getHeuristicDist()+o.getOriginDist()))
			return 1;
		else 
			return -1;
	}
	
}
