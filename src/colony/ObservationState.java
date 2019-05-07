package colony;


import events.Observation;

/**
 * Class which defines an observation event and triggers it
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class ObservationState extends Observation{

	SimulationColony simu;
	
	/**
	 * Creates the observation event
	 * 
	 * @param time Time to perform the observation
	 * @param _simu	Object for the simulation
	 */
	public ObservationState(double time, SimulationColony _simu) {
		super(time);
		this.simu = _simu;
		
	}
	
	/**
	 * Triggers a simulation observation, adds to the simulation's total observations counter and prints the number of
	 * observations, moves, evaporations until then, and the best hamiltonian cycle found yet, if there is one
	 */
	protected void WriteObservation() {
		
		this.simu.obsCounter ++;
		System.out.println("Observation " + this.simu.obsCounter + ":\n");
		System.out.println("\t\t Present instant: \t\t " + this.getTimeEvent() + "\n");
		System.out.println("\t\t Number of move events: \t " + this.simu.moveCounter + "\n");
		System.out.println("\t\t Number of evaporation events: \t " + this.simu.evapCounter + "\n");
		System.out.println("\t\t Hamiltonian cycle: \t\t " + this.simu.toString() + "\n");
		//System.out.println(this.simu.colony.graph.toString());
		this.simu.scheduleObservation();
	}


}
