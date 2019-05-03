package tsp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import graph.Graph;
import colony.SimulationColony;



/**
 * 
 * Project of Object Oriented Programming:<p>
 * Travelling Salesmen Problem by Ant Colony Optimization<p>
 * 
 * @author  Pedro Mendes (81046) - pedrogoncalomendes@tecnico.ulisboa.pt <p>
 * 			Rui Livramento (81051) - rui.livramento@tecnico.ulisboa.pt <p>
 * 			Francisco Costa (81673) - francisco.s.r.m.costa@tecnico.ulisboa.pt <p>
 *
 *  Group 9 <p> <p>
 *  
 *  Second Semester 2018/19 <p>
 *  MEEC - IST <p>
 *  
 */
public class Main {

	
	/**
	 *  * Main function: Reads the input file creates the graph and runs the simulation.
	 * 
	 * @param args Input Filename
	 * 	
	 */
	public static void main(String[] args) {
		
    	/*if(args.length != 1)
        {
            System.out.println("ERROR: Wrong Input\n");
            System.exit(1);
        }
		
		String filename = args[0];*/	
		String filename = "data1.xml";
				
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		Input inInfo = new Input();
		
		try {
	        saxParser = saxParserFactory.newSAXParser();
	        
	        saxParser.parse(new File(filename), inInfo);
	        
	    } catch (ParserConfigurationException | SAXException e) {
	        e.printStackTrace();
			System.out.println("ERROR: Parser function\n");
			System.exit(1);
			
	    } catch (IOException er) {
	    	er.printStackTrace();
	    	System.out.println("ERROR: Input file does not exist");
			System.exit(1);
			
	    }
		
		//create the graph
		Graph graph = new Graph(inInfo.nbnodes, inInfo.nestnode, inInfo.nodesArray, inInfo.W);
		
		if(verifySolution(graph)) {
	
			//create the simulation 
			SimulationColony simulation = new SimulationColony(inInfo.finalinst, inInfo.alpha, inInfo.beta, inInfo.delta, inInfo.eta, inInfo.rho, inInfo.plevel, inInfo.antcolsize, inInfo.nestnode, graph);
		
			//verifies the end time condition
			while(!simulation.verifyEnd()) {
				simulation.nextStep();	//performs the next event in PEC
							
			}
		}else {
			System.out.println("It's impossible to solve the problem");
		}
		System.out.println("END!!!");
		
	}
	
	
	
	
	/**
	 * The function verifies if the traveling salesman problem (TSP) has 
	 * solution for the given graph. 
	 * It verifies if there is at least a node that has only one neighbor.
	 * If there is, the TSP is impossible to solve and the simulation is 
	 * finish.
	 *
	 * @param graph The graph of the simulation.
	 * @return True if the TSP has a solution for the given graph or False
	 * otherwise.
	 * 
	 */
	private static boolean verifySolution(Graph graph) {
		
		
		for(int i=0; i<graph.nbnodes;i++) {
			if(graph.arrayNodes[i].listNeighbor.size() == 1) {
				return false;
			}
		}
		return true;
	}
	
}
