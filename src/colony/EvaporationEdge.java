package colony;

import events.Evaporation;

/**
 * Class which defines an evaporation event for this specific population simulation and triggers it
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public class EvaporationEdge extends Evaporation{
	
	private Colony col;
	
	/**
	 * Constructor that creates an event for evaporation the pheromone level
	 * in an edge
	 * 
	 * @param timeinstant Time to trigger the event
	 * @param n1 Node to identify the edge
	 * @param n2 Node to identify the edge
	 * @param _col Object to the Colony
	 */
	public EvaporationEdge(double timeinstant, int n1, int n2, Colony _col) {
		super(timeinstant, n1, n2);
		
		this.col = _col;
	}

	/**
	 * Triggers an edge's evaporation and increments the simulation's total evaporations counter
	 */
	protected void EvaporationEvent() {
		this.col.simColony.evapCounter ++; //count evaporations
		
		this.col.simColony.Evaporation(this.edgeN1, this.edgeN2);	//perform the evaporation event
	}
	
}
