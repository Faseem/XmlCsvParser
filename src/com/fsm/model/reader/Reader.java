package com.fsm.model.reader;

import java.util.List;

import com.fsm.exception.XmlCsvParserException;

public interface Reader {
	
	public List<String> getTagList() throws XmlCsvParserException;

}
