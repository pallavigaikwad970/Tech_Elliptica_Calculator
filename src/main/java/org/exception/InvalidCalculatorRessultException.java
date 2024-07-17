package org.exception;

import java.util.logging.Logger;

public class InvalidCalculatorRessultException extends Exception{
    private static final Logger logger = Logger.getLogger(InvalidCalculatorRessultException.class.getName());
    public InvalidCalculatorRessultException(String msg){
        super(msg);
        logger.severe(msg);
    }
}
