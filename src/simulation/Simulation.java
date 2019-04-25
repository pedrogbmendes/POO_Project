package simulation;

public abstract class Simulation {

	protected double currentTime;
	protected double simulationTime;
	protected PEC pec = new PEC();
	
	
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
		
		if(this.currentTime >= this.simulationTime) {
			return true;
		}
		return false;
	}
}