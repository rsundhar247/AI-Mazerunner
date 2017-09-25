package app;

public class GridDetails implements Comparable<GridDetails> {

	private int row;
	private int col;
	private int dist;
	private int originDist;
	
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
	public int getDist() {
		return dist;
	}
	public void setdist(int dist) {
		this.dist = dist;
	}
	public int getOriginDist() {
		return originDist;
	}
	public void setOriginDist(int originDist) {
		this.originDist = originDist;
	}
	
	public GridDetails(int r, int c, int m, int o){
		setRow(r);
		setCol(c);
		setdist(m);
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
	
	public String toString(){
		return "Row "+getRow()+" Col "+getCol()+" dist "+getDist()+" OriginDist "+getOriginDist();
	}
}
