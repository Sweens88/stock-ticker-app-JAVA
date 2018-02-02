package edu.uml.chrisweakley.tests.services;

import edu.uml.chrisweakley.services.StockServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockServiceExceptionTest {


    private final String message = "exception";
    private final StockServiceException stockServiceException =
            new StockServiceException("exception", new NullPointerException());

    private final StockServiceException stockServiceException2 =
            new StockServiceException("exception");

    @Test
    public void testPersonServiceException() {
        assertEquals(stockServiceException.getMessage(), message);
    }

    @Test
    public void testPersonServiceException2() {
        assertEquals(stockServiceException2.getMessage(), message);
    }

}