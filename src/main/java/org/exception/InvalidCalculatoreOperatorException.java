package org.exception;

import java.util.logging.Logger;

public class InvalidCalculatoreOperatorException extends Exception{
    private static final Logger logger = Logger.getLogger(InvalidCalculatoreOperatorException.class.getName());

    public InvalidCalculatoreOperatorException(String msg){
        super(msg);
        logger.severe(msg);// Log the exception message as severe (error) level
    }
}
