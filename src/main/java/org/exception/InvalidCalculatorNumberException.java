package org.exception;

import java.util.logging.Logger;

public class InvalidCalculatorNumberException extends Exception{
    private static final Logger logger = Logger.getLogger(InvalidCalculatorNumberException.class.getName());
    public InvalidCalculatorNumberException(String msg){
        super(msg);
        logger.severe(msg);
    }
}
