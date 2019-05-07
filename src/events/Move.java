package events;

import simulation.Events;


/**
 * Abstract class that extends an event for a specific type Move and
 * moves one individual of a population 
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public abstract class Move extends Events{

	protected int ID;
	
	/**
	 * Constructor that creates an event evaporation of an edge 
	 * in the time instant timeinstant to be trigger 
	 * 
	 * 
	 * @param timeinstant Time to trigger the event
	 * @param Id Identifier of a specimen 
	 */
	public Move(double timeinstant,int Id) {
		super(timeinstant);
		
		this.ID = Id;
	}
	
	
	/**
	 * Triggers the event Move
	 */
	public void TriggerEvent() {
		MoveEvent();
	}

	/**
	 * Abstract function that is implemented depending the type of the population 
	 * and runs when an event move is triggered
	 */
	protected abstract void MoveEvent();
	
}
