package events;

public class Move extends Events{

	public float alpha, beta, delta;
	
	public Move(int timeinstant, float _alpha, float _beta, float _delta) {
		super(timeinstant);
		
		this.alpha = _alpha;
		this.beta = _beta;
		this.delta = _delta;
		
	}
	
}
