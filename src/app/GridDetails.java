package app;

public class GridDetails implements Comparable<GridDetails> {

	private int row;
	private int col;
<<<<<<< HEAD
	private double dist;
=======
	private double heuristicDist;
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
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
<<<<<<< HEAD
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
=======
	public double getHeuristicDist() {
		return heuristicDist;
	}
	public void setHeuristicDist(double heuristicDist) {
		this.heuristicDist = heuristicDist;
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
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
<<<<<<< HEAD
		setDist(e);
=======
		setHeuristicDist(e);
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
		setOriginDist(o);
	}
	
	public boolean equals(GridDetails o){
<<<<<<< HEAD
		return (this.getDist()+this.getOriginDist()) == (o.getDist()+o.getOriginDist());
=======
		return (this.getHeuristicDist()+this.getOriginDist()) == (o.getHeuristicDist()+o.getOriginDist());
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
	}
	
	public int compareTo(GridDetails o){
		if(this.equals(o))
			return 0;
<<<<<<< HEAD
		else if((getDist()+getOriginDist()) > (o.getDist()+o.getOriginDist()))
=======
		else if((getHeuristicDist()+getOriginDist()) > (o.getHeuristicDist()+o.getOriginDist()))
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
			return 1;
		else 
			return -1;
	}
	
<<<<<<< HEAD
}
=======
}
>>>>>>> c79146cb04c03fe5b8257de23056067df8c76a48
