package colony;


import events.Move;

/**
 * Class which defines a move event and triggers it
 * @author Rui
 *
 */
public class MoveAnt extends Move {

	Colony col;
	
	public MoveAnt(double timeinstant, int Id, Colony _col) {
		super(timeinstant, Id);
		this.col = _col;
	}

	/**
	 * Trigger Function 
	 * Triggers an ant's move and adds to the simulation's total move counter
	 */
	protected void MoveEvent() {
		this.col.simColony.moveCounter ++;
		this.col.simColony.Move(this.ID);
	}
}
