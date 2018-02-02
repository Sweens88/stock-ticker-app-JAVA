package edu.uml.chrisweakley.services;
import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;

import java.time.LocalDateTime;


import java.util.List;

/**
 * Christopher Weakley 9/17/17
 * Java 3030 - Assignment Two
 *
 * An interface for our various methods of retrieving stock quotes
 */
public interface StockService {
    /**
     * Returns the current price for a share of stock for the given symbol
     * @param symbol the stock symbol of the company you want a quote for.
     * e.g. APPL for APPLE
     * @return a <CODE>StockQuote </CODE> instance
     */
    StockQuote getQuote(String symbol) throws StockServiceException;

    /**
     * Gets a historical list of stock quotes for the provided symbol
     * @param symbol the stock symbol to search for
     * @param from the date of the first stock quote
     * @param until the date of the last stock quote
     * @return a list of StockQuote instances.
     * One for each day in the range specified.
     */
    List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until) throws StockServiceException;

    /**
     *
     * @param symbol ​the stock symbol to search for
     * @param from ​the date of the first stock quote
     * @param until ​the date of the last stock quote
     * @param interval ​­ the number of StockQuotes to get. E.g. if IntervalEnum.MIDDAY was
     * specified, only one stock quote would be returned per day
     * @return a list of StockQuote instances, one for each day within the range at the
     *         specified intervals
     */
    List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until,
                              IntervalEnum interval) throws StockServiceException;
}