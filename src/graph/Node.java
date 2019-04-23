package graph;

import java.util.LinkedList;
import java.util.Iterator;


public class Node {
	
	public int nodeidx;
	public LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
	public Node(int id) {
		this.nodeidx = id;
	}

	@Override
	public String toString() {
		Iterator<Weight> iter = this.listNeighbor.listIterator(0);
		String list = new String();
		while(iter.hasNext() ) {
			list += iter.next().toString();
		}
		
		return "Node [nodeidx=" + nodeidx + ", listNeighbor=\n" + list  + "]\n";
	}
	
	

	
}