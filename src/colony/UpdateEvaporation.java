package colony;




public class UpdateEvaporation{
	
	private double plevel, rho;

	
	
	public UpdateEvaporation(double plevel, double rho) {
		this.rho = rho;
		this.plevel = plevel;
	}
	
	void decPheromone(GraphInterface graph, int edgeN1, int edgeN2) {
		
		graph.decrementPheromone(edgeN1, edgeN2, this.rho);
		
	}
		
	public void incPheromone(GraphInterface graph, Ant ant) {
		
		double incVal = (this.plevel * graph.getTotalWeight()) / (ant.weightPath);
		double pheroLevel;
		
		int node1, node2; 
		
		for(int i=0 ; i < ant.path.size()-1; i++) {
			node1 = ant.path.get(i);
			node2 = ant.path.get(i+1);
			pheroLevel = graph.getPheromone(node1,node2);

			graph.setPheromone(node1, node2, pheroLevel + incVal);
		}
		
	}
	
	
	

	
	
}
