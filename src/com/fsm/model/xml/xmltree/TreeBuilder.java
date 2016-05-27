package com.fsm.model.xml.xmltree;

import java.util.HashMap;

import com.fsm.model.xml.Element;

public interface TreeBuilder {
	public Element getRootElement();
	public void generateTreeStructure(String tagName, int tokenType, HashMap<String, String> atributes);
}
