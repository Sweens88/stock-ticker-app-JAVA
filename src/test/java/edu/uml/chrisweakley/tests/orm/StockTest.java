package edu.uml.chrisweakley.tests.orm;

import edu.uml.chrisweakley.orm.Stock;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for Hobby class
 */
public class StockTest {

    private final static String symbol = "AAPL";


    /**
     * Testing helper method for generating Hobby test data
     *
     * @return a Hobby object that uses static constants for data.
     */
    private static Stock createStock() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        return stock;
    }

    @Test
    public void testStockSettersAndGettersPositive() {
        Stock stock = createStock();
        int id = 10;
        stock.setId(id);
        assertEquals("Name", symbol, stock.getSymbol());
        assertEquals("id", id, stock.getId());

    }

    @Test
    public void testStockSetterAndGettersNegative() {
        Stock stock = createStock();
        int id = 10;
        String wrongSymbol = "goog";
        int wrongId = 11;
        stock.setId(id);
        assertFalse("Name", wrongSymbol.equals(stock.getSymbol()));
        assertFalse("id", wrongId == stock.getId());
    }

    @Test
    public void testEqualsMethod() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);

        Stock stock2 = stock;
        Stock stock3 = new Stock();

        assertTrue("stocks should be equal", stock.equals(stock2));
        assertFalse("should not be equal", stock.equals(stock3));
    }
    @Test
    public void testHashCode() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        Stock stock2 = stock;
        assertEquals("hash codes should be equal", stock.hashCode(), stock2.hashCode());
    }

    @Test
    public void testToString() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        assertTrue("should contain string", stock.toString() != null);
    }



}
