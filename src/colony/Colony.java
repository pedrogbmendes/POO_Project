package colony;


/**
 * Class which defines the colony. <p>
 * It stores the number of ants in the colony, the map used for the simulation 
 * and the object to the colony's simulation.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public class Colony {

	Ant[] AntColony;
	GraphInterface graph;
	SimulationColony simColony;

	/**
	 * Colony's Constructor. It saves the graph and the object for the simulation
	 * and creates all the ants and save all in a vector andcreates and schedule 
	 * the first move event for all the ants.
	 * 
	 * @param SimCol Object for the simulations
	 * @param colonySize Size of the colony, i.e., number of existing ants
	 * @param nest Identifier of the nest node
	 * @param _graph Map for the simulation
	 */
	public Colony(SimulationColony SimCol, int colonySize, int nest, GraphInterface _graph) {
		this.simColony = SimCol;	//object for the simulation
		this.graph = _graph;		//map -> graph
		
		//create colony
		 this.AntColony = new Ant[colonySize];
		for(int j=0; j< colonySize; j++) {
			this.AntColony[j] = new Ant(nest);
		}
		
	}
	 
}
