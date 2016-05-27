package com.fsm.parserfactory;

import java.util.ArrayList;
import java.util.List;
import com.fsm.model.csv.CsvDocument;
import com.fsm.model.csv.CsvData;
import com.fsm.model.csv.ElementsKeaper;

public class CsvParser<T extends List<CsvData>> implements Parser{
	
	public CsvDocument csvDocument;
	private List<ElementsKeaper> allElement;
	
	
	public CsvParser(){
		
	}
	
	public CsvDocument getCsvDocument() {
		return csvDocument;
	}

	public void setCsvDocument(CsvDocument csvDocument) {
		this.csvDocument = csvDocument;
	}

	public List<ElementsKeaper> getAllElement() {
		return allElement;
	}

	public void setAllElement(List<ElementsKeaper> allElement) {
		this.allElement = allElement;
	}
	
	
	
	@Override
	public T getFirstElement() {
		// TODO Auto-generated method stub
		return (T) allElement.get(0).getCsvElement();
	}

	@Override
	public T getLastElement() {
		// TODO Auto-generated method stub
		return (T) allElement.get(allElement.size()-1).getCsvElement();
	}

	@Override
	public T getElementByIndex(int index) {
		// TODO Auto-generated method stub
		return (T) allElement.get(index).getCsvElement();
	}

	@Override
	public T getElementByName(String name) {
		// TODO Auto-generated method stub
		List<CsvData> values=new ArrayList<CsvData>();
		List<CsvData> ids= (List<CsvData>) allElement.get(0).getCsvElement();
		int i=0;
		int k = 0;
		
		for(CsvData elm : ids){
		
			if(elm.getData().equals(name)){

				i=i;
				break;
			}
			
			i++;
		}
		
		for(int j=1;j<allElement.size();j++){
			CsvData elem = allElement.get(j).getCsvElement().get(i);
			values.add(elem);
		}
		
		
		return (T) values;
	}

	
	

	

}
