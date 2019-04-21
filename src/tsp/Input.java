package tsp;



import org.xml.sax.*;
import org.xml.sax.helpers.*;

import events.*;
import graph.*;


public class Input extends DefaultHandler{

	int finalinst, antcolsize, plevel;
	int nbnodes, nestnode;
	Node[] nodesArray;
	int nodeID;
	Move move;
	Evaporation evap;
	
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
		float _alpha = 0, _beta = 0, _delta = 0, _eta = 0, _rho = 0;
		
		switch(tag) {
			
			case "simulation":
						
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("finalinst")) {
						this.finalinst = Integer.parseInt(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("antcolsize")) {
						this.antcolsize = Integer.parseInt(attributes.getValue(i));
								
					}else if(attributes.getLocalName(i).equals("plevel")) {
						this.plevel = Integer.parseInt(attributes.getValue(i));
								
					}
				}
				break;
			
			case "graph":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("nbnodes")) {
						this.nbnodes = Integer.parseInt(attributes.getValue(i));
						nodesArray = new Node[this.nbnodes];
						
					}else if(attributes.getLocalName(i).equals("nestnode")) {
						this.nestnode = Integer.parseInt(attributes.getValue(i));
					
					}
				}
				break;
				
			case "node":
				this.nodeID = Integer.parseInt(attributes.getValue(0));
				this.nodesArray[this.nodeID-1].nodeidx = this.nodeID;
								
				break;
				
			case "weight":
				int targetnodeID = Integer.parseInt(attributes.getValue(0));
				Weight Myneighbor = new Weight(targetnodeID, 0);
				Weight Yourneighbor = new Weight(this.nodeID, 0);
				
				this.nodesArray[this.nodeID-1].listNeighbor.add(Myneighbor);
				this.nodesArray[targetnodeID-1].listNeighbor.add(Yourneighbor);
				
				break;
				
			case "move":
				
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("alpha")) {
						_alpha = Integer.parseInt(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("beta")) {
						_beta = Integer.parseInt(attributes.getValue(i));
					
					}else if(attributes.getLocalName(i).equals("delta")) {
						_delta= Integer.parseInt(attributes.getValue(i));
					
					}
				}	
				this.move = new Move(0, _alpha, _beta, _delta);
				break;
		
			case "evaporation":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("eta")) {
						_eta = Integer.parseInt(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("rho")) {
						_rho = Integer.parseInt(attributes.getValue(i));
					
					}
				}
				this.evap = new Evaporation(0, _eta, _rho);
				break;
		}
	}
	
	
	@Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
    	
       
    }
	

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

        String weightSring = new String(ch,start,length);
        int weightEdge = Integer.parseInt(weightSring);
        int neighborID = this.nodesArray[this.nodeID-1].listNeighbor.getLast().getID();
        
        this.nodesArray[this.nodeID-1].listNeighbor.getLast().setWeight(weightEdge);
        this.nodesArray[neighborID-1].listNeighbor.getLast().setWeight(weightEdge);
        
	   }



}
