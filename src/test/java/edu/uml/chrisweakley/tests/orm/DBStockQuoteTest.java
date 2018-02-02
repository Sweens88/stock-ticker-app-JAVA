package edu.uml.chrisweakley.tests.orm;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.orm.DBStockQuote;
import edu.uml.chrisweakley.tests.model.AbstractDatabaseInit;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBStockQuoteTest extends AbstractDatabaseInit {

    private int id;
    private String stockSymbol = "AAPL";
    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private IntervalEnum interval = IntervalEnum.OPEN_CLOSE;
    private BigDecimal price = BigDecimal.valueOf(118);
    private DBStockQuote dbStockQuote = new DBStockQuote(time, price, stockSymbol);


    @Test
    public void getId() throws Exception {
        dbStockQuote.setId(1);
        assertEquals("ID should be 2", dbStockQuote.getId(), 1);
    }

    @Test
    public void setId() throws Exception {
        dbStockQuote.setId(2);
        assertEquals("ID should be 2", dbStockQuote.getId(), 2);

    }

    @Test
    public void getTime() throws Exception {
        assertEquals("Time should be equal", dbStockQuote.getTime(), time);
    }

    @Test
    public void setTime() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp newTime = new Timestamp(time);
        dbStockQuote.setTime(newTime);
        assertEquals("Time should be equal", dbStockQuote.getTime(), newTime);

    }

    @Test
    public void getPrice() throws Exception {
        assertEquals("Price should be equal", dbStockQuote.getPrice(), price);
    }

    @Test
    public void setPrice() throws Exception {
        BigDecimal newPrice = BigDecimal.valueOf(120);
        dbStockQuote.setPrice(newPrice);
        assertEquals("Price should be equal", dbStockQuote.getPrice(), newPrice);
    }

    @Test
    public void getSymbol() throws Exception {
        assertEquals("Symbol should be equal", dbStockQuote.getSymbol(), stockSymbol);
    }

    @Test
    public void setSymbol() throws Exception {
        dbStockQuote.setSymbol("GOOG");
        assertEquals("Symbol should be equal", dbStockQuote.getSymbol(), "GOOG");
    }

    @Test
    public void testEquals() {
        assertTrue("this should be the same", dbStockQuote.equals(dbStockQuote));
    }

    @Test
    public void testHashCode() {
        int hash1 = dbStockQuote.hashCode();
        DBStockQuote dbStockQuote2 = new DBStockQuote(time, price, stockSymbol);
        int hash2 = dbStockQuote2.hashCode();
        assertTrue("hash codes should be equal", hash1 == hash2);

    }

}