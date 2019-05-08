package events;

import simulation.Events;

/**
 * Abstract class that extends an event Observation
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public abstract class Observation extends Events{

	/**
	 * Sets the time to trigger the observation
	 * 
	 * @param time Time to trigger the event
	 */
	public Observation (double time) {
		super(time);
	}
	
	/**
	 * Triggers the event Observation
	 */
	public void TriggerEvent() {
		WriteObservation();
	}
	
	/**
	 * Abstract function that implements the observation in order to
	 * write in the console the state of the simulation
	 */
	protected abstract void WriteObservation();



}
