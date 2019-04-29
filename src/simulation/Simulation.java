package simulation;

/**
 * 
 * Abstract class where is define a Stochastic Simulation based on the 
 * event occurence.
 * The simulation has the current time and it's running during the simulation
 * time (while there is events to run stored in the PEC).<p>
 * 
 * Gives the basic functioning to extend the simulation to a specific type.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public abstract class Simulation {

	protected double currentTime;
	protected double simulationTime;
	protected PEC pec = new PEC();
	
	
	/**
	 * Class constructor - 
	 * 
	 * 
	 * @param simuTime Simulation Time
	 */
	public Simulation(double simuTime) {
		
		this.currentTime = 0.0;
		this.simulationTime = simuTime;
		
	}
	
	
	public void nextStep() {
		
		if(!pec.isEmpty()) {
	
			this.currentTime = pec.nextTimeEvent();
			pec.TimeToTrigger(pec.QueueEvents);
		}
	}
	
	public double getCurrentTime() {
		return this.currentTime;
	}
	
	
	public boolean verifyEnd() {
		
		if(this.currentTime > this.simulationTime) {
			return true;
		}
		return false;
	}
}