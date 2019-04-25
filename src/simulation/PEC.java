package simulation;

import java.util.PriorityQueue;


public class PEC {

	protected PriorityQueue<Events> QueueEvents;

	
	public PEC() {
		
		this.QueueEvents = new PriorityQueue<Events>();
	}
	
	
    public boolean isEmpty() {
        
        return this.QueueEvents.isEmpty();
    }
	
    
    public double nextTimeEvent() {
    	
    	return this.QueueEvents.peek().getTimeEvent();
    }
    
    
    public void add(Events event) {
    	QueueEvents.add(event);
    }
    
    public void TimeToTrigger(PriorityQueue<Events> pec) {
    	
    	pec.poll().TriggerEvent();
   
    }
    
}
