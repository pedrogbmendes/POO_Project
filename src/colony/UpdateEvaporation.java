package colony;




public class UpdateEvaporation{
	
	private double plevel, rho;

	
	
	public UpdateEvaporation(double plevel, double rho) {
		this.rho = rho;
		this.plevel = plevel;
	}
	
	double decPheromone(GraphInterface graph, int edgeN1, int edgeN2) {
		
		graph.decrementPheromone(edgeN1, edgeN2, this.rho);
		return graph.getPheromone(edgeN1, edgeN2);
		
	}
		
	
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
