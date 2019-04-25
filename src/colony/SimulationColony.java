package colony;

import java.util.Random;

import events.*;
import simulation.Events;
import simulation.Simulation;

public class SimulationColony extends Simulation{

	UpdateMoveAnt calculateMove;
	UpdateEvaporation calculateEvaporation;
	Ant[] colony;
	static Random random = new Random();
	double delta, eta;
	GraphInterface graph;
	
	public SimulationColony(double simuTime, double alpha, double beta, double delta, double eta, double rho, double plevel, int colonySize, int nest, GraphInterface _graph) {
		super(simuTime);
		
		this.graph = _graph;
		
		this.calculateMove = new UpdateMoveAnt(alpha, beta);
		this.calculateEvaporation = new UpdateEvaporation(plevel, rho);
		this.delta = delta;
		
		//create colony
		 this.colony = new Ant[colonySize];
			for(int j=0; j< colonySize; j++) {
				colony[j] = new Ant(nest);
				
				this.addToPec(new Move(expRandom(delta), j));
			} 
		
	}
	
	private void addToPec(Events e) {
		
		this.pec.add(e);
		
	}
	
	
	public void Move(int antID) {
		
		int weightEdge = this.calculateMove.AntMove(this.colony[antID], this.graph);
		scheduleMove(antID, weightEdge);
	}
	
	
	private void scheduleMove(int antID, int weightEdge) {
	
		this.addToPec(new Move(this.getCurrentTime()+expRandom(delta*weightEdge), antID));
	
	}

	void evaporation(int edgeN1, int edgeN2) {
		
		this.calculateEvaporation.decPheromone(graph, edgeN1, edgeN2);
		scheduleEvaporation(edgeN1, edgeN2);
			
	}
	
	private void scheduleEvaporation(int edgeN1, int edgeN2) {
		
		this.addToPec(new Evaporation(this.getCurrentTime()+expRandom(eta), edgeN1, edgeN2));
		
	}
	
	
	
	

	private static double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}
	
	
}
