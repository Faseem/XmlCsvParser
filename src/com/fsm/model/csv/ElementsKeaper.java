package com.fsm.model.csv;

import java.util.ArrayList;
import java.util.List;

public class ElementsKeaper {
	
	List<CsvData> data=new ArrayList<>();

	public List<CsvData> getCsvElement() {
		return data;
	}

	public void addData(CsvData data) {
		this.data.add(data);
	}
	
	
}
