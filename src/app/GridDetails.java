package app;

public class GridDetails implements Comparable<GridDetails> {

	private int row;
	private int col;
	private double dist;
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
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
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
		setDist(e);
		setOriginDist(o);
	}
	
	public boolean equals(GridDetails o){
		return (this.getDist()+this.getOriginDist()) == (o.getDist()+o.getOriginDist());
	}
	
	public int compareTo(GridDetails o){
		if(this.equals(o))
			return 0;
		else if((getDist()+getOriginDist()) > (o.getDist()+o.getOriginDist()))
			return 1;
		else 
			return -1;
	}
	
}
