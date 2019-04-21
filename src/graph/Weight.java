package graph;


public class Weight {

	public int targetnode;
	public int weight;
	
	public Weight(int neighborID, int weight) {
		this.targetnode = neighborID;
		this.weight = weight;
	}
	
	public void setWeight(int weightEdge) {
		this.weight = weightEdge;
		
	}
	
	public int getID() {
		return this.targetnode;
	}
	
}