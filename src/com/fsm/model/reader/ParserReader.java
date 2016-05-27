package com.fsm.model.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.fsm.exception.XmlCsvParserException;


public class ParserReader implements Reader, ApplicationContextAware{
	
	private String filePath;
	private BufferedReader inputBuffer;
	public List<String> tagList;
	String fileType;
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public ParserReader() {
		// TODO Auto-generated constructor stub
	
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) throws XmlCsvParserException {
		this.filePath = filePath;
		try {
			
			//while setting the file path filling the input Buffer using FileReader
			//and initializing a tagList as a ArrayList to store the tags in the file.
			
			this.inputBuffer=new BufferedReader(new FileReader(filePath));
			this.tagList=new ArrayList<String>();
		} catch (FileNotFoundException e) {
			
			//if any exceptions occurs it will throw a XmlCsvParser Exception
			
			throw new XmlCsvParserException("Reader", e);
			// TODO Auto-generated catch block
		}
	}
	
	// as soon as the bean is created and fileType is set for the bean  getTagList Method will be called to assign values to the List
	
	@Override
	public List<String> getTagList() throws XmlCsvParserException{
		String data;
		try {
			//checking the content type and adding the values to tag list
			int count=0;
			String keeper="";
			if(fileType.equalsIgnoreCase("text/xml")){
				while ((data = inputBuffer.readLine()) != null ) {	
					//we have to extract the xml tag 	
					if(!data.equals("")){
						keeper=keeper+data;
						if(keeper.indexOf('<')!=-1 && keeper.indexOf('>')!=-1){
							if(count==0){
								//the first line maight be a version tag line so have to omit the line
								final Pattern tagPttern=Pattern.compile("<\\?(.+?)\\?>");
								Matcher matcher=tagPttern.matcher(keeper);
								if(!matcher.find()){
									System.out.println("Not Found XML Description Tag in first line");
									//If the first line is not a version tag we have to extract it
									
									if(keeper.indexOf('!')==-1)
									extractTag(keeper);
									keeper="";
								}else{
									System.out.println("Found XML Description Tag in first line");
									keeper="";
								}
								count++;
							}else{
								//calling method extractTag() to extract the tag
								extractTag(keeper);
								keeper="";
							}							
						}else{
						//	keeper=keeper+data;
						}	
					}	
				}				
				
			}else if(fileType.equalsIgnoreCase("application/vnd.ms-excel")){
				while ((data = inputBuffer.readLine()) != null ) {		
					//csv lines can be added line by line
					tagList.add(data);
				}
			}else{
				throw new XmlCsvParserException("Reader Class - Content nnot Match to XML or CSV", null);
			}
			
			
			
			//inputBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
				throw new XmlCsvParserException("Reader", e);
		}
		return tagList;
	}
	
	private void extractTag(String line) {
		// TODO Auto-generated method stub
		
		//Using RegEx checking for a start tag or end tag if any match in the line the line will be added to list
		
		
		final Pattern tagPtternC=Pattern.compile("<(.+?)>");
		Matcher matcherC=tagPtternC.matcher(line);
		
		int i=0;
		while(matcherC.find()){
			i++;
		}
		
		//If there is no open tag or closing tag it should be a data line....
		//so we have to extract the data here
		
		if(i==0){
			
			//skipping the white spaces
			
			int k=0;
			for(int l=0;l<line.length();l++){
				if(!Character.isWhitespace(line.charAt(l))){
					k=l;
					break;
				}
			}

			
		//	check whether the data line has > like this < as required if not adding thoese
			
			if(line.charAt(k)!='>'){
				line=line.trim();
				line=">"+line;
				
				if(line.charAt(line.length()-1)!='<'){
					line=line+"<";
				}
			}
			
		//And now calling extract data method
			
			extractText(line.trim());
			
		//if the line has only two  or one tag'<>'	tags
			
		}else if(i<=2){
			
			//skipping the white spaces
			
			int k=0;
			for(int l=0;l<line.length();l++){
				if(!Character.isWhitespace(line.charAt(l))){
					k=l;
					break;
				}
			}
			
		//	System.out.println("FirstLine:"+line.charAt(k));
			if(Character.isLetterOrDigit(line.charAt(k))){
				line=line.trim();
				//line=">"+line;
				
				extractText(">"+line);
			}
			
			addToList(line);
			
		}else if(i>2){
			//if there are more than 2 open tags have to split it again..... which meaans more tags are there in the line....
			
			split(line);
			
		}
	}
	
