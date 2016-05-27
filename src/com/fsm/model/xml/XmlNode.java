package com.fsm.model.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlNode implements Node {
	
	List<Node> childrenList;
	
	XmlNode(){
		childrenList=new ArrayList<Node>();
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Node> getChildNodes() {
		// TODO Auto-generated method stub
		return childrenList;
	}
	

	public void addChildNode(Node child){
		this.childrenList.add(child);
	}
}
