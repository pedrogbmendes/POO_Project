package tsp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import graph.Graph;
import colony.SimulationColony;



public class Main {

	
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
		
		Graph graph = new Graph(inInfo.nbnodes, inInfo.nestnode, inInfo.nodesArray, inInfo.W);
		System.out.println(graph.toString());
	
	
		SimulationColony simulation = new SimulationColony(inInfo.finalinst, inInfo.alpha, inInfo.beta, inInfo.delta, inInfo.eta, inInfo.rho, inInfo.plevel, inInfo.antcolsize, inInfo.nestnode, graph);
		
		while(!simulation.verifyEnd()) {
			
		}
	
		
		
		
		
		
	
	}
	
}
