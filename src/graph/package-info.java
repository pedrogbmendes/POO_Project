/**
 * Package that implements the graph and all the information related with it.
 * 
 * <p>
 * The graph is implemented using a adjacency list that saves all the information
 * related with the edges (identifier of the edge, the repective weight and pheromone level).
 * 
 * The adjacency list is implemented in an array of {@link Node} and each node has a list of 
 * {@link Weight} to store the neighbors.
 * 
 * <p>
 * The {@link Graph} also implements the methods define in the an interface.
 * 
 * @see simulation.GraphInterface
 * @see Graph
 * @see Node
 * @see Weight
 * 
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
package graph;