	private void addToList(String line) {
		// TODO Auto-generated method stub
		final Pattern tagPttern=Pattern.compile("<(.+?)>");
		Matcher matcher=tagPttern.matcher(line);
		
		
		int count=0;
		while(matcher.find()){
			
			String toAdd=matcher.group();
			
			int k=0;
			

		    for(int i=0;i<toAdd.length();i++){
				if(toAdd.charAt(i)=='>'){
					k=i-1;
					break;
				}
			}
			
			
			if(toAdd.charAt(k)=='/'){
			
				StringBuilder bulid = new StringBuilder(toAdd);
			    bulid.deleteCharAt(k);  // Shift the positions front.
			    toAdd=bulid.toString();
			    toAdd.trim();
			    
			    this.tagList.add(toAdd);
			    
				for(int i=0;i<toAdd.length();i++){
					if(toAdd.charAt(i)=='<'){
						k=i+1;
						break;
					}
				}
					   
			    toAdd = toAdd.substring(0, k) + "/" + toAdd.substring(k, toAdd.length());
			    String closeTag="";
			    for(int i=0;i<toAdd.length();i++){
			    	if(!Character.isWhitespace(toAdd.charAt(i))){
			    		closeTag=closeTag+toAdd.charAt(i);
			    	}else{
			    		break;
			    	}
			    	
			    }
			    
			    if(!closeTag.contains(">")){
			    	 closeTag=closeTag+">";
			    }
			   // toAdd.trim();
			    this.tagList.add(closeTag);
			}else{
				this.tagList.add(toAdd);
			}
			
			if(count==0){
				
				if(line.charAt(line.length()-1)!='>'){
					extractText(line+"<");
				}
				
				else{
					extractText(line);
				}
				count++;
			}
		}
		
	}

	private void split(String line) {
		
		int k=0;
		for(int i=0;i<line.length();i++){
			if(!Character.isWhitespace(line.charAt(i))){
				k=i;
				break;
			}
		}
		String toParse=line.substring(k);	
		String[] lineSplitArray=toParse.split(">");
		
		int n=0;
		for(int i=0;i<lineSplitArray.length;i++){

			String splitedIem=lineSplitArray[i];
			
			for(int j=0;j<splitedIem.length();j++){
				if(!Character.isWhitespace(splitedIem.charAt(j))){
					n=j;
					break;
				}
			}
		if(splitedIem.trim().length()>0){
			
				if(splitedIem.charAt(n)=='<'){
				
				//if it is first type we have to extract the tag
			
				extractTag(lineSplitArray[i]+">");
				
		//		System.out.println("sending to extract  : "+a[i]+">");
			}else if(splitedIem.charAt(n)=='>'){
				
				// if it is second type we have to extract it as well.
				
				extractTag(lineSplitArray[i]+">");
	//			System.out.println("sending to extract  : "+a[i]+">");
			}else if(Character.isLetterOrDigit(splitedIem.charAt(n))){
				if((splitedIem.indexOf('>')==-1)){
					if((splitedIem.indexOf('<')==-1)){
						extractTag(">"+splitedIem+"<");
						break;
					}
				}				
				extractTag(splitedIem+">");
			}
			
		}
			
			
			
		}
		
	}

	private void extractText(String line) {
		// TODO Auto-generated method stub
		//Using RegEx checking for data as the data will be placed in between ">data<"
		final Pattern textPattern = Pattern.compile(">(.+?)<");
		Matcher matcher=textPattern.matcher(line);
		
		while(matcher.find()){
			
			String data=matcher.group();
			String last=tagList.get(tagList.size()-1);
			String toAdd="";
			
			//if last tag in the tagList is a data we will have to add the data to existing data
			
			if(textPattern.matcher(last).find()){	
				
				if(!data.equals(last)){
					for(int i=0;i<last.length()-1;i++){
						toAdd=toAdd+last.charAt(i);
					}
					toAdd=toAdd.trim();
					for(int i=1;i<data.length();i++){
						toAdd=toAdd+data.charAt(i);
					}
					
					//removing the old data and adding new data
					
					this.tagList.remove(this.tagList.size()-1);
					this.tagList.add(toAdd);
				}
				//this.tagList.add(toAdd);
			}else{
				this.tagList.add(data);
			}
			
		}			
	}
	
	//after the bean is created this method will be called to get the tag list filled up with values
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		// TODO Auto-generated method stub
		
		//gettting back the bean created
		
		//Reader reader=(ParserReader)context.getBean("readerBean");
		
		System.out.println("file Path is : "+filePath);
		
		File fileToParse=new File(filePath);
		
		try {
			//setting the fileType using probeContentType method
			
			fileType = Files.probeContentType(fileToParse.toPath());
			
			System.out.println("File Type is : "+fileType);
			
			try {
				
				//storing value to list
				
				getTagList();
				
			} catch (XmlCsvParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
				try {
					throw new XmlCsvParserException("ReaderClass", e);
				} catch (XmlCsvParserException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
		}
	}

}
