package simulation;

import java.util.PriorityQueue;

/**
 * Class that implements a Pending Event Container (PEC), where the 
 * Events are added and saved, in the correct order in a Priority
 * Queue, and triggered.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public class PEC {

	/**
	 * Priority Queue where the events are saved by temporal order.  
	 */
	protected PriorityQueue<Events> QueueEvents;

	
	/**
	 * 	 * Class constructor - Priority Queue initialization
	 * 
	 */
	public PEC() {
		
		this.QueueEvents = new PriorityQueue<Events>();
	}
	
	
    /**
     * Verifies if the Priority Queue is empty
     * 
     * @return True if the Priority Queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        
        return this.QueueEvents.isEmpty();
    }
	
    
    /**
     * Gives the time of the next event to be performed
     * 
     * @return Time of the next event (i.e., the time of the first event in the
     * Priority Queue).
     */
    public double nextTimeEvent() {
    	
    	return this.QueueEvents.peek().getTimeEvent();
    }
    
    
    /**
     * Adds a new event to the Queue 
     * 
     * @param event Event to be added
     */
    public void add(Events event) {
    	QueueEvents.add(event);
    }
    
    
    /**
     * Retrieves and remove the first event in the Priority Queue and triggers that event
     *
     */
    public void TimeToTrigger() {
    	
    	this.QueueEvents.poll().TriggerEvent();
   
    }
    
}
