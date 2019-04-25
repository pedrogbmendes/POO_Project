package events;

import simulation.Events;

public abstract class Evaporation extends Events{

	protected int edgeN1, edgeN2;
	
	public Evaporation(double timeinstant, int n1, int n2) {
		super(timeinstant);
		
		this.edgeN1 = n1;
		this.edgeN2 = n2;
		
	}
	
	
	public void TriggerEvent() {
		 EvaporationEvent();
	}
	
	protected abstract void EvaporationEvent();

}
