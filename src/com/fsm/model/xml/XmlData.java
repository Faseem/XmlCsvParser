package com.fsm.model.xml;

public class XmlData extends XmlNode implements Data{
	
	String data;
	
	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return this.data;
	}
	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		this.data=data;
	}
	
	@Override
	public int getType(){
		return DATA_NODE;
	}
}
