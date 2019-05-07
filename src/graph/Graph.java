package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import colony.GraphInterface;


/**
 * Class that saves all the information about the graph,
 * including the number of node, the nest and implements 
 * the adjacency list and the respective methods.
 *
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class Graph implements GraphInterface{

	public int nbnodes;
	public int nestnode;
	public Node[] arrayNodes;
	public int W;
	
	/**
	 * Constructor function of the Graph. Initializes the number of nodes, 
	 * the nest node, the array of nodes and the total weight of the graph
	 * 
	 * @param numberNodes Number of nodes in the graph
	 * @param nest Identifier of the nest node
	 * @param vnode Array of all nodes of the graph
	 * @param W Total weight of the graph
	 */
	public Graph(int numberNodes, int nest, Node[] vnode, int W) {
		this.nbnodes = numberNodes;
		this.nestnode = nest;
		this.arrayNodes = vnode;
		this.W = W;
	}
	
	
	/**
	 * Function that sets the pheromone level of an edge between two nodes to a specific amount
	 * 
	 * @param myID Identifier of the first node
	 * @param neighborID Identifier of the first node's neighbour
	 * @param phero Level of pheromone to be set to the edge 
	 */
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
	
	
	/**
	 * Function that returns the amount of pheromones in the edge between two nodes
	 * 
	 * @param myId Identifier of the first node
	 * @param neighborID Identifier of the first node's neighbour
	 * @return Amount of pheromones in the edge between the two nodes
	 */
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
	
	
	/**
	 * Function that decrements the amount of pheromones of the edge between to nodes
	 * 
	 * @param myID Identifier of the first node
	 * @param neighborID Identifier of the first node's neighbour
	 * @param dec Amount of pheromones to be decreased in the edge between the two nodes
	 */
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
	
	
	/**
	 * Function that returns the sum of weights of the edges of the graph
	 * 
	 * @return Total weight of the graph
	 */
	public int getTotalWeight() {
		return this.W;
	}
	
	
	/**
	 * Function that returns the weight of the edge between two nodes
	 * 
	 * @param myID Identifier of the first node
	 * @param neighborID Identifier of the first node's neighbour
	 * @return weight of the edge between the two nodes
	 */
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
	
	
	/**
	 * Function that returns the number of neighbors of a node
	 * 
	 * @param id Identifier of the node
	 * @return Number of neighbors of the node
	 */
	public int numberNeighbor(int id) {
		return this.arrayNodes[id-1].listNeighbor.size();	
	}
	
	
	/**
	 * Function that returns the list of neighbors of a node
	 * 
	 * @param id Identifier of the node
	 * @return List of the neighbors of the node 
	 */
	public LinkedList<Weight> getListNeighbor(int id) {
		return this.arrayNodes[id-1].listNeighbor;
	}
	
	
	/**
	 * Function that returns the identifier of the ith neighbor of a node
	 * 
	 * @param id identifier of the node
	 * @param index index of the neighbor in the node's neighbor list
	 * @return identifier of the neighbor
	 */
	public int getNeighborID(int id, int index) {
		return this.arrayNodes[id-1].listNeighbor.get(index).targetnode;
	}
	
	
	/**
	 * Function that returns the number of nodes in the graph
	 * 
	 * @return Number of nodes in the graph
	 */
	public int getNumberNodes() {
		return this.nbnodes;
	}
	
	
	/**
	 * Function that returns the identifier of the nest
	 * 
	 * @return Identifier of the nest node of the graph
	 */
	public int getNestNode() {
		return this.nestnode;
	}
	
	
	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", arrayNodes=\n" + Arrays.deepToString(arrayNodes)+ "]";
	}
	
}
