package events;

import java.util.ListIterator;
import java.util.stream.*;

import graph.*;

public class Move extends Events{

	private float alpha, beta, delta;
	
	public Move(int timeinstant, float _alpha, float _beta, float _delta) {
		super(timeinstant);
		
		this.alpha = _alpha;
		this.beta = _beta;
		this.delta = _delta;
		
	}
	
	
	
	
}
