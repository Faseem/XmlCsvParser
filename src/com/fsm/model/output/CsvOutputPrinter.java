package com.fsm.model.output;

import java.util.List;

import com.fsm.model.csv.CsvData;

public class CsvOutputPrinter implements Printer<List<CsvData>>{

	@Override
	public void printElement(List<CsvData> elements) {
		// TODO Auto-generated method stub
		
		if(elements!=null){
			for(CsvData elem: elements){
				System.out.println(elem.getData());
			}
		}else{
			System.out.print("Null element");
		}
			
	}
}
