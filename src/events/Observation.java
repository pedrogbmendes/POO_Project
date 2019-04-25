package events;

import simulation.Events;

public abstract class Observation extends Events{

	public Observation (double time) {
		super(time);
	}
	
	public void TriggerEvent() {
		WriteObservation();
	}
	
	protected abstract void WriteObservation();



}
