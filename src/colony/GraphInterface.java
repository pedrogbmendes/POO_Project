package colony;

import java.util.LinkedList;
import graph.Weight;

/**
 * Interface for the graph, where are define the methods implemented in
 * the Graph Package.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public interface GraphInterface {

	/**
	 * Function that sets the pheromone level of an edge in the graph.
	 * 
	 * @param myID Node to identify the edge
	 * @param neighborID Node to identify the edge
	 * @param phero Pheromone level to be store in the edge
	 */
	void setPheromone(int myID, int neighborID, double phero);
	
	
	/**
	 * Function that gets the pheromone level of an edge in the graph.
	 * 
	 * @param myID Node to identify the edge
	 * @param neighborID Node to identify the edge
	 * @return Pheromone level of the edge
	 */
	double getPheromone(int myID, int neighborID);
	
	
	/**
	 * Function that decrements the pheromone level of an edge.
	 * 
	 * @param myId Node to identify the edge
	 * @param neighborId Node to identify the edge
	 * @param dec Value to decrement to the edge
	 */
	void decrementPheromone(int myId, int neighborId, double dec);
	
	
	/**
	 * Function that returns the weight of an edge.
	 * 
	 * @param myID Node to identify the edge
	 * @param neighborID Node to identify the edge
	 * @return weight of the edge
	 */
	int getWeight(int myID, int neighborID);
	
	
	/**
	 * Function that determines the number of neighbors of a node.
	 * 
	 * @param id Node's Identifier 
	 * @return Number of neighbor of a node
	 */
	int numberNeighbor(int id);
	
	
	/**
	 * Function that returns the list of neighbors of a node.
	 * 
	 * @param id Node's Identifier
	 * @return list of neighbors
	 */
	LinkedList<Weight> getListNeighbor(int id);
	
	
	/**
	 * Function that gives the identifier number of a neighbor.
	 * 
	 * @param id Node's Identifier
	 * @param index Index of the position in the list of neighbors where the neighbor is stored
	 * @return Neighbor identifier
	 */
	int getNeighborID(int id, int index);
	
	
	/**
	 * Function that gives the sum of all weight of all the edges of a graph.
	 * 
	 * @return Sum of weight of all edges
	 */
	int getTotalWeight();
	

	/**
	 * Function that returns the number of node of the graph
	 * @return Number of nodes
	 */
	int getNumberNodes();
	
	
	/**
	 * Function that returns the identifier of the nest node
	 * 
	 * @return Nest Identifier
	 */
	int getNestNode();
	
	
	String toString();
	
}
