package tsp;

import java.util.LinkedList;

import graph.Weight;

public interface GraphInterface {

	void setPheromone(int myID, int neighborID, float phero);
	float getPheromone(int myID, int neighborID);
	int getWeight(int myID, int neighborID);
	int numberNeighbor(int id);
	LinkedList<Weight> getListNeighbor(int id);
	int getNeighborID(int id, int index);
	int getTotalWeight();
	
}
