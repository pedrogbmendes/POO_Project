package colony;

import java.util.LinkedList;
import java.util.Random;

import simulation.Events;
import simulation.Simulation;

public class SimulationColony extends Simulation {

	UpdateMoveAnt calculateMove;
	UpdateEvaporation calculateEvaporation;
	Colony colony;
	static Random random = new Random();
	double delta, eta;
	int obsCounter, moveCounter, evapCounter;
	LinkedList<Integer> hamiltonCycle = new LinkedList<Integer>();
	int hamiltonWeight;
	
	
	public SimulationColony(double simuTime, double alpha, double beta, double delta, double eta, double rho, double plevel, int colonySize, int nest, GraphInterface _graph) {
		super(simuTime);
				
		this.calculateMove = new UpdateMoveAnt(alpha, beta);
		this.calculateEvaporation = new UpdateEvaporation(plevel, rho);
		this.delta = delta;
		this.eta = eta;
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
		System.out.println(verifyHamiltonCycle(antID));
		if(verifyHamiltonCycle(antID)) {
			hamiltonionCycle(antID);
		}
		
		scheduleMove(antID, weightEdge);
	}
	
	
	private void scheduleMove(int antID, int weightEdge) {
		this.addToPec(new MoveAnt(this.getCurrentTime()+1.0, antID, this.colony));
		//this.addToPec(new MoveAnt(this.getCurrentTime()+expRandom(delta*weightEdge), antID, this.colony));
	
	}

	
	private boolean verifyHamiltonCycle(int antID) {
		
		Ant ant = this.colony.AntColony[antID];
		int lastNode = ant.path.getLast();
		
		if(lastNode == this.colony.graph.getNestNode() && ant.path.size() == this.colony.graph.getNumberNodes()+1) {
			//cycle completed
			return true;
		}
		
		return false;
	}
	
	
	private void hamiltonionCycle(int antID) {
		System.out.println(".jkksg,");
		this.calculateEvaporation.incPheromone(this.colony, antID);
		
		if (!this.hamiltonCycle.isEmpty()) {
			//not the frist hamiltonian cycle
			
			if(this.colony.AntColony[antID].weightPath < this.hamiltonWeight) {
				//new better hamiltonian path
				
				this.hamiltonCycle.clear();
				this.colony.AntColony[antID].path.removeLast();
				this.hamiltonCycle = new LinkedList<>(this.colony.AntColony[antID].path);
				this.hamiltonWeight = this.colony.AntColony[antID].weightPath;
			
			}
		}else {
			//first cycle
			this.hamiltonCycle.clear();
			this.colony.AntColony[antID].path.removeLast();
			this.hamiltonCycle = new LinkedList<>(this.colony.AntColony[antID].path);
			this.hamiltonWeight = this.colony.AntColony[antID].weightPath;
		}
		

			
		resetAnt(antID);
		
	}
	
	
	private void resetAnt(int antID) {
		this.colony.AntColony[antID].path.clear();
		this.colony.AntColony[antID].weightPath = 0;
		
		this.colony.AntColony[antID].updateAnt(this.colony.graph.getNestNode(), 0);
		
	}
	
	
	void Evaporation(int edgeN1, int edgeN2) {
		
		double actPhero = this.calculateEvaporation.decPheromone(colony.graph, edgeN1, edgeN2);
		if (actPhero!=0) {
			//schedule the decrement of pheromone if the edge has pheromones
			scheduleEvaporation(edgeN1, edgeN2);
		}
	}
	
	
	void scheduleEvaporation(int edgeN1, int edgeN2) {
		this.addToPec(new EvaporationEdge(this.getCurrentTime()+1, edgeN1, edgeN2, this.colony));
		//this.addToPec(new EvaporationEdge(this.getCurrentTime()+expRandom(eta), edgeN1, edgeN2, this.colony));
		
	}
	
	
	double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}

	@Override
	public String toString() {
		if(!this.hamiltonCycle.isEmpty()){
			String listInString = "{";
			
			
			for (int n : this.hamiltonCycle) {
				listInString += n + ",";
			}
			int size = listInString.length();
			listInString = listInString.substring(0, size-1);
			return  listInString + "}";
		}
		return "";
	}
	
	
	
	
	
}
