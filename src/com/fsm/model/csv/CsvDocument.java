package com.fsm.model.csv;

import java.util.List;


import com.fsm.tokenization.csv.CSVTokenizer;


public class CsvDocument {
	
	private CSVTokenizer tokenizer;
	
	private List<String> lines;
	private ElementsKeaper elementsKeaper;
	private DocumentKeeper docKeeper;
	public List<ElementsKeaper> allElem;
	
	
	
	public CSVTokenizer getTokenizer() {
		return tokenizer;
	}



	public void setTokenizer(CSVTokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}



	public DocumentKeeper getDocKeeper() {
		return docKeeper;
	}



	public void setDocKeeper(DocumentKeeper docKeeper) {
		this.docKeeper = docKeeper;
	}



	public List<ElementsKeaper> getAllElem() {
		return allElem;
	}



	public void setAllElem(List<ElementsKeaper> allElem) {
		this.allElem = allElem;
	}



	public List<String> getLines() {
		return lines;
	}

	
	

	public void setLines(List<String> lines) {
		this.lines = lines;
	}



	public CsvDocument(){
		
	}

	private void processCsvTags() {
		// TODO Auto-generated method stub
		for(String line : lines){
			elementsKeaper=new ElementsKeaper();
			elementsKeaper=tokenizer.generateElement(line);
			docKeeper.addData(elementsKeaper);
		}
	}
	
	public List<ElementsKeaper> getCsvElements(){

		processCsvTags();
		 allElem=docKeeper.getAllData();
		 return allElem;

	}
	
}
