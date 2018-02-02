package edu.uml.chrisweakley.orm;

import javax.persistence.*;

/**
 * Models the Stock table
 */
@Entity
@Table (name = "stocks.stock")
public class Stock {

    private int id;
    private String stock;
    private Person person;


    /**
     * Primary Key - Unique ID for a particular row in the stock table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "symbol", nullable = false, length = 256)
    public String getSymbol() {
        return stock;
    }

    /**
     * Specify the stock symbol
     *
     * @param  symbol String value of the stocks symbol
     */
    public void setSymbol(String symbol) {
        this.stock = symbol;
    }

    /**
     * Set the unique ID for a particular row in the stock table.
     * This method should not be called by client code. The value is managed in internally.
     *
     * @param id a unique value.
     */
    public void setId(int id) {
        this.id = id;
    }



    /**
     * A method for testing equality between to stocks
     * @param o a stock to be compared to
     * @return true if equality exists, otherwise false
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {return false;}

        Stock stock = (Stock) o;

        if (id != stock.id) {return false;}
        return stock != null ? stock.equals(stock.stock) : stock.stock == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }

    /**
     * a toString method for converting stock data to a readable string
      * @return a string representation of a stocks id and symbol
     */
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", symbol='" + stock + '}';
    }


}
