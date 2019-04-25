package colony;

import java.util.LinkedList;

import graph.Weight;

public interface GraphInterface {

	void setPheromone(int myID, int neighborID, double phero);
	double getPheromone(int myID, int neighborID);
	void decrementPheromone(int myId, int neighborId, double dec);
	int getWeight(int myID, int neighborID);
	int numberNeighbor(int id);
	LinkedList<Weight> getListNeighbor(int id);
	int getNeighborID(int id, int index);
	int getTotalWeight();
	String toString();
	
}
