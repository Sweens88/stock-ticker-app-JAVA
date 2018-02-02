package edu.uml.chrisweakley.services;

import javax.validation.constraints.NotNull;

/**
 * Christopher Weakley 9/17/17
 * Java 3030 - Assignment Two
 *
 * A factory class designed to return an implementation of a <CODE>StockService</CODE>
 */
public class ServiceFactory {

    /**
     * Private constructor to ensure only static methods are used
     */
    private ServiceFactory(){}

    /**
     * Static factory method for returning an instance of whichever <CODE>StockService</CODE>
     * we decide to use
     * @return the desired <CODE>StockService</CODE> instance
     */
    public static @NotNull StockService getStockServiceInstance() {
        return new DatabaseStockService();
    }

    /**
     * Static factory method for returning an instance of whichever PersonServce we
     * decide to use
     * @return the desired <code>PersonService</code> instance
     */
    public static @NotNull PersonService getPersonServiceInstance() {
        return new DatabasePersonService();
    }

}
