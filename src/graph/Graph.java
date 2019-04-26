package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import colony.GraphInterface;


public class Graph implements GraphInterface{

	public int nbnodes;
	public int nestnode;
	public Node[] arrayNodes;
	public int W;
	
	public Graph(int numberNodes, int nest, Node[] vnode, int W) {
		this.nbnodes = numberNodes;
		this.nestnode = nest;
		this.arrayNodes = vnode;
		this.W = W;
	}
	
		
	public void setPheromone(int myID, int neighborID, double phero) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID-1].listNeighbor.listIterator(0);
		Weight nodeWeight;
		
		while(iter.hasNext()) {
			nodeWeight = iter.next();
			if(nodeWeight.getID() == neighborID) {
				nodeWeight.setPheromone(phero);
				break;
			}
		}
		
		iter = this.arrayNodes[neighborID-1].listNeighbor.listIterator(0);
		while(iter.hasNext()) {
			nodeWeight = iter.next();
			if(nodeWeight.getID() == myID) {
				nodeWeight.setPheromone(phero);
				break;
			}
		}				
	}
	
	public double getPheromone(int myID, int neighborID) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID-1].listNeighbor.listIterator(0);
		double phero = 0;
		Weight nodeWeight;

		while(iter.hasNext()) {
			nodeWeight = iter.next();

			if(nodeWeight.getID() == neighborID) {
				phero = nodeWeight.getPheronome();
				break;
			}
		}

		return phero;
						
	}
	
	public void decrementPheromone(int myId, int neighborId, double dec) {
		
		double pheroVal = this.getPheromone(myId, neighborId);
		if(pheroVal != 0) {
			if(pheroVal < dec) {
				this.setPheromone(myId, neighborId, 0);
			}else {
				this.setPheromone(myId, neighborId, pheroVal-dec);
			}
		}
		
	}
	
	
	public int getTotalWeight() {
		return this.W;
	}
	
	public int getWeight(int myID, int neighborID) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID-1].listNeighbor.listIterator(0);
		int weight_ = 0;
		Weight nodeWeight;
		
		while(iter.hasNext()) {
			nodeWeight = iter.next();
			if(nodeWeight.getID() == neighborID) {
				weight_ = nodeWeight.getWeight();
			}
		}		
		return weight_;
						
	}
	
	
	public int numberNeighbor(int id) {
		return this.arrayNodes[id-1].listNeighbor.size();
		
	}
	
	
	public LinkedList<Weight> getListNeighbor(int id) {
		return this.arrayNodes[id-1].listNeighbor;
	}
	
	
	public int getNeighborID(int id, int index) {
		return this.arrayNodes[id-1].listNeighbor.get(index).targetnode;
	}
	
	
	public int getNumberNodes() {
		return this.nbnodes;
	}
	
	
	public int getNestNode() {
		return this.nestnode;
	}
	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", arrayNodes=\n" + Arrays.deepToString(arrayNodes)+ "]";
	}
	
	
	
	
}
