package graph;

import java.util.LinkedList;
import java.util.Iterator;


public class Node {
	
	public LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
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