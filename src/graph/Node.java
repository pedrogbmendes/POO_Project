package graph;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Node class that stores, for each node, its list of neighbours in the form of a linkedlist
 */
public class Node {
	
	public LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
	
	/**
	 * Function that prints the list of neighbours of a node
	 * 
	 * @return [string] String with the neighbours of the node
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