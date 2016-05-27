package com.fsm.tokenization.csv;

import com.fsm.model.csv.CsvData;
import com.fsm.model.csv.ElementsKeaper;


public class MyCSVTokenizer implements CSVTokenizer{
	
	private String[] record;
    private ElementsKeaper elementsKeaper;
	private CsvData csvElement;
	private String DELIMITER;


	//private Box box;
   
	public String getDELIMITER() {
		return DELIMITER;
	}

	public void setDELIMITER(String dELIMITER) {
		DELIMITER = dELIMITER;
	}

	public ElementsKeaper getElementsKeaper() {
		return new ElementsKeaper();
	}

	public void setElementsKeaper(ElementsKeaper elementsKeaper) {
		this.elementsKeaper = elementsKeaper;
	}

	public CsvData getCsvElement() {
		return new CsvData();
	}

	public void setCsvElement(CsvData csvElement) {
		this.csvElement = csvElement;
	}

	public MyCSVTokenizer(){
 
	}
    
    @Override
    public ElementsKeaper generateElement(String line){
    	elementsKeaper=getElementsKeaper();
    	record=line.split(DELIMITER);
    	for(int i=0; i<record.length;i++){
    			csvElement=getCsvElement();
        		csvElement.setData(record[i]);
        		this.elementsKeaper.addData(csvElement);
    	} 	
    	return elementsKeaper;
    	
    }
    
   
    

}
