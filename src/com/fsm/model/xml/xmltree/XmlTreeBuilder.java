package com.fsm.model.xml.xmltree;

import java.util.HashMap;
import java.util.Stack;

import com.fsm.exception.XmlCsvParserException;
import com.fsm.model.xml.Node;
import com.fsm.model.xml.XmlData;
import com.fsm.model.xml.Data;
import com.fsm.model.xml.XmlElement;
import com.fsm.model.xml.Element;



public class XmlTreeBuilder implements TreeBuilder{
	
	public XmlTreeBuilder(){
		this.nodes=new Stack<Element>();
	}
	
	private Element rootElement;
	private Stack<Element> nodes;
	
	@Override
	public Element getRootElement() {
		return rootElement;
	}

	
	@Override
	public void generateTreeStructure(String tagName, int tokenType,
			HashMap<String, String> atributes) {
		
		// TODO Auto-generated method stub
		XmlElement element=null;
		Data data=null;
		
		if(tokenType==1){
			element=new XmlElement();
			element.setTagName(tagName);
			if(atributes.size()!=0){
				element.setAtributes(atributes);
				
			}
			if(rootElement==null){
				rootElement=element;
			}else{
				if(!nodes.peek().isHasEndTag()){
					nodes.peek().addChildNode(element);
				}
			}
			nodes.push(element);	
		}else if(tokenType==2){
			Element e=nodes.peek();
			if(e.getTagName().equals(tagName)){
				nodes.peek().setHasEndTag(true);
				nodes.pop();
			}else{	
				////////////////////////////////
				System.out.println("Tags are miss matching in XML DOC"+e.getTagName());
				
				
			
				
				int k=-1;
				   for(int i=0; i<nodes.size();i++){
					   if(tagName.equals(nodes.get(i).getTagName())){
						   k=i;
						   break;
					   }
				   }
			System.out.println("Remove till : "+k);
			
			 int l=nodes.size();
			 if(k!=-1){
				  while(!(nodes.size()==k)){
					   nodes.pop();
				   }
			 }else{
				 
			 }
			 
				
				
				
				/*try {
					throw new XmlCsvParserException("XmlTreaBuilder", null);
				} catch (XmlCsvParserException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
				////////////////////////////////////////
			}
		}else if(tokenType==3){
			data=new XmlData();
			data.setData(tagName);
			nodes.peek().addChildNode(data);
		}
	}
}
