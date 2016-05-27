package com.fsm.parserfactory;


import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import com.fsm.model.reader.ParserReader;

public class ParserFactory{
	
	
	private static Parser parser;
	
	public static Parser getParser(ApplicationContext context) {
		
		
		System.out.println("Im geting the Parser Now");
		
		String fileType;
		ParserReader reader = (ParserReader)context.getBean("readerBean");
		fileType=reader.getFileType();
		
		System.out.println("File Type is : "+fileType);
		
		BeanDefinitionRegistry factory = (BeanDefinitionRegistry) context.getAutowireCapableBeanFactory();
		
		if(fileType.equalsIgnoreCase("text/xml")){
		
			factory.removeBeanDefinition("csvParser");
			parser=(XmlParser) context.getBean("xmlParser");
			System.out.println("Setting XML Parser");
			
		}else if(fileType.equalsIgnoreCase("application/vnd.ms-excel")){
			
			parser=(CsvParser) context.getBean("csvParser");
			factory.removeBeanDefinition("xmlParser");
			System.out.println("Setting CSV Parser");
			
		}
		
		return parser;
		
	}

}
