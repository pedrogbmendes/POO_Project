package colony;

import java.util.Random;

import simulation.Events;
import simulation.Simulation;

public class SimulationColony extends Simulation {

	UpdateMoveAnt calculateMove;
	UpdateEvaporation calculateEvaporation;
	Colony colony;
	static Random random = new Random();
	double delta, eta;
	int obsCounter=0, moveCounter, evapCounter;
	
	
	public SimulationColony(double simuTime, double alpha, double beta, double delta, double eta, double rho, double plevel, int colonySize, int nest, GraphInterface _graph) {
		super(simuTime);
				
		this.calculateMove = new UpdateMoveAnt(alpha, beta);
		this.calculateEvaporation = new UpdateEvaporation(plevel, rho);
		this.delta = delta;
		colony = new Colony(this, colonySize, nest, _graph);
		
		scheduleObservation();
	}
	
	void addToPec(Events e) {
		
		this.pec.add(e);
		
	}
		
	
	void scheduleObservation() {
		this.addToPec(new ObservationState(this.getCurrentTime() + (this.simulationTime/20.0), this));
	}
	
	void Move(int antID) {
		
		int weightEdge = this.calculateMove.AntMove(colony.AntColony[antID], colony.graph);
		scheduleMove(antID, weightEdge);
	}
	
	
	private void scheduleMove(int antID, int weightEdge) {
	
		this.addToPec(new MoveAnt(this.getCurrentTime()+expRandom(delta*weightEdge), antID, this.colony));
	
	}

	
	void Evaporation(int edgeN1, int edgeN2) {
		
		this.calculateEvaporation.decPheromone(colony.graph, edgeN1, edgeN2);
		scheduleEvaporation(edgeN1, edgeN2);
			
	}
	
	
	private void scheduleEvaporation(int edgeN1, int edgeN2) {
		
		this.addToPec(new EvaporationEdge(this.getCurrentTime()+expRandom(eta), edgeN1, edgeN2, this.colony));
		
	}
	
	
	double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}
	
	
}
