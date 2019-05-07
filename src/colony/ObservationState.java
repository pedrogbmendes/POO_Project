package colony;


import events.Observation;

/**
 * Class which defines an observation event and triggers it
 * @author Rui
 *
 */
public class ObservationState extends Observation{

	SimulationColony simu;
	
	public ObservationState(double time, SimulationColony _simu) {
		super(time);
		this.simu = _simu;
		
	}
	
	/**
	 * Trigger Function 
	 * Triggers a simulation observation, adds to the simulation's total observations counter and prints the number of
	 * observations, moves, evaporations until then, and the best hamiltonian cycle found yet, if there is one
	 */
	protected void WriteObservation() {
		
		this.simu.obsCounter ++;
		System.out.println("Observation " + this.simu.obsCounter + ":\n");
		System.out.println("\t\t Number of move events: " + this.simu.moveCounter + "\n");
		System.out.println("\t\t Number of evaporation events: " + this.simu.evapCounter + "\n");
		System.out.println("\t\t Hamiltonian cycle: " + this.simu.toString() + "\n");
		System.out.println(this.simu.colony.graph.toString());
		this.simu.scheduleObservation();
	}


}
