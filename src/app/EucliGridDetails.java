package app;

public class EucliGridDetails implements Comparable<EucliGridDetails> {

	private int row;
	private int col;
	private double eucliDist;
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
	public double getEucliDist() {
		return eucliDist;
	}
	public void setEucliDist(double eucliDist) {
		this.eucliDist = eucliDist;
	}
	public double getOriginDist() {
		return originDist;
	}
	public void setOriginDist(double originDist) {
		this.originDist = originDist;
	}
	
	public EucliGridDetails(int r, int c, double e, double o){
		setRow(r);
		setCol(c);
		setEucliDist(e);
		setOriginDist(o);
	}
	
	public boolean equals(EucliGridDetails o){
		return (this.getEucliDist()+this.getOriginDist()) == (o.getEucliDist()+o.getOriginDist());
	}
	
	public int compareTo(EucliGridDetails o){
		if(this.equals(o))
			return 0;
		else if((getEucliDist()+getOriginDist()) > (o.getEucliDist()+o.getOriginDist()))
			return 1;
		else 
			return -1;
	}
	
	public String toString(){
		return "Row "+getRow()+" Col "+getCol()+" EucliDist "+getEucliDist()+" OriginDist "+getOriginDist();
	}
}
