package simulation;

public class Weight {

	int targetnode;
	int weight;
	
	public Weight(int neighborID, int weight) {
		this.targetnode = neighborID;
		this.weight = weight;
	}
	
}
