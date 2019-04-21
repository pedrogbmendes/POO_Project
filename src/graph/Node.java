package graph;

import java.util.LinkedList;


public class Node {
	
	public int nodeidx;
	public LinkedList<Weight> listNeighbor = new LinkedList<Weight>();
	
	public Node(int id) {
		this.nodeidx = id;
	}
	

	
}