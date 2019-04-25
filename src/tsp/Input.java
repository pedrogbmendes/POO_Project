package tsp;


import org.xml.sax.*;
import org.xml.sax.helpers.*;

import colony.UpdateMoveAnt;
import colony.UpdateEvaporation;
import graph.Node;
import graph.Weight;


public class Input extends DefaultHandler{

	double finalinst,plevel;
	int antcolsize;
	int nbnodes, nestnode;
	Node[] nodesArray;
	private int nodeID, targetnodeID;
	double alpha, beta, delta, eta, rho;
	int W = 0;
	
	@Override
    public void startDocument() throws SAXException
    {
        //System.out.println("start of the document");
    }
 
	@Override
    public void endDocument() throws SAXException
    {
        //System.out.println("end of the document document");
    }
	
	
	@Override
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
				
		int attributesSize = attributes.getLength();
		int i;
		
		switch(tag) {
			
			case "simulation":
						
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("finalinst")) {
						this.finalinst = Double.parseDouble(attributes.getValue(i));
		
					}else if(attributes.getLocalName(i).equals("antcolsize")) {
						this.antcolsize = Integer.parseInt(attributes.getValue(i));
								
					}else if(attributes.getLocalName(i).equals("plevel")) {
						this.plevel = Double.parseDouble(attributes.getValue(i));
						
					}
				}
				break;
			
			case "graph":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("nbnodes")) {
						this.nbnodes = Integer.parseInt(attributes.getValue(i));
						nodesArray = new Node[this.nbnodes];
						for(int j=0; j<this.nbnodes; j++) {
							Node new_node = new Node();
							nodesArray[j] = new_node;
						}
						
					}else if(attributes.getLocalName(i).equals("nestnode")) {
						this.nestnode = Integer.parseInt(attributes.getValue(i));
					
					} 
				};
				break;
				
			case "node":
				this.nodeID = Integer.parseInt(attributes.getValue(0));			
				break;
				
			case "weight":
				this.targetnodeID = Integer.parseInt(attributes.getValue(0));
				Weight Myneighbor = new Weight(this.targetnodeID, 0);
				Weight Yourneighbor = new Weight(this.nodeID, 0);
				
				
				
				this.nodesArray[this.nodeID-1].listNeighbor.add(Myneighbor);
				this.nodesArray[this.targetnodeID-1].listNeighbor.add(Yourneighbor);

				break;
				
			case "move":
				
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("alpha")) {
						this.alpha = Double.parseDouble(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("beta")) {
						this.beta = Double.parseDouble(attributes.getValue(i));
					
					}else if(attributes.getLocalName(i).equals("delta")) {
						this.delta= Double.parseDouble(attributes.getValue(i));
					
					}
				}	
				break;
		
			case "evaporation":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("eta")) {
						this.eta = Double.parseDouble(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("rho")) {
						this.rho = Double.parseDouble(attributes.getValue(i));
					
					}
				}
				break;
		}
	}
	
	
	@Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
    	
       
    }
	

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

        String weightSring = new String(ch,start,length);
        int weightEdge = Integer.parseInt(weightSring);
        
        this.nodesArray[this.nodeID-1].listNeighbor.getLast().setWeight(weightEdge);
        this.nodesArray[this.targetnodeID-1].listNeighbor.getLast().setWeight(weightEdge);
        this.W += weightEdge;
        
	   }



}
