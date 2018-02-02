package edu.uml.chrisweakley.tests.services;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.services.DatabaseStockService;
import edu.uml.chrisweakley.services.StockService;
import edu.uml.chrisweakley.services.StockServiceException;
import edu.uml.chrisweakley.utils.DatabaseInitializationException;
import edu.uml.chrisweakley.utils.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseStockServiceTest extends AbstractDatabaseInit {

    private DatabaseStockService testDatabase;
    private String symbol = "AAPL";
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private LocalDate from = LocalDate.parse("09/10/1990", df);
    private LocalDate until = LocalDate.parse("09/18/2017", df);
    private LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIDNIGHT);
    private LocalDateTime untilDate = LocalDateTime.of(until, LocalTime.MIDNIGHT);
    private IntervalEnum interval = IntervalEnum.OPEN_CLOSE;
    private IntervalEnum interval2 = IntervalEnum.HOURLY;
    private IntervalEnum interval3 = IntervalEnum.MIDDAY;
    private BigDecimal price = BigDecimal.valueOf(118);
    private StockQuote stockQuote;
    private List<StockQuote> stockQuotes = new ArrayList<StockQuote>();
    private Statement statement;
    private StockService stockService = new DatabaseStockService();



    @Before
    public void setUp() throws DatabaseInitializationException {
        super.setUp();

        try {
            Connection connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuote = new StockQuote(symbolValue, time, price);
                stockQuotes.add(stockQuote);
            }
        } catch (Exception e) {
            System.out.println("Could not get info from database.");
        }

    }

    @Test
    public void testGetQuotePositive() throws StockServiceException {
        StockQuote stockQuote = stockService.getQuote("AAPL");
        assertTrue("Stock symbol should be AAPL", stockQuote.getSymbol().equals("AAPL"));
        }

    @Test
    public void testGetQuoteWithDatePositive() throws StockServiceException {

        List<StockQuote> stockQuotes = stockService.getQuote(symbol, fromDate, untilDate);
        assertTrue("Symbols should match", stockQuotes.get(0).getSymbol().equals(symbol));
    }

    @Test
    public void testGetQuoteWithDateAndIntervalPostitive() throws StockServiceException {
        List<StockQuote> stockQuotes = stockService.getQuote(symbol, fromDate, untilDate, interval);
        assertTrue("Symbols should match", stockQuotes.get(0).getSymbol().equals(symbol));
        //assertTrue(stockQuotes.get(0).getIntervalEnum().equals(interval));
    }

    @Test
    public void testIntervals() throws StockServiceException {
        List<StockQuote> stockQuotes2 = stockService.getQuote(symbol, fromDate, untilDate, interval2);
        List<StockQuote> stockQuotes3 = stockService.getQuote(symbol, fromDate, untilDate, interval3);
        assertTrue("symbol should match", stockQuotes3.get(0).getSymbol().equals(symbol));
        assertTrue("symbol should match", stockQuotes2.get(0).getSymbol().equals(symbol));

    }

    @Test
    public void getQuoteTestSymbolOnlyPositive() {
        assertNotNull("Verify we can get a stock quote from the db", stockQuote);
        assertEquals("Make sure the symbols match", symbol, stockQuote.getSymbol());
    }

    @Test
    public void getQuoteTestSymbolOnlyNegative() throws Exception {
        assertFalse("The symbols shouldn't match",
                stockQuote.getSymbol().equalsIgnoreCase("goog"));
    }


    @Test
    public void getQuoteTestSymbolAndDatesPositive() throws Exception {
        assertNotNull("Verify we can get data from a list of stockquotes", stockQuotes);
        assertNotNull("Verify date information is not null", stockQuotes.get(0).getDate());
    }

    @Test
    public void getQuoteTestSymbolAndDatesNegative() throws Exception {
        assertFalse("Dates should not match", stockQuotes.get(0).equals(fromDate));

    }

    @Test
    public void getQuoteTestSymbolDatesEnumPositive() throws Exception {
        assertNotNull("Prices should match", stockQuote.getPrice());

    }

    @Test
    public void getQuoteTestSymbolDatesEnumNegative() throws Exception {
        assertFalse("Prices should not match", stockQuote.getPrice().equals(price));
    }

    @Test
    public void getQuoteResultCountTestPositive() throws Exception {
        assertTrue("there are 11 entries total", stockQuotes.size() == 11);
    }

    @Test
    public void getQuoteResultCountTestNegative() throws Exception {
        assertFalse("there are 11 entries total", stockQuotes.size() == 12);
    }




}