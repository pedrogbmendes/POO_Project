package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import tsp.GraphInterface;


public class Graph implements GraphInterface{

	public int nbnodes;
	public int nestnode;
	public Node[] arrayNodes;
	
	public Graph(int numberNodes, int nest, Node[] vnode) {
		this.nbnodes = numberNodes;
		this.nestnode = nest;
		this.arrayNodes = vnode;
		
	}
	
	
	
	public void setPheromone(int myID, int neighborID, float phero) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID].listNeighbor.listIterator(0);
		
		while(iter.hasNext()) {
			if(iter.next().getID() == neighborID) {
				iter.next().setPheromone(phero);
				break;
			}
		}
		
		iter = this.arrayNodes[neighborID].listNeighbor.listIterator(0);
		while(iter.hasNext()) {
			if(iter.next().getID() == myID) {
				iter.next().setPheromone(phero);
				break;
			}
		}				
	}
	
	public float getPheromone(int myID, int neighborID) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID].listNeighbor.listIterator(0);
		float phero = 0;
		
		while(iter.hasNext()) {
			if(iter.next().getID() == neighborID) {
				phero = iter.next().getPheronome();
			}
		}
		
		return phero;
						
	}
	
	public int getWeight(int myID, int neighborID) {
		
		ListIterator<Weight> iter = this.arrayNodes[myID].listNeighbor.listIterator(0);
		int weight_ = 0;
		
		while(iter.hasNext()) {
			if(iter.next().getID() == neighborID) {
				weight_ = iter.next().getWeight();
			}
		}		
		return weight_;
						
	}
	
	public int numberNeighbor(int id) {
		return arrayNodes[id-1].listNeighbor.size();
		
	}
	
	public LinkedList<Weight> getListNeighbor(int id) {
		return arrayNodes[id-1].listNeighbor;
	}
	
	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", arrayNodes=\n" + Arrays.deepToString(arrayNodes)+ "]";
	}
	
	
	
	
}
