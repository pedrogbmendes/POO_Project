package events;

import simulation.Events;

/**
 * Abstract class that extends an event for a specific type Evaporation
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public abstract class Evaporation extends Events{

	protected int edgeN1, edgeN2;
	
	/**
	 * Constructor that creates an event evaporation of an edge 
	 * in the time instant timeinstant to be trigger 
	 * 
	 * @param timeinstant Time to trigger the event
	 * @param n1 Node identifier
	 * @param n2 Node identifier
	 */
	public Evaporation(double timeinstant, int n1, int n2) {
		super(timeinstant);
		
		this.edgeN1 = n1;
		this.edgeN2 = n2;
	}
	
	
	/**
	 * Triggers the event Evaporation
	 */
	public void TriggerEvent() {
		 EvaporationEvent();
	}
	
	
	/**
	 * Abstract function that is implemented depending the type of the population 
	 * and runs when an event is triggered
	 */
	protected abstract void EvaporationEvent();

}
