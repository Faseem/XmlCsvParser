package com.fsm.model.output;

import org.springframework.context.ApplicationContext;

import com.fsm.exception.XmlCsvParserException;
import com.fsm.model.reader.ParserReader;


public class PrinterFactory{

private static Printer printer;
	
	public static Printer getPrinter(ApplicationContext context) {
		
		System.out.println("Im geting the Printer Now");
		
		String fileType;
		ParserReader reader = (ParserReader)context.getBean("readerBean");
		fileType=reader.getFileType();
		
		//System.out.println("File Type is : "+fileType);
		
		if(fileType.equalsIgnoreCase("text/xml")){		
			System.out.println("Setting XML Printer");
			printer=(XmlOutputPrinter) context.getBean("XmlOutputBean");
		}else if(fileType.equalsIgnoreCase("application/vnd.ms-excel")){
			System.out.println("Setting Csv Printer");
			printer=(CsvOutputPrinter) context.getBean("CsvOutputBean");
		}else{
			try {
				throw new XmlCsvParserException("PrinterFactory - No Such File Type", null);
			} catch (XmlCsvParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return printer;
	}


}
