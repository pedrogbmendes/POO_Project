package colony;

import java.util.LinkedList;


/**
 * @author pedro
 *
 */
public class Ant {

	int actualNode;
	LinkedList<Integer> path = new LinkedList<Integer>();
	int weightPath;
	
	
	/**
	 * @param nest
	 */
	public Ant(int nest) {
		this.actualNode = nest;
		this.path.add(nest);
		this.weightPath = 0;
	
	}
	
	/**
	 * @param newNode
	 * @param addWeight
	 */
	public void updateAnt(int newNode, int addWeight){
		
		this.actualNode = newNode;
		this.path.add(newNode);
		this.weightPath += addWeight;
		
	}
	
	public void updateAnt(int newNode){
		
		this.actualNode = newNode;
	}
	
}
