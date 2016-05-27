package com.fsm.model.csv;

import java.util.ArrayList;
import java.util.List;

public class DocumentKeeper {
	
	List<ElementsKeaper> data=new ArrayList<>();
	
	void addData(ElementsKeaper e){
		data.add(e);
	}
	
	List<ElementsKeaper> getAllData(){
		return data;
	}
}
