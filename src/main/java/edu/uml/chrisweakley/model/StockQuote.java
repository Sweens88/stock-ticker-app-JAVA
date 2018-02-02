package edu.uml.chrisweakley.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Christopher Weakley 9/17/17
 * Java 3030 - Assignment Two
 *
 * A container class representing a stock quote for a given company
 */
@Immutable
public class StockQuote {

    /**
     * Class member variables
     */
    private String stockSymbol;
    private LocalDateTime from;
    private LocalDateTime until;
    private LocalDateTime date;
    private IntervalEnum intervalEnum;
    private BigDecimal price;

    /**
     * A constructor used for creating a new StockQuote of a given stockSymbol
     * in a given range Calendar <code>from</code> and <code>until</code> (inclusive)
     * with a given <code>interval</code> This will be used by a <code>StockService</code>
     * when generating a new stock quote.
     * @param stockSymbolValue the symbol of the stock
     * @param fromValue the date of the first stock quote
     * @param untilValue the date of the last stock quote
     * @param interval the number of stock quotes to get per day
     */
    public StockQuote(@NotNull String stockSymbolValue, @NotNull LocalDateTime fromValue,
                      @NotNull LocalDateTime untilValue, @NotNull IntervalEnum interval) {
        this.stockSymbol = stockSymbolValue;
        this.from = fromValue;
        this.until = untilValue;
        this.intervalEnum = interval;
    }

    /**
     * A constructor used for holding a single stock quote for a given symbol at
     * a given time
     * @param stockSymbolValue the symbol of the stock
     * @param dateValue the date of the stockquote
     * @param priceValue the price of the stock at the time
     */
    public StockQuote(@NotNull String stockSymbolValue, @NotNull LocalDateTime dateValue,
                      @NotNull BigDecimal priceValue) {
        this.stockSymbol = stockSymbolValue;
        this.date = dateValue;
        this.price = priceValue;
    }

    /**
     * A method for getting a stocks symbol
     * @return a String representation of a stock symbol
     */
    public String getSymbol() {
        return stockSymbol;
    }

    /**
     * A method for getting the first date (inclusive) in the range of
     * requested stock data
     * @return the first date in a range
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * A method for getting the last date (inclusive) in the range of
     * requested stock data
     * @return the last date in a range
     */
    public LocalDateTime getUntil() {
        return until;
    }

    /**
     * A method for getting the specified interval for an instance
     * of a StockQuote
     * @return the interval (OPEN_CLOSE, HOURLY, MIDDAY)
     */
    public IntervalEnum getIntervalEnum() {return intervalEnum;}

    /**
     * A method for getting the date of a singular stock quote
     * @return the date and time of the stock quote
     */
    public LocalDateTime getDate() {return date;
    }

    public void setDate(LocalDateTime dateValue) {
        this.date = dateValue;
    }

    public BigDecimal getPrice() {return price;
    }

    public void setPrice(BigDecimal priceValue) {
        this.price = priceValue;
    }


    /**
     * A toString method for formatting a stockquote     *
     * @return stockSymbol, date and price
     */
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
        return "StockQuote{" +
                "symbol='" + stockSymbol + '\'' +
                ", date=" + date.format(f) +
                ", price=" + price +
                '}';
    }
}
