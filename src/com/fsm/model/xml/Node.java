package com.fsm.model.xml;

import java.util.List;

public interface Node {
	
	int DOCUMENT_NODE=1;
	int ELEMENT_NODE=2;
	int DATA_NODE=3;
	
	int getType();
	
	List<Node> getChildNodes();

//	void addChildNode(Node child);
	
	
}
