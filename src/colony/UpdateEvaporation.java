package colony;

import tsp.GraphInterface;

public class UpdateEvaporation {
	
	private float eta;
	private float rho;
	
	public UpdateEvaporation(float eta, float rho) {
		this.eta = eta;
		this.rho = rho;
	}
	
	public void decPheromone(GraphInterface graph, int node1, int node2) {
		
		float pheroVal = graph.getPheromone(node1, node2);
		if(pheroVal != 0) {
			if(pheroVal < this.rho) {
				graph.setPheromone(node1, node2, 0);
			}else {
				graph.setPheromone(node1, node2, pheroVal-this.rho);
			}
		}
		
	}
	
	public void incPheromone(GraphInterface graph, Ant ant) {
		
		float incVal = (this.eta * graph.getTotalWeight()) / (ant.weightPath);
		float pheroLevel;
		
		int node1, node2; 
		
		for(int i=0 ; i < ant.path.size()-1; i++) {
			node1 = ant.path.get(i);
			node2 = ant.path.get(i+1);
			pheroLevel = graph.getPheromone(node1,node2);

			graph.setPheromone(node1, node2, pheroLevel + incVal);
		}
		
	}
	
	
}
