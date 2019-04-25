package events;

import simulation.Events;
import colony.SimulationColony;

public class Move extends Events{

	protected int ID;
	
	
	public Move(double timeinstant,int Id) {
		super(timeinstant);
		
		this.ID = Id;
	}
	
	
	public void TriggerEvent() {
		Move(this.ID);
		
	}

}
