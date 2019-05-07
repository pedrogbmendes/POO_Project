package colony;

import java.util.LinkedList;
import java.util.Random;

import simulation.Events;
import simulation.Simulation;

/**
 * Class which manages the colony simulation
 * Specifies the use of the simulation defined in {@link simulation.Simulation}.
 * Õutputs the state of the simulation in predetermined time intervals.
 * Creates and schedules new events, manages them and keeps track of the state of the colony.
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 * 
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
	 * Creates a simulation of a colony
	 * @param simuTime Maximum time the simulation will run
	 * @param alpha Normalization parameter !!!
	 * @param beta Normalization parameter !!!!
	 * @param delta 
	 * @param eta 
	 * @param rho Rate at which each edge will evaporate its pheromones 
	 * @param plevel Initial level of pheromones on each edge
	 * @param colonySize The amount of ants that compose the colony
	 * @param nest Node which corresponds to the initial node of the colony
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
	}
	
	/**
	 * Event adder
	 * Adds new events to the PEC (Priority Event Container)
	 * @param e Event which will be added to the PEC
	 */
	void addToPec(Events e) {
		
		this.pec.add(e);
		
	}
		
	
	/**
	 * Observation event scheduler
	 * Adds an Observation event to the PEC
	 */
	void scheduleObservation() {
		this.addToPec(new ObservationState(this.getCurrentTime() + (this.simulationTime/20.0), this));
	}
	
	
	/**
	 * Move Planner
	 * Calculates and determines the next move for a specific ant. Also checks if a hamiltonian cycle was completed
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
	 * @param antID
	 * @param weightEdge
	 */
	private void scheduleMove(int antID, int weightEdge) {
		//this.addToPec(new MoveAnt(this.getCurrentTime()+1.0, antID, this.colony));
		this.addToPec(new MoveAnt(this.getCurrentTime()+expRandom(delta*weightEdge), antID, this.colony));
	
	}

	
	/**
	 * Hamiltonian Cycle verifier
	 * Verifies if a hamiltonian cycle was achieved
	 * @param antID Ant identifier to act upon
	 * @return true, if it verifies an hamiltonian cycle / false, otherwise
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
	 * Hamiltonian Cycle
	 * 
	 * @param antID
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
	 * Ant path reseter
	 * Resets the ant's path 
	 * @param antID Ant identifier to act upon
	 */
	private void resetAnt(int antID) {
		this.colony.AntColony[antID].path.clear();
		this.colony.AntColony[antID].weightPath = 0;
		
		this.colony.AntColony[antID].updateAnt(this.colony.graph.getNestNode(), 0);
		
	}
	
	
	/**
	 * Evaporation Event creator
	 * Updates the pheromone level of a specific edge and schedules the next evaporation
	 * @param edgeN1 1st node of the specific edge
	 * @param edgeN2 2nd node of the specific edge
	 */
	void Evaporation(int edgeN1, int edgeN2) {
		
		double actPhero = this.calculateEvaporation.decPheromone(colony.graph, edgeN1, edgeN2);
		if (actPhero!=0) {
			//schedule the decrement of pheromone if the edge has pheromones
			scheduleEvaporation(edgeN1, edgeN2);
		}
	}
	
	
	/**
	 * Evaporation event scheduler
	 * Adds a new evaporation event of a specific edge to the PEC
	 * @param edgeN1 1st node of the specific edge
	 * @param edgeN2 2nd node of the specific edge
	 */
	void scheduleEvaporation(int edgeN1, int edgeN2) {
		//this.addToPec(new EvaporationEdge(this.getCurrentTime()+1, edgeN1, edgeN2, this.colony));
		this.addToPec(new EvaporationEdge(this.getCurrentTime()+expRandom(eta), edgeN1, edgeN2, this.colony));
		
	}
	
	
	/**
	 * Exponential Distribution
	 * Determines a random probability using an exponential distribution
	 * @param m Mean deviation
	 * @return 
	 */
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
		
		//int size =  this.hamiltonCycle.toString().length(); 
		//return "{" + this.hamiltonCycle.toString().substring(1, size-1) + "}";
	}
	
	
	
	
	
}
