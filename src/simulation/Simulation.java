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
	 * Class constructor - Class initialization
	 * <p>
	 * initial time equals to 0
	 * final time of simulation is given in the input file
	 * 
	 * 
	 * @param simuTime Simulation Time
	 */
	public Simulation(double simuTime) {
		
		this.currentTime = 0.0;
		this.simulationTime = simuTime;
		
	}
	
	
	/**
	 * Verifies if the PEC is empty. 
	 * In affirmative case, it updates the current time with the event's time of 
	 * the first event in the PEC, and then performs the event. 
	 * 
	 */
	public void nextStep() {
		
		if(!pec.isEmpty()) {
	
			this.currentTime = pec.nextTimeEvent();
			pec.TimeToTrigger();
		}
	}
	
	
	/**
	 * Gives the current simulation time
	 * 
	 * @return Current time
	 */
	public double getCurrentTime() {
		return this.currentTime;
	}
	
	
	/**
	 * Verifies if the current simulation time is higher that the maximum simulation time
	 * 
	 * @return True in the end of simulation, false otherwise.
	 */
	public boolean verifyEnd() {
		
		if(this.currentTime > this.simulationTime) {
			return true;
		}
		return false;
	}
}