package edu.uml.chrisweakley.tests.services;

import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.services.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class for testing ServiceFactory methods
 */
public class ServiceFactoryTest {

    /**
     * Positive Test
     * Tests the return type of the <CODE>getInstance()</CODE> method which
     * returns a StockService implementation currently set to return BasicStockService
     */
    @Test
    public void getStockInstanceTestPositive() {
        StockService stockService = ServiceFactory.getStockServiceInstance();
        assertTrue("return type DatabaseStockService", stockService.getClass().equals(DatabaseStockService.class));
    }

    /**
     * Negative Test
     * Tests the return type of the <CODE>getInstance()</CODE> method which
     * returns a StockService implementation currently set to return BasicStockService
     */

    @Test
    public void getStockInstanceTestNegative() {
        StockService stockService = ServiceFactory.getStockServiceInstance();
        assertFalse("should not return type StockQuote", stockService.getClass().equals(StockQuote.class));
    }

    /**
     * Positive Test
     * Tests the return type of the <CODE>getInstance()</CODE> method which
     * returns a StockService implementation currently set to return BasicStockService
     */
    @Test
    public void getPersonInstanceTestPositive() {
        PersonService personService = ServiceFactory.getPersonServiceInstance();
        assertTrue("return type BasicStockService",
                personService.getClass().equals(DatabasePersonService.class));
    }

    /**
     * Negative Test
     * Tests the return type of the <CODE>getInstance()</CODE> method which
     * returns a StockService implementation currently set to return BasicStockService
     */

    @Test
    public void getPersonInstanceTestNegative() {
        PersonService personService = ServiceFactory.getPersonServiceInstance();
        assertFalse("should not return type StockQuote", personService.getClass().equals(StockQuote.class));
    }




}