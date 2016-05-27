package com.fsm.tokenization.csv;

import com.fsm.model.csv.ElementsKeaper;

public interface CSVTokenizer {
	
	//public final static String DELIMITER=",";

	ElementsKeaper generateElement(String line);


}
