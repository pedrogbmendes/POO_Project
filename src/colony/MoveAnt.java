package colony;


import events.Move;


public class MoveAnt extends Move {

	Colony col;
	
	public MoveAnt(double timeinstant, int Id, Colony _col) {
		super(timeinstant, Id);
		this.col = _col;
	}

	protected void MoveEvent() {
		this.col.simColony.moveCounter ++;
		this.col.simColony.Move(this.ID);
	}
}
