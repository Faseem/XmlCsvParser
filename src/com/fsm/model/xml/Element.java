package com.fsm.model.xml;

import java.util.HashMap;
import java.util.List;

public interface Element extends Node {
	
	
	
	String getTagName();
	
	void setTagName(String tagName);
	
	HashMap<String,String> getAtributes();
	
	void setAtributes(HashMap<String, String> atributes);
	
	boolean isHasEndTag();
	
	void setHasEndTag(boolean hasEndTag);
	
	void addChildNode(Node node);
	
	List<Node> getChildNodes();

}
