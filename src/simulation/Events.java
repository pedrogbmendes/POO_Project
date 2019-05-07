package simulation;



/**
 * Abstract Class where an event of the simulation is defined (to be added in the PEC)
 * All the events have the event time attribute in common, and so when the object 
 * of this class is created, the event time is schedule (for the given time).
 * 
 * It class must be extended to define a specific event (for example, move, evaporation, 
 * observation, reproduction, etc.), with the associated effect produce by a specific event.
 * 
 * @author Pedro Mendes, Rui Livramento, Francisco Costa
 *
 */
public abstract class Events implements Comparable<Events> {

		double eventTime;
		
		
		/**
		 * Class constructor - Class initialization<p>
		 * Initialization of the object with the time of the event to 
		 * be triggered.
		 * 
		 * @param timeinstant Event time
		 */
		public Events(double timeinstant){
			this.eventTime = timeinstant;		
		}
		
		
		/**
		 * @return Event time of an event to be triggered
		 */
		public double getTimeEvent() {
			return this.eventTime;
		}

		
	    /**
	     *
	     * Events are saved in the priority queue by the correct order, i.e., events 
	     * are sorted by default in ascending order of eventTime.
	     * 
	     * @param event New event to compare and save
	     */
	    @Override
	    public int compareTo(Events event) {
	         
	    	//a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.

	        // Order by ascending time
	        if (this.eventTime < event.eventTime) {
	            return -1;
	            
	        } else if (this.eventTime > event.eventTime) {
	            return 1;
	            
	        }
	        return 0;
	    }
	    
	    public abstract void TriggerEvent();

}
