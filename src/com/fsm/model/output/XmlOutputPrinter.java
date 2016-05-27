package com.fsm.model.output;


import java.util.Iterator;
import com.fsm.model.xml.Data;
import com.fsm.model.xml.Element;
import com.fsm.model.xml.Node;

public class XmlOutputPrinter implements Printer<Element>{

	@Override
	public void printElement(Element  xmlElement) {
		// TODO Auto-generated method stub
		if(xmlElement!=null){	
			printInLoop(xmlElement,0);
		}else{
			System.out.println("No such element Found");
		}	
	}

	private void printInLoop(Node element,int i){
		
		if(!(element==null)){
			
			if(element.getType()==2){
				Element elmnt=(Element) element;
				System.out.printf("Name of the %d  Super Node : %s ",i,elmnt.getTagName());
				System.out.println();
				int atb=elmnt.getAtributes().size();
				if(atb>0){
					System.out.println();
					System.out.println("---------Attributes---------");
					
					Iterator iterator = elmnt.getAtributes().keySet().iterator();
					int k=1;
					while (iterator.hasNext()) {
					   String key = iterator.next().toString();
					   String value = elmnt.getAtributes().get(key);
					   System.out.println(k+")"+" Name : "+ key + " Value : "+ value);
					   k++;
					}
					//System.out.println();
				}
				i++;
				for(Node child : element.getChildNodes()){
					
					printInLoop(child,i);
					

				}
			}else {
				Data data=(Data) element;
				--i;
				//System.out.println("Data of the "+i+" Node : "+elmnt.getData());
				System.out.printf("Data of the %d  Super Node : %s ",i,data.getData());
				System.out.println();
			}
			
		}else{
			
		}
	
	}
	
	
	//////////////////////////////////
	
/*	
	private void printAtributes(HashMap<String, String> atributes) {
		// TODO Auto-generated method stub
		for (String name: atributes.keySet()){

            String key =name.toString();
            String value = atributes.get(name).toString();  
            System.out.println(key + " " + value);  


		} 
	
	}*/
	
	
	
	/*
	void printElem(Element xmlElement){
		
		System.out.println("ELEMENT NAME :" + xmlElement.getTagName());
		
		if(xmlElement.getAtributes()!=null){
			HashMap<String, String> atributes=xmlElement.getAtributes();
			printAtributes(atributes);
		}
		Node node=xmlElement;
		if(node.getType()==2){
			List<Node> childelements =xmlElement.getChildNodes();
			for(Node node1 : childelements)	{
				printElement((Element) node1) ;
			}		
		}else if(node.getType()==3){
			Data data = (Data) node;
			System.out.println("   ELEMENT DATA :"+ data.getData());
		}
	}*/
	
	
	//////////////////////////////////////////
	/*System.out.println("  ELEMENT NAME :" + xmlElement.getTagName());
	
	if(xmlElement.getAtributes()!=null){
		HashMap<String, String> atributes=xmlElement.getAtributes();
		printAtributes(atributes);
	}
	
	List<Node> childelements =xmlElement.getChildNodes();
	Element childElement;
	for (Node node : childelements) {
		childElement = (ElementImpl) node;
		System.out.println(" CHILD ELEMENT :" + childElement.getTagName());
		
		for (Node node1 : childElement.getChildNodes()) {
			if (node1.getType() == 2) {
				Element childElement1 = (ElementImpl) node1;
				System.out.println("---SUPER CHILD ELEMENT :"+ childElement1.getTagName());
				
				if(childElement1.getChildNodes()!=null){
					for(Node superChilds : childElement1.getChildNodes())
					{
					
						if(superChilds.getType() == 2){
							Element superChildElement1 = (ElementImpl) superChilds;
							System.out.println("---SUPRIM CHILD ELEMENT :"+ superChildElement1.getTagName());
							for(Node superimChilds : superChildElement1.getChildNodes())
							{
							
								if(superimChilds.getType() == 2){
									Element suprimChildElement1 = (ElementImpl) superimChilds;
									System.out.println("---SUPRIM SUPRE CHILD ELEMENT :"+ suprimChildElement1.getTagName());
									
									for(Node superimSuprimChilds : superChildElement1.getChildNodes())
									{
									
										if(superimSuprimChilds.getType() == 2){
											Element suprimChildElement2 = (ElementImpl) superimSuprimChilds;
											System.out.println("---SUPRIM SUPRIM CHILD ELEMENT :"+ suprimChildElement2.getTagName());
											
										} else if (superimSuprimChilds.getType() == 3) {
											Data data = (Data) superimSuprimChilds;
											System.out.println("---SUPRIM SUPER CHILD ELEMENT DATA :"+ data.getData());
										}
									}	
								} else if (superimChilds.getType() == 3) {
									Data data = (Data) superimChilds;
									System.out.println("---SUPRIM SUPER CHILD ELEMENT DATA :"+ data.getData());
								}
							}
						} else if (superChilds.getType() == 3) {
							Data data = (Data) superChilds;
							System.out.println("---SUPRIM CHILD ELEMENT DATA :"+ data.getData());
						}
					}
				}
				
			} else if (node1.getType() == 3) {
				Data data = (Data) node1;
				System.out.println("---SUPER CHILD ELEMENT DATA :"+ data.getData());
			}

		}
	}
	
}else{
	
	System.out.println("No Elements return by the parser to print");
	*/
	//////////////////////////////////////////////
	
	

}
