package com.fsm.parser.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fsm.model.output.PrinterFactory;
import com.fsm.model.output.Printer;
import com.fsm.parserfactory.ParserFactory;
import com.fsm.parserfactory.Parser;




public class Application {
	
	static Parser parser;
	static Printer printer;

	
	public static void main(String args[]){
		
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");	
			
			System.out.println("--------------------------------------------Initializing Parser Iterface---------------------------------------------");
			parser=ParserFactory.getParser(context);
			
			System.out.println("--------------------------------------------Initializing Printer Iterface--------------------------------------------");
			printer=PrinterFactory.getPrinter(context);
			
			System.out.println("------------------------------------------------Printing 1st Element-------------------------------------------------");
			
			printer.printElement(parser.getFirstElement());
			
			System.out.println("--------------------------------------------- --Printing last Element------------------------------------------------");
			printer.printElement(parser.getLastElement());
			
			System.out.println("----------------------------------------------Printing Element by index----------------------------------------------");
			printer.printElement(parser.getElementByIndex(1));
			
			System.out.println("-----------------------------------------------Printing Element by Name----------------------------------------------");
			printer.printElement(parser.getElementByName("First Name"));

	}

}
