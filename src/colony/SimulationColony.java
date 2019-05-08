package colony;

import java.util.LinkedList;
import java.util.Random;

import simulation.Events;
import simulation.Simulation;

/**
 * Class which manages the colony simulation. <p>
 * Specifies the use of the simulation defined in {@link simulation.Simulation}.
 * Creates and schedules new events, manages them and keeps track of the state of the colony.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 */
public class SimulationColony extends Simulation {

	UpdateMoveAnt calculateMove;
	UpdateEvaporation calculateEvaporation;
	Colony colony;
	static Random random = new Random();
	double delta, eta;
	int obsCounter, moveCounter, evapCounter;
	LinkedList<Integer> hamiltonCycle = new LinkedList<Integer>();
	int hamiltonWeight;
	
	
	/**
	 * Creates a simulation of a colony.
	 * 
	 * @param simuTime Maximum time the simulation will run
	 * @param alpha Parameter concerning the ant move event w.r.t the pheromone level of an edge
	 * @param beta Parameter concerning the ant move event w.r.t the weight of an edge
	 * @param delta Parameter concerning the ant move event w.r.t the time to traverse an edge
	 * @param eta Parameter concerning the pheromone evaporation event w.r.t the time between evaporations 
	 * @param rho Parameter concerning the pheromone evaporation event w.r.t the rate at which each 
	 * edge will evaporate the pheromones 
	 * @param plevel Parameter concerning pheromone level when the Hamiltonian cycle is complete
	 * @param colonySize Number of ants that compose the colony
	 * @param nest Identifier of the nest node of the colony
	 * @param _graph Map where ants can traverse through
	 */
	public SimulationColony(double simuTime, double alpha, double beta, double delta, double eta, double rho, double plevel, int colonySize, int nest, GraphInterface _graph) {
		super(simuTime);
				
		this.calculateMove = new UpdateMoveAnt(alpha, beta);
		this.calculateEvaporation = new UpdateEvaporation(plevel, rho);
		this.delta = delta;
		this.eta = eta;
		colony = new Colony(this, colonySize, nest, _graph);
		
		scheduleObservation();
		
		for(int j=0; j< colonySize; j++) {
			//creates the first event for the ants
			this.Move(j);
				
		} 
	}
	
	/**
	 * Adds new events to the PEC (Priority Event Container).
	 * 
	 * @param e Event which will be added to the PEC
	 */
	void addToPec(Events e) {
		
		if(e.getTimeEvent() <= this.simulationTime)
			this.pec.add(e);
	}
		
	
	/**
	 * Observation event scheduler. Adds an Observation event to the PEC.
	 */
	void scheduleObservation() {
		this.addToPec(new ObservationState(this.getCurrentTime() + (this.simulationTime/20.0), this));
	}
	
	
	/**
	 * Calculates the next move for a specific ant. 
	 * Also checks if a Hamiltonian cycle was completed and schedule the new move event
	 * 
	 * @param antID Ant identifier to act upon
	 */
	void Move(int antID) {
		
		int weightEdge = this.calculateMove.AntMove(colony.AntColony[antID], colony.graph);
		
		if(verifyHamiltonCycle(antID)) {
			hamiltonionCycle(antID);
		}
		
		scheduleMove(antID, weightEdge);
	}
	
	
	/**
	 * Move event scheduler
	 * 
	 * @param antID Ant's identifier
	 * @param weightEdge Weight of the edge that the ant will traverse
	 */
	private void scheduleMove(int antID, int weightEdge) {
		this.addToPec(new MoveAnt(this.getCurrentTime()+expRandom(delta*weightEdge), antID, this.colony));
	
	}

	
	/**
	 * Verifies if a Hamiltonian cycle was achieved.
	 * 
	 * @param antID Ant identifier to act upon
	 * @return True, if it verifies an hamiltonian cycle; false, otherwise.
	 */
	private boolean verifyHamiltonCycle(int antID) {
		
		Ant ant = this.colony.AntColony[antID];
		int lastNode = ant.path.getLast();
		
		if(lastNode == this.colony.graph.getNestNode() && ant.path.size() == this.colony.graph.getNumberNodes()+1) {
			//cycle completed
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Increments the level of pheromones on the path of the ant that completed the
	 * Hamiltonian cycle. Verifies if there already exist a Hamiltonian cycle.
	 * 
	 * If false, the Hamiltonian cycle is saved and the ant is reseted.
	 * 
	 * If true, verifies if the new cycle of Hamilton has a smaller weight 
	 * that the previous one. In affirmative case, the Hamiltonian cycle is 
	 * saved and the ant is reseted. In negative case, only resets the ant.
	 * 
	 * @param antID Ant's identifier
	 */
	private void hamiltonionCycle(int antID) {

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
	
	
	/**
	 * Resets the ant's path 
	 * 
	 * @param antID Ant identifier to act upon
	 */
	private void resetAnt(int antID) {
		this.colony.AntColony[antID].path.clear();
		this.colony.AntColony[antID].weightPath = 0;
		
		this.colony.AntColony[antID].updateAnt(this.colony.graph.getNestNode(), 0);	
	}
	
	
	/**
	 * Updates the pheromone level of a specific edge and schedules the next evaporation
	 * 
	 * @param edgeN1 Node to identify the edge
	 * @param edgeN2 Node to identify the edge
	 */
	void Evaporation(int edgeN1, int edgeN2) {
		
		double actPhero = this.calculateEvaporation.decPheromone(colony.graph, edgeN1, edgeN2);
		if (actPhero!=0) {
			//schedule the decrement of pheromone if the edge has pheromones
			scheduleEvaporation(edgeN1, edgeN2);
		}
	}
	
	
	/**
	 * Adds a new evaporation event of a specific edge to the PEC
	 * 
	 * @param edgeN1 Node to identify the edge
	 * @param edgeN2 Node to identify the edge
	 */
	void scheduleEvaporation(int edgeN1, int edgeN2) {
		this.addToPec(new EvaporationEdge(this.getCurrentTime()+expRandom(eta), edgeN1, edgeN2, this.colony));

	}
	
	
	/**
	 * Exponential Distribution function that calculates a random probability using an exponential distribution
	 * 
	 * @param m Mean deviation
	 * @return Random value of a exponential distribution 
	 */
	double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}

	
	/**
	 * toString function that returns a string with the hamiltonian cycle to be
	 * printed in the observation
	 */
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
