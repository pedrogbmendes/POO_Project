package events;

import simulation.Events;


public abstract class Move extends Events{

	protected int ID;
	
	public Move(double timeinstant,int Id) {
		super(timeinstant);
		
		this.ID = Id;
	}
	
	
	public void TriggerEvent() {
		MoveEvent();
	}

	protected abstract void MoveEvent();
	
}
