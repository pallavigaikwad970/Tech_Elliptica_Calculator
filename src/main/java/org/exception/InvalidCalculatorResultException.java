package org.exception;

public class InvalidCalculatorResultException extends Exception {
    public InvalidCalculatorResultException(String result){
        super(result);
    }


}
