package edu.uml.chrisweakley.services;

public class StockServiceException extends Exception {
    public StockServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public StockServiceException(String s) {
        super(s);
    }
}
