package com.fsm.model.xml;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsm.exception.XmlCsvParserException;
import com.fsm.model.reader.Reader;
import com.fsm.model.xml.xmltree.TreeBuilder;
import com.fsm.tokenization.xml.XmlTokenizer;

public class XmlDocument extends XmlNode implements Document {
	
	private XmlTokenizer tokenizer;
	private TreeBuilder treeBuilder;
	private Reader xmlReader;
	public Element rootElement;

	public Element getRootElement() {
		return rootElement;
	}

	public XmlTokenizer getTokenizer() {
		return tokenizer;
	}

	public void setTokenizer(XmlTokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public TreeBuilder getTreeBuilder() {
		return treeBuilder;
	}

	public void setTreeBuilder(TreeBuilder treeBuilder) {
		this.treeBuilder = treeBuilder;
	}

	public Reader getXmlReader() {
		return xmlReader;
	}

	public void setXmlReader(Reader xmlReader) {
		this.xmlReader = xmlReader;
	}
	
	public XmlDocument() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		processXmlTags();
		rootElement=treeBuilder.getRootElement();
		return rootElement;
	}
	
	@Override
	public int getType() {
		return DOCUMENT_NODE;
	}
	
	private Document processXmlTags() {
		// TODO Auto-generated method stub
		try {

			for(String tag : xmlReader.getTagList()){
				
				final Pattern commentPattern=Pattern.compile("<!--(.+?)-->");
				Matcher matcher=commentPattern.matcher(tag);
				
				final Pattern virsionTagPttern=Pattern.compile("<?(.+?)?>");
				Matcher versionMatcher=virsionTagPttern.matcher(tag);
				
				final Pattern doctypTagPttern=Pattern.compile("<!(.+?)>");
				Matcher docTypeMatcher=doctypTagPttern.matcher(tag);
				
				final Pattern docTagPttern=Pattern.compile("<!(.+?)>");
				Matcher docMatcher=docTagPttern.matcher(tag);
				
				
			//	System.out.println(emptyDataMatcher.group(1));
				
				if(!matcher.find()){
					if(!versionMatcher.find()){
						if(!docTypeMatcher.find()){
							if(!docMatcher.find()){
								
									tokenizer.setInput(tag);
									tokenizer.genNextToken();
									boolean dataIsNotEmpty=checkData(tokenizer.getTagName());
									if(tokenizer.getTokenType()==3){
										if(dataIsNotEmpty){
											treeBuilder.generateTreeStructure(tokenizer.getTagName(),tokenizer.getTokenType(),tokenizer.getAtributes());
										}else{
											//System.out.println("EmptyDataFound");
										}										
									}else{
										treeBuilder.generateTreeStructure(tokenizer.getTagName(),tokenizer.getTokenType(),tokenizer.getAtributes());
									}
							}							
						}						
					}
				}
				
			}
		} catch (XmlCsvParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
		private boolean checkData(String string)
			{
			   return string != null && !string.isEmpty() && !string.trim().isEmpty();
			}
}
