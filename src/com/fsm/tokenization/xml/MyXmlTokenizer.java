package com.fsm.tokenization.xml;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsm.exception.XmlCsvParserException;

public class MyXmlTokenizer implements XmlTokenizer {
	
	private HashMap<String, String> atributes;
	private String tagName;
	private int tokenType;
	private String input;
	
	
	

	public MyXmlTokenizer() {
		this.atributes=new HashMap<String,String>();
		this.tokenType=0;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getTokenType() {
		// TODO Auto-generated method stub
		return this.tokenType;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return this.tagName;
	}

	@Override
	public HashMap<String, String> getAtributes() {
		// TODO Auto-generated method stub
		return this.atributes;
	}

	@Override
	public int getAtributesCount() {
		// TODO Auto-generated method stub
		return this.atributes.size();
	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		this.input=input;

	}
	
	@Override
	public void genNextToken() throws XmlCsvParserException {
		// TODO Auto-generated method stub
		this.atributes=new HashMap<>();
		matchInput();
		
		if(this.tokenType==1){
		//	this.tagName=generateTagName();
			this.tagName=generateOpenTagName();
			this.tokenType=START_TAG;
		}else if(this.tokenType==2){
			resetState(true);
		//	this.tagName=generateTagName();
			this.tagName=generateCloseTagName();
			this.tokenType=END_TAG;
		}else if(this.tokenType==3){
			resetState(true);
			this.tagName=generateData();
			this.tokenType=DATA;
		}else if(this.tokenType < 0 && this.tokenType > 3){
			System.out.println("Wrong tag Identification Err..");
			throw new XmlCsvParserException("Tokenizer",null);
		}
	
	}
	

	private String generateCloseTagName() {
		// TODO Auto-generated method stub
		String tagName="";
		
		final Pattern textPattern = Pattern.compile("</(.+?)>");
		Matcher matcher=textPattern.matcher(input);
		
		while(matcher.find()){
			tagName=matcher.group(1);
		}
		return tagName;
		
		
	}

	private String generateOpenTagName() {
		// TODO Auto-generated method stub
		String tagName="";
		char c;
		
		for(int i=0;i<input.length()-1;i++){
			c=input.charAt(i);
			if(c=='<'){
				continue;
			}else{
				if(Character.isWhitespace(c)){
					genarateAtributes(i);
					break;
				}else{
					tagName=tagName+c;
				}
			}
			
		}
		
		
		return tagName;
	}

	private String generateData() {
		String data="";
		
		final Pattern textPattern = Pattern.compile(">(.+?)<");
		Matcher matcher=textPattern.matcher(input);
		
		while(matcher.find()){
			data=matcher.group(1);
		}
		
		int lessThanInt=data.indexOf("&lt;");
		int greaterThanInt=data.indexOf("&gt;");
		int ampInt=data.indexOf("&amp;");
		int aposInt=data.indexOf("&apos;");
		int quotInt=data.indexOf("&quot;");
		
		if(lessThanInt!=-1){
			data = data.substring(0, lessThanInt) + "<" + data.substring((4+lessThanInt), data.length());			
		}
		if(greaterThanInt!=-1){
			data = data.substring(0, greaterThanInt) + ">" + data.substring((4+greaterThanInt), data.length());			
		}
		if(ampInt!=-1){
			data = data.substring(0, ampInt) + "&" + data.substring((5+ampInt), data.length());			
		}
		if(aposInt!=-1){
			data = data.substring(0, aposInt) + "'" + data.substring((6+aposInt), data.length());			
		}
		if(quotInt!=-1){
			data = data.substring(0, quotInt) + '"' + data.substring((6+quotInt), data.length());			
		}
		
		return data;
	}

	private void resetState(boolean resetState) {
		// TODO Auto-generated method stub
		if(resetState){
			tagName="";
		}
		this.atributes.clear();
		this.tokenType=0;
		
	}

	private HashMap<String, String> genarateAtributes(int startFrom) {
		// TODO Auto-generated method stub
		final Pattern pattern = Pattern.compile("(\\S+)=[\"']?((?:.(?![\"']?\\s+(?:\\S+)=|[>\"']))+.)[\"']?");
		Matcher matcher = pattern.matcher(input);

		while(matcher.find()) {
		    String match = matcher.group();
		    if(match.contains("=")){
		    	String  attrArr[]= match.split("=");
				atributes.put(attrArr[0], attrArr[1].replace("\"", "").replace("'", ""));
				
		    }
		}	
		return atributes;
		
	}

	private void matchInput() {
		// TODO Auto-generated method stub
		if(this.input.matches("</(.+?)>")){
			this.tokenType=END_TAG;
		}else if(this.input.matches("<(.+?)>")){
			this.tokenType=START_TAG;
		}else if(this.input.matches(">(.+?)<")){
			this.tokenType=DATA;
		}else{
			//System.out.println("Un defined token"+input);
		}	
		
	}
}
