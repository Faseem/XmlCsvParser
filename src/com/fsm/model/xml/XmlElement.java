package com.fsm.model.xml;

import java.util.HashMap;
import java.util.Map;

public class XmlElement extends XmlNode implements Element{
	
	private boolean hasEndTag;
	String tagName;
	private Map<String,String> atributes;
	
	public XmlElement() {
		// TODO Auto-generated constructor stub
		hasEndTag=false;
		atributes=new HashMap<>();
	}
	
	@Override
	public void addChildNode(Node node) {
		super.addChildNode(node);
	}
	
	@Override
	public int getType() {
		return ELEMENT_NODE;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return tagName;
	}

	@Override
	public void setTagName(String tagName) {
		// TODO Auto-generated method stub
		this.tagName=tagName;
		
	}

	@Override
	public HashMap<String, String> getAtributes() {
		// TODO Auto-generated method stub
		return (HashMap<String, String>) atributes;
	}

	@Override
	public void setAtributes(HashMap<String, String> atributes) {
		// TODO Auto-generated method stub
		this.atributes=atributes;
	}

	
	@Override
	public boolean isHasEndTag() {
		// TODO Auto-generated method stub
		return hasEndTag;
	}

	@Override
	public void setHasEndTag(boolean hasEndTag) {
		// TODO Auto-generated method stub
		this.hasEndTag=hasEndTag;
	}



}
