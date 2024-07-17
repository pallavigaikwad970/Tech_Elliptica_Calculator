package org.exception;

import java.util.logging.Logger;

public class InvalidCalculatorBtnException extends Exception{
    private static final Logger logger = Logger.getLogger(InvalidCalculatorBtnException.class.getName());
    public InvalidCalculatorBtnException(String msg){
        super(msg);
        logger.severe(msg);
    }
}
