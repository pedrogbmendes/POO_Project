package colony;

import java.util.LinkedList;
import java.util.Random;

//import graph.Weight;
import tsp.GraphInterface;

public class UpdateMoveAnt{

	private float alpha, beta, delta;
	
	
	public UpdateMoveAnt(float _alpha, float _beta, float _delta) {
		this.alpha = _alpha;
		this.beta = _beta;
		this.delta = _delta;
		
	}
	
	public int AntMove(Ant ant, GraphInterface graph) {
		
		int nextNode = calculateNextNode(ant, graph);
		
		
		//verifies if the ant already pass on the newNode
		if( ant.path.contains(nextNode) ) {
			int index = ant.path.indexOf(nextNode);
			int size = ant.path.size();
			int last, before;
			
			for(int i=size-1; i>index; i--) {
				last = ant.path.pollLast();
				before = ant.path.getLast();
								
				ant.weightPath -= graph.getWeight(before, last);
			}	
		}
				
		return nextNode;
	}
	
	
	private int calculateNextNode(Ant ant, GraphInterface graph){
	
		LinkedList<Double> c_ij = new LinkedList<Double>();
		LinkedList<Integer> notVisitNeighbor = new LinkedList<Integer>();
		
		int nextNode=1, i, a_ij, neighborID;
		double c_i=0, f_ij;
		
		Random rand = new Random();
		
		int numberNeighbor = graph.numberNeighbor(ant.actualNode);
		
		for(i=0; i<numberNeighbor; i++  ) {
			neighborID = graph.getNeighborID(ant.actualNode, i);
					
			if(!ant.path.contains(neighborID)){
				f_ij = graph.getPheromone(ant.actualNode,neighborID);
				a_ij = graph.getWeight(ant.actualNode,neighborID); 
				
				c_ij.add( (this.alpha + f_ij) / (this.beta + a_ij) );
				notVisitNeighbor.add(neighborID);
				
			}
		}
		
		if(c_ij.isEmpty()) {
			//all neighbors were visited
			int randNumberint = rand.nextInt(numberNeighbor);
			nextNode = graph.getListNeighbor(ant.actualNode).get(randNumberint).targetnode;
			
			
		}else {
			//there are neighbor nodes that were not visit yet
			Double[] arrayProb = new Double[c_ij.size()];
			Double[] intervalProb = new Double[c_ij.size()-1];
					
			for(Double d : c_ij)
				c_i += d;
			
			int size = c_ij.size();
			
			for(i = 0; i < size; i++)
				arrayProb[i] = c_ij.get(i) / c_i;

			
			double randNumber = rand.nextDouble();
			
			intervalProb[0] = arrayProb[0];
			if(randNumber <= intervalProb[0]) {
				nextNode = notVisitNeighbor.get(0);
			}else {
				for(i = 1; i < size; i++) {
				
					intervalProb[i] = intervalProb[i-1] + arrayProb[i];
				
					if(randNumber <= intervalProb[i])
						nextNode = notVisitNeighbor.get(i);
				}
			}
							
			
		}
		
		return nextNode;	
	}
		
}
