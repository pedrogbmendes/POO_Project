package graph;

/**
 * Weight Class TO DO
 */
public class Weight{
	
	public int targetnode;
	int weight;
	double pheromone;
	
	/**
	 * Constructor of the class Weight. Intializes the target node's identificator, the weight of the edge and sets the pheromone level to zero
	 * 
	 * @param neigborID Identificator of the neighbour node
	 * @param weight Weight of the edge
	 */
	public Weight(int neighborID, int weight) {
		this.targetnode = neighborID;
		this.weight = weight;
		this.pheromone = 0;
	}
	
	/**
	 * Function that sets the weight of the edge
	 * 
	 * @param weightEdge Weight of the edge
	 */
	public void setWeight(int weightEdge) {
		this.weight = weightEdge;
		
	}
	
	
	/**
	 * Function that sets a pheromone level in the edge
	 * 
	 * @param phero Amount of pheromone to be set to the edge
	 */
	public void setPheromone(double phero) {
		this.pheromone = phero;
		
	}
	
	
	/**
	 * Function that sets a target node
	 * 
	 * @param id Identificator of the target node
	 */
	public void setTargernode(int id) {
		this.targetnode = id;
		
	}
	
	
	/**
	 *  Function that returns the id of the target node
	 *  
	 *  @return Target node's id
	 */
	int getID() {
		return this.targetnode;
	}
	
	
	/**
	 * Function that returns the weights of the edge
	 * 
	 * @return Weight of the edge
	 */
	int getWeight() {
		return this.weight;
	}
	
	
	/**
	 * Function that returns the pheromone level of the edge
	 * 
	 * @return Pheromone level of the edge
	 */
	double getPheronome() {
		return this.pheromone;
	}

	
	/**
	 * Function that prints the target node, the weight and the pheromone level of the edge
	 * 
	 * @return String with the target node, weight and the pheromone level of the edge
	 */
	@Override
	public String toString() {
		return "\tWeight [targetnode=" + targetnode + ", weight=" + weight + ", pheromone=" + pheromone +"]\n";
	}
	
}