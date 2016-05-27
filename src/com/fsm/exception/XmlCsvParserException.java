package com.fsm.exception;

public class XmlCsvParserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String className;
    private Exception exception ;

    
    
    public XmlCsvParserException(String className,Exception exception) {
        super("TempoEngineException......");
        this.className=className;
        this.exception=exception;
    }
    @Override
    public String getMessage()
    {
        return super.getMessage() + "\nError Occurs at Class Name : " +className +"\n" + "And The Exception is :"+exception.getMessage()+"\n Caught By XMlCsv Parser Exception Class";
    }
}
