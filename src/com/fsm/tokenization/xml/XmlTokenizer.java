package com.fsm.tokenization.xml;

import java.util.HashMap;

import com.fsm.exception.XmlCsvParserException;

public interface XmlTokenizer {
	
	static final int START_TAG=1;
	static final int END_TAG=2;
	static final int DATA=3;
	
	void genNextToken() throws XmlCsvParserException;
	int getTokenType();
	String getTagName();
	HashMap<String, String> getAtributes();
	int getAtributesCount();
	void setInput(String input);

}
