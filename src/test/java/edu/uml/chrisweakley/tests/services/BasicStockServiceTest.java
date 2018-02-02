package edu.uml.chrisweakley.tests.services;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.services.BasicStockService;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * A class for testing methods used by a mocked stock service
 */
public class BasicStockServiceTest {

    /**
     * Variable used for testing methods
     */
    private String stockSymbol = "AAPL";
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yy");
    private LocalDate from = LocalDate.parse("09/10/17", df);
    private LocalDate until = LocalDate.parse("09/18/17", df);

    private LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIDNIGHT);
    private LocalDateTime untilDate = LocalDateTime.of(until, LocalTime.MIDNIGHT);
    private IntervalEnum interval= IntervalEnum.OPEN_CLOSE;
    private BasicStockService stockService = new BasicStockService();


    @Test
    public void testGetStockQuote() {
        StockQuote stockQuote = stockService.getQuote(stockSymbol);
        assertTrue("Symbols should match", stockQuote.getSymbol().equals(stockSymbol));
    }

    @Test
    public void testGetStockWithRangeQuote() {
        List<StockQuote> stockQuotes = stockService.getQuote(stockSymbol, fromDate, untilDate);
        assertTrue("Symbols should match", stockQuotes.get(0).getSymbol().equals(stockSymbol));
    }

    @Test
    public void testGetStockQuoteWithRangeAndInterval() {
        List<StockQuote> stockQuotes = stockService.getQuote(stockSymbol, fromDate, untilDate, interval);
        assertTrue("Symbols should match", stockQuotes.get(0).getIntervalEnum().equals(interval));
    }


    /**
     * Positive Test
     * Mocked BasicStockService for testing the getQuote method
     * and retrieving the stocks symbol
     */
    @Test
    public void getStockQuoteTestPositive() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(stockSymbol,
                fromDate, untilDate, interval));

        StockQuote stockQuote = stockServiceMock.getQuote(stockSymbol);
        assertTrue("stock symbol should be AAPL", stockQuote.getSymbol().equals("AAPL"));
        assertTrue("from date should be 9/12/17", stockQuote.getFrom().equals(fromDate));
        assertTrue("until date should be 9/18/17", stockQuote.getUntil().equals(untilDate));
        assertTrue("interval should be OPEN_CLOSE",
                stockQuote.getIntervalEnum().equals(IntervalEnum.OPEN_CLOSE));
    }

    /**
     * Negative Test
     * Mocked BasicStockService for testing the qetQuote method
     * and retrieving the stocks symbol
     */
    @Test
    public void getStockQuoteTestNegative() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(stockSymbol,
                fromDate, untilDate, interval));

        StockQuote stockQuote = stockServiceMock.getQuote(stockSymbol);
        assertFalse("stock symbol should not be TSLA", stockQuote.getSymbol().equals("TSLA"));
        assertFalse("from date should be 9/12/17", stockQuote.getFrom().equals(untilDate));
        assertFalse("until date should be 9/18/17", stockQuote.getUntil().equals(fromDate));
        assertFalse("interval should be OPEN_CLOSE",
                stockQuote.getIntervalEnum().equals(IntervalEnum.MIDDAY));
    }

    /**
     * Positive Test
     * Mocked BasicStockService for testing the getQuote method
     * and retrieving the stocks symbol, from date and until date
     */
    @Test
    public void getStockQuoteRangeTestPositive() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(stockSymbol,
                fromDate, untilDate, interval));
        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();
        stockQuoteList.add(stockServiceMock.getQuote(stockSymbol));
        assertTrue("stock symbol should be AAPL",
                stockQuoteList.get(0).getSymbol().equals("AAPL"));
        assertTrue("fom date should be 9/12/17",
                stockQuoteList.get(0).getFrom().equals(fromDate));
        assertTrue("until date should be 9/18/17",
                stockQuoteList.get(0).getUntil().equals(untilDate));
        assertTrue("interval should be OPEN_CLOSE",
                stockQuoteList.get(0).getIntervalEnum().equals(interval));
    }


    /**
     * Negative Test
     * Mocked BasicStockService for testing the getQuote method
     * when three arguments are provided, retrieves the stocks symbol,
     * beginning date and end date
     */
    @Test
    public void getStockQuoteRangeTestNegative() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class))).thenReturn(new StockQuote(stockSymbol,
                fromDate, untilDate, interval));
        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();
        stockQuoteList.add(stockServiceMock.getQuote(stockSymbol));
        assertFalse("stock symbol should not be TSLA",
                stockQuoteList.get(0).getSymbol().equals("TSLA"));
        assertFalse("fom date should not be 9/13/17",
                stockQuoteList.get(0).getFrom().equals(fromDate.getHour() + 24));
        assertFalse("until date should not be 9/19/17",
                stockQuoteList.get(0).getUntil().equals(untilDate.getHour() + 24));

    }

    /**
     * Positive Test
     * Mocked BasicStockService for testing the getQuote method
     * when three arguments are provided, retrieves the stocks symbol,
     * beginning date and end date
     *
     */
    @Test
    public void getStockQuoteIntervalTestPositive() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class),any(LocalDateTime.class),any(LocalDateTime.class),
                any(IntervalEnum.class))).thenReturn
                (new ArrayList<StockQuote>()); // I cannot get mockito to initialize a
        List<StockQuote> stockQuoteList =  //new list of StockQuotes with data
                 stockServiceMock.getQuote(stockSymbol, fromDate, untilDate, interval); //here
        stockQuoteList.add(new StockQuote(stockSymbol, fromDate, untilDate, interval)); //manually added
        assertTrue("stock symbol should be AAPL",
                stockQuoteList.get(0).getSymbol().equals("AAPL"));
        assertTrue("fom date should be 9/12/17",
                stockQuoteList.get(0).getFrom().equals(fromDate));
        assertTrue("until date should be 9/18/17",
                stockQuoteList.get(0).getUntil().equals(untilDate));
        assertTrue("interval should be OPEN_CLOSE",
                stockQuoteList.get(0).getIntervalEnum().equals(interval));

    }

    /**
     * Neagative Test
     * Mocked BasicStockService for testing the getQuote method
     * when three arguments are provided, retrieves the stocks symbol,
     * beginning date and end date
     *
     */
    @Test
    public void getStockQuoteIntervalTestNegative() {
        BasicStockService stockServiceMock = Mockito.mock(BasicStockService.class);
        when(stockServiceMock.getQuote(any(String.class),any(LocalDateTime.class),any(LocalDateTime.class),
                any(IntervalEnum.class))).thenReturn
                (new ArrayList<StockQuote>()); // I cannot get mockito to initialize a
        List<StockQuote> stockQuoteList =  //new list of StockQuotes with data
                stockServiceMock.getQuote(stockSymbol, fromDate, untilDate, interval); //here
        stockQuoteList.add(new StockQuote(stockSymbol, fromDate, untilDate, interval)); //manually added
        assertFalse("stock symbol should be AAPL",
                stockQuoteList.get(0).getSymbol().equals("AAP"));
        assertFalse("fom date should be 9/12/17",
                stockQuoteList.get(0).getFrom().equals(untilDate));
        assertFalse("until date should be 9/18/17",
                stockQuoteList.get(0).getUntil().equals(fromDate));
        assertFalse("interval should be OPEN_CLOSE",
                stockQuoteList.get(0).getIntervalEnum().equals(IntervalEnum.MIDDAY));

    }

}