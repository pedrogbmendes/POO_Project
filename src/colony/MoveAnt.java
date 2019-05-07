package colony;


import events.Move;

/**
 * Class which defines a move event for this specific population simulation and triggers it
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class MoveAnt extends Move {

	Colony col;
	
	/**
	 * Creates a new event to move an ant 
	 * 
	 * @param timeinstant Time instant to trigger the event
	 * @param Id Ant's identifier
	 * @param _col Object to the Colony
	 */
	public MoveAnt(double timeinstant, int Id, Colony _col) {
		super(timeinstant, Id);
		this.col = _col;
	}

	/**
	 * Triggers an ant's move and adds to the simulation's total move counter
	 */
	protected void MoveEvent() {
		this.col.simColony.moveCounter ++;
		this.col.simColony.Move(this.ID);
	}
}
