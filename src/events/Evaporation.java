package events;

public class Evaporation extends Events{

	public float eta, rho;
	
	public Evaporation(int timeinstant, float _eta, float _rho) {
		super(timeinstant);
		
		this.eta =_eta;
		this.rho = _rho;
		
	}
	
}
