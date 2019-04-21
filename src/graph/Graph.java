package graph;

import java.util.Arrays;

public class Graph {

	public int nbnodes;
	public int nestnode;
	public Node[] arrayNodes;
	
	public Graph(int numberNodes, int nest, Node[] vnode) {
		this.nbnodes = numberNodes;
		this.nestnode = nest;
		this.arrayNodes = vnode;
		
	}

	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", arrayNodes=" + Arrays.toString(arrayNodes)+ "]";
	}
	
	
	
	
}
