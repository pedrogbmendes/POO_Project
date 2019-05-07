package colony;

import java.util.LinkedList;


/**
 * Class Ant that identifies an ant, its path and the weight of its path
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class Ant {

	int actualNode;
	LinkedList<Integer> path = new LinkedList<Integer>();
	int weightPath;
	
	
	/**
	 * Ant's creation 
	 * 
	 * @param nest Nest node
	 */
	public Ant(int nest) {
		this.actualNode = nest;
		this.path.add(nest);
		this.weightPath = 0;
	
	}
	
	/**
	 * Function that updates the ant's position and its path
	 * 
	 * @param newNode Node to which the ant will move
	 * @param addWeight Weight associated with the edge the ant will traverse
	 */
	public void updateAnt(int newNode, int addWeight){
		
		this.actualNode = newNode;
		this.path.add(newNode);
		this.weightPath += addWeight;
		
	}
	
	/**
	 * Function that updates the ant's position when a node it's 
	 * repeated in the ant's path
	 * 
	 * @param newNode Node to which the ant will move
	 */
	public void updateAnt(int newNode){
		
		this.actualNode = newNode;
	}
	
}
