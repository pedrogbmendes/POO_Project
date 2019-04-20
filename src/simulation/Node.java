package simulation;

import java.util.LinkedList;

public class Node {
	
	int nodeidx;
	LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
	public Node(int id) {
		this.nodeidx = id;
	}
}
