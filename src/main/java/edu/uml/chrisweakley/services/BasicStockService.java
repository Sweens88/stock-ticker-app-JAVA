package edu.uml.chrisweakley.services;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;

import java.time.LocalDate;
import java.time.LocalDateTime;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Christopher Weakley 9/17/17
 * Java 3030 - Assignment Two
 *
 * An implementation of StockService which will be modified based on our
 * preferred service for retrieving stock quotes (database, web service, flat file)
 */
public class BasicStockService implements StockService {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yy");
    private LocalDate from = LocalDate.parse("09/10/17", df);
    private LocalDate until = LocalDate.parse("09/18/17", df);

    private LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIDNIGHT);
    private LocalDateTime untilDate = LocalDateTime.of(until, LocalTime.MIDNIGHT);
    private IntervalEnum interval = IntervalEnum.OPEN_CLOSE;


    /**
     * A method for getting a stock quote for the current date and price when
     * only the stock symbol is provided
     * @param symbol the stock symbol of the company you want a quote for.
     * e.g. APPL for APPLE
     * @return <CODE>StockQuote</CODE> the quote for the corresponding company
     */
    @Override
    public StockQuote getQuote(String symbol) {
        StockQuote stockQuote = new StockQuote(symbol, fromDate, untilDate, interval);
        return stockQuote;
    }

    /**
     * A method for getting a range of stock quotes when a date range and stock
     * symbol are provided
     * @param symbol the stock symbol to search for
     * @param symbol the stock symbol to search for
     * @param from the date of the first stock quote
     * @param until the date of the last stock quote
     * @return a list of stock quotes, one for each day in the date range
     */
    @Override
    public List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until) {
        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();
        StockQuote stockQuote = new StockQuote(symbol, from, until, interval);
        stockQuoteList.add(stockQuote);
        return stockQuoteList;
    }

    /**
     * A method for getting a range of stock quotes when a date range, stock
     * symbol and interval are provided
     * @param symbol ​the stock symbol to search for
     * @param from ​the date of the first stock quote
     * @param until ​the date of the last stock quote
     * @param interval ​­ the number of StockQuotes to get. E.g. if IntervalEnum.MIDDAY was
     * specified, only one stock quote would be returned per day
     * @return a list of stock quotes for each day with at the provided interval
     */
    @Override
    public List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until,
                                     IntervalEnum interval) {
        List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();
        StockQuote stockQuote = new StockQuote(symbol, from, until, interval);
        stockQuoteList.add(stockQuote);
        return stockQuoteList;
    }

}
