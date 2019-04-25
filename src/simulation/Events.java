package simulation;

public abstract class Events implements Comparable<Events> {

		double eventTime;
		
		public Events(double timeinstant){
			this.eventTime = timeinstant;		
		}
		
		
		public double getTimeEvent() {
			return this.eventTime;
		}

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
