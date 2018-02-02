package edu.uml.chrisweakley.tests.model;


import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class for testing methods found in the StockQuote Class, positive and negative
 * tests for getters.
 */
public class StockQuoteTest {

    private String stockSymbol = "AAPL";
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yy");
    private LocalDate from = LocalDate.parse("09/10/17", df);
    private LocalDate until = LocalDate.parse("09/18/17", df);
    private BigDecimal price = BigDecimal.valueOf(120);
    private LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIDNIGHT);
    private LocalDateTime untilDate = LocalDateTime.of(until, LocalTime.MIDNIGHT);
    private IntervalEnum interval= IntervalEnum.OPEN_CLOSE;
    private StockQuote stockQuote = new StockQuote(stockSymbol, fromDate, untilDate, interval);


    /**
     * Positive Test
     * Testing the getStockSymbol() getter
     */
    @Test
    public void getStockSymbolTestPostivie() {
        assertTrue("stock symbol should be AAPL", stockQuote.getSymbol().equals("AAPL"));
    }

    /**
     * Negative Test
     * Testing the getStockSymbol() getter
     */
    @Test
    public void getStockSymbolTestNegative() {
        assertFalse("stock symbol should not be TSLA", stockQuote.getSymbol().equals("TSLA"));
    }


    /**
     * Positive Test
     * Testing the getfrom() getter
     */
    @Test
    public void getfromTestPostivie() {
        assertTrue("from date should be 9/10/17",
                stockQuote.getFrom().equals(fromDate));
    }

    /**
     * Negative Test
     * Testing the getfrom() getter
     */
    @Test
    public void getfromTestNegative() {
        assertFalse("from date should be 9/10/17", stockQuote.getFrom().equals(untilDate));
    }

    /**
     * Positive Test
     * Testing the getuntil() getter
     */
    @Test
    public void getuntilTestPostivie() {
        assertTrue("until date should be 9/18/17", stockQuote.getUntil().equals(untilDate));
    }

    /**
     * Negative Test
     * Testing the getuntil() getter
     */
    @Test
    public void getuntilTestNegative() {
        assertFalse("until date should be 9/18/17", stockQuote.getUntil().equals(from));
    }

    /**
     * Positive Test
     * Testing the getIntervalEnum() getter
     */
    @Test
    public void getIntervalTestPostivie() {
        assertTrue("interval should be OPEN_CLOSE",
                stockQuote.getIntervalEnum().equals(IntervalEnum.OPEN_CLOSE));
    }

    /**
     * Negative Test
     * Testing the getIntervalEnum() getter
     */
    @Test
    public void getIntervalTestNegative() {
        assertFalse("interval should be OPEN_CLOSE",
                stockQuote.getIntervalEnum().equals(IntervalEnum.MIDDAY));
    }

    @Test
    public void testToString() {
        stockQuote.setDate(fromDate);
        stockQuote.setPrice(price);
        assertTrue("should return string",
                stockQuote.toString().equals("StockQuote{symbol='AAPL', date=09/10/17 00:00, price=120}"));
    }

    @Test
    public void testSetDate() {
        stockQuote.setDate(fromDate);
        assertEquals("dates should be equal", stockQuote.getFrom(), fromDate);
    }

    @Test
    public void testSetPrice() {
        stockQuote.setPrice(price);
        assertEquals("price should be equal", stockQuote.getPrice(), price);
    }

}
