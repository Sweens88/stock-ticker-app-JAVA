package edu.uml.chrisweakley.orm;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Models the quotes table
 */

@Entity
@Table (name= "quotes")
public class DBStockQuote {
    private int id;
    private Timestamp time;
    private BigDecimal price;
    private String stockSymbol;

    /**
     * Constructor for creating a new database stock quote
     * @param time Timestamp representation of the quote time
     * @param price BigDecimal price of the stock quote
     * @param stockSymbol 2 to 4 character String representing the stock's symbol
     */
    public DBStockQuote(Timestamp time, BigDecimal price, String stockSymbol) {
        this.time = time;
        this.price = price;
        this.stockSymbol = stockSymbol;
    }


    /**
     * Primary Key - Unique ID for each person within the person table
     * @return an integer value ID
     */
    @Id
    @Column(name = "id",  nullable = false)
    public int getId() {
        return id;
    }

    /**
     * Primary Key - Unique ID for each person within the person table
     * @param id an integer value ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Time - the time at which the stock quote occurred
     * @return the Timestamp time of the quote
     */
    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    /**
     * Time - the time at which the stock quote occurred
     * @param time
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * Price - the price of the stock at the time of the quote
     * @return BigDecimal price of the stock
     */
    @Basic
    @Digits(integer=8, fraction = 8)
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Price - the price of the stock at the time of the quote
     * @param price - the price to be set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "symbol", nullable = false, length = 256)
    public String getSymbol() {
        return stockSymbol;
    }

    /**
     * Specify the stock symbol
     *
     * @param  symbol String value of the stocks symbol
     */
    public void setSymbol(String symbol) {
        this.stockSymbol = symbol;
    }

    /**
     * A method for comparing two quotes for equality
     * @param o a Stock quote
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBStockQuote quote = (DBStockQuote) o;

        if (id != quote.id) return false;
        if (price != quote.price) return false;
        return time != null ? time.equals(quote.time) : quote.time == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + price.hashCode();
        return result;
    }

    /**
     * Too sting method for a DBStockQuote
     * @return a string representaion of the stock quote
     */
    @Override
    public String toString() {
        return "Stock Quote{"+
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", price='" + price + "\'" +
                ", time='" + time + "\'}";
    }


}
