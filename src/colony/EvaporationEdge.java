package colony;

import events.Evaporation;

public class EvaporationEdge extends Evaporation{
	
	private Colony col;
	
	public EvaporationEdge(double timeinstant, int n1, int n2, Colony _col) {
		super(timeinstant, n1, n2);
		
		this.col = _col;
		
	}

	protected void EvaporationEvent() {
		this.col.simColony.evapCounter ++;
		this.col.simColony.Evaporation(this.edgeN1, this.edgeN2);
		
		
	}
	
}
