package tsp;



import org.xml.sax.*;
import org.xml.sax.helpers.*;

import events.*;
import graph.*;


public class Input extends DefaultHandler{

	float finalinst,plevel;
	int antcolsize;
	int nbnodes, nestnode;
	Node[] nodesArray;
	private int nodeID, targetnodeID;
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
						this.finalinst = Float.parseFloat(attributes.getValue(i));
		
					}else if(attributes.getLocalName(i).equals("antcolsize")) {
						this.antcolsize = Integer.parseInt(attributes.getValue(i));
								
					}else if(attributes.getLocalName(i).equals("plevel")) {
						this.plevel = Float.parseFloat(attributes.getValue(i));
						
					}
				}
				break;
			
			case "graph":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("nbnodes")) {
						this.nbnodes = Integer.parseInt(attributes.getValue(i));
						nodesArray = new Node[this.nbnodes];
						for(int j=0; j<this.nbnodes; j++) {
							Node new_node = new Node(j+1);
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
						_alpha = Float.parseFloat(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("beta")) {
						_beta = Float.parseFloat(attributes.getValue(i));
					
					}else if(attributes.getLocalName(i).equals("delta")) {
						_delta= Float.parseFloat(attributes.getValue(i));
					
					}
				}	
				this.move = new Move(0, _alpha, _beta, _delta);
				break;
		
			case "evaporation":
				for(i = 0; i < attributesSize; i++) {
					if(attributes.getLocalName(i).equals("eta")) {
						_eta = Float.parseFloat(attributes.getValue(i));
						
					}else if(attributes.getLocalName(i).equals("rho")) {
						_rho = Float.parseFloat(attributes.getValue(i));
					
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
	public void characters(char[] ch, int start, int length) throws SAXException {

        String weightSring = new String(ch,start,length);
        int weightEdge = Integer.parseInt(weightSring);
        
        this.nodesArray[this.nodeID-1].listNeighbor.getLast().setWeight(weightEdge);
        this.nodesArray[this.targetnodeID-1].listNeighbor.getLast().setWeight(weightEdge);
        
	   }



}
