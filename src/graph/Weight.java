package graph;


public class Weight{

	int targetnode;
	int weight;
	float pheromone;
	
	public Weight(int neighborID, int weight) {
		this.targetnode = neighborID;
		this.weight = weight;
		this.pheromone = 0;
	}
	
	public void setWeight(int weightEdge) {
		this.weight = weightEdge;
		
	}
	
	public void setPheromone(float phero) {
		this.pheromone = phero;
		
	}
	
	public void setTargernode(int id) {
		this.targetnode = id;
		
	}
	
	int getID() {
		return this.targetnode;
	}
	
	int getWeight() {
		return this.weight;
	}
	
	float getPheronome() {
		return this.pheromone;
	}

	@Override
	public String toString() {
		return "\tWeight [targetnode=" + targetnode + ", weight=" + weight + ", pheromone=" + pheromone +"]\n";
	}
	
	
	
}