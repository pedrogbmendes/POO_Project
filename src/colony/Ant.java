package colony;

import java.util.LinkedList;


public class Ant {

	int actualNode;
	LinkedList<Integer> path = new LinkedList<Integer>();
	int weightPath;
	
	
	public Ant(int nest) {
		this.actualNode = nest;
		this.path.add(nest);
		this.weightPath = 0;
	
	}
	
	public void updateAnt(int newNode, int addWeight){
		
		this.actualNode = newNode;
		this.path.add(newNode);
		this.weightPath += addWeight;
		
	}
	
}
