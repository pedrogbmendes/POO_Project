package colony;


public class Colony {

	Ant[] AntColony;
	GraphInterface graph;
	SimulationColony simColony;

	
	public Colony(SimulationColony SimCol, int colonySize, int nest, GraphInterface _graph) {
		this.simColony = SimCol;
		this.graph = _graph;
		
		//create colony
		 this.AntColony = new Ant[colonySize];
			for(int j=0; j< colonySize; j++) {
				this.AntColony[j] = new Ant(nest);
				
				this.simColony.addToPec(new MoveAnt(SimCol.expRandom(SimCol.delta), j, this));
			} 
		
	}
	 
}
