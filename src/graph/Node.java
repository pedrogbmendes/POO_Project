package graph;

import java.util.LinkedList;
import java.util.Iterator;


/**
 * Class that implements the adjacency list
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class Node {
	
	public LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
	
	/**
	 * Function that returns the adjacency list of neighbors of a node
	 * 
	 * @return [string] String with the neighbors of the node
	 */
	@Override
	public String toString() {
		Iterator<Weight> iter = this.listNeighbor.listIterator(0);
		String list = new String();
		while(iter.hasNext() ) {
			list += iter.next().toString();
		}
		
		return "Node [listNeighbor=\n" + list  + "]\n";
	}
	
	

	
}