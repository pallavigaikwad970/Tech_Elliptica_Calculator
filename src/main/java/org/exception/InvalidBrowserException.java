package org.exception;

import java.util.logging.Logger;

public class InvalidBrowserException extends Exception {
    private static final Logger logger = Logger.getLogger(InvalidBrowserException.class.getName());
    public InvalidBrowserException(String msg){
        super(msg);
        logger.severe(msg);
    }
}
