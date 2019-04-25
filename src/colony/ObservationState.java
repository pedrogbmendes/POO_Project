package colony;


import events.Observation;

public class ObservationState extends Observation{

	SimulationColony simu;
	
	public ObservationState(double time, SimulationColony _simu) {
		super(time);
		this.simu = _simu;
		
	}
	
	protected void WriteObservation() {
		
		this.simu.obsCounter ++;
		System.out.println("Observation " + this.simu.obsCounter + ":\n");
		System.out.println("\t\t Number of move events: " + this.simu.moveCounter + "\n");
		System.out.println("\t\t Number of evaporation events: " + this.simu.evapCounter + "\n");
		//System.out.println("\t\t Hamiltonian cycle: " + moveNumber + "\n");
		this.simu.scheduleObservation();
	}


}
