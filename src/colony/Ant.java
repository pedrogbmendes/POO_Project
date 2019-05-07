package colony;

import java.util.LinkedList;


/**
 * Class that the position and path of an ant
 * @author pedro
 *
 */
public class Ant {

	int actualNode;
	LinkedList<Integer> path = new LinkedList<Integer>();
	int weightPath;
	
	
	/**
	 * @param nest Node where the ant starts
	 */
	public Ant(int nest) {
		this.actualNode = nest;
		this.path.add(nest);
		this.weightPath = 0;
	
	}
	
	/**
	 * Ant position updater
	 * Updates the ant's position and its path
	 * @param newNode Node to which the ant will move
	 * @param addWeight Weight associated with the edge the ant will traverse
	 */
	public void updateAnt(int newNode, int addWeight){
		
		this.actualNode = newNode;
		this.path.add(newNode);
		this.weightPath += addWeight;
		
	}
	
	/**
	 * Ant position updater
	 * @param newNode
	 */
	public void updateAnt(int newNode){
		
		this.actualNode = newNode;
	}
	
}
