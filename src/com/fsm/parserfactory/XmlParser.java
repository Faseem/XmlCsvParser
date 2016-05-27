package com.fsm.parserfactory;

import com.fsm.model.xml.XmlDocument;
import com.fsm.model.xml.Element;
import com.fsm.model.xml.Node;


public class XmlParser <T extends Element> implements Parser{
	
	private XmlDocument document;
	private Element rootElement;
	
	
	
	public XmlDocument getDocument() {
		return document;
	}

	public void setDocument(XmlDocument document) {
		this.document = document;
	}

	public Element getRootElement() {
		return rootElement;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}

	/*public XmlParser(String filePath) throws XmlParserException {
		// TODO Auto-generated constructor stub
		this.document=new Document(filePath);
		//rootElement=(Element) document.getElement();
		rootElement=document.getElement();
	}*/
	
	public XmlParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public T getFirstElement() {
		// TODO Auto-generated method stub
		return (T) rootElement.getChildNodes().get(0);
	}

	@Override
	public T getLastElement() {
		// TODO Auto-generated method stub
		return (T) rootElement.getChildNodes().get(rootElement.getChildNodes().size()-1);
	}

	@Override
	public T getElementByIndex(int index) {
		// TODO Auto-generated method stub
		int i= (rootElement.getChildNodes().size());
		if(index>=i || index<0){
			System.out.println("Total elements are : "+i+" And You are Requesting :"+index);
			return null;
		}else{
			return (T) rootElement.getChildNodes().get(index);
		}
	}


	public T getElementByName(String name) {
		// TODO Auto-generated method stub
		Node searched=null;
		 for(Node child: rootElement.getChildNodes()){
			 
			 if(child.getType()==2){
				 Element e=(Element) child;
				 if(e.getTagName().equals(name)){
					searched=child;;
					break;		
				}
			 }
		 }
		 return (T) searched; 
		 
		 }
}
