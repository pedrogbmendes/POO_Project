package colony;


/**
 * Class that will handle the evaporation updates on a specific edge.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class UpdateEvaporation{
	
	private double plevel, rho;
	
	/**
	 * Constructor to handle the evaporation events.
	 * 
	 * @param plevel Parameter concerning pheromone level when the Hamiltonian cycle is complete
	 * @param rho Parameter concerning the pheromone evaporation event w.r.t 
	 * the rate at which each edge will evaporate the pheromones 
	 */
	public UpdateEvaporation(double plevel, double rho) {
		this.rho = rho;
		this.plevel = plevel;
	}
	
	/**
	 * Decrements the level of pheromones on a specific edge of the graph.
	 * 
	 * @param graph Graph
	 * @param edgeN1 Node to identify an edge
	 * @param edgeN2 Node to identify an edge
	 * @return Pheromone level on the specified edge
	 */
	double decPheromone(GraphInterface graph, int edgeN1, int edgeN2) {
		
		graph.decrementPheromone(edgeN1, edgeN2, this.rho);
		return graph.getPheromone(edgeN1, edgeN2);
		
	}
		
	
	/**
	 * Increments the pheromone level of a specified edge based on an ant's path
	 * 
	 * @param colony Object to the colony
	 * @param antID Ant's identifier
	 */
	void incPheromone(Colony colony, int antID) {
		
		double incVal = (this.plevel * colony.graph.getTotalWeight()) / (colony.AntColony[antID].weightPath);
		double pheroLevel;
		
		int node1, node2; 
		
		for(int i=0 ; i < colony.AntColony[antID].path.size()-1; i++) {
			node1 = colony.AntColony[antID].path.get(i);
			node2 = colony.AntColony[antID].path.get(i+1);
						
			if(colony.graph.getPheromone(node1, node2) == 0) {
				colony.simColony.scheduleEvaporation(node1, node2);
			}
			
			pheroLevel = colony.graph.getPheromone(node1,node2);

			colony.graph.setPheromone(node1, node2, pheroLevel + incVal);
			
			
		}
	}
	
}
