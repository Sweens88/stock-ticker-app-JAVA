package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.xml.Stocks;
import org.junit.Test;

import java.math.BigDecimal;

import static edu.uml.chrisweakley.utils.PersistDatabase.convertPrice;
import static junit.framework.TestCase.assertTrue;

public class PersistDatabaseTest {

    Stocks.Stock stock = new Stocks.Stock();
    BigDecimal price = BigDecimal.valueOf(100);

    @Test
    public void testConvertPrice() {
        stock.setPrice("100");
        BigDecimal priceConverted = convertPrice(stock);
        assertTrue("prices should be 100", priceConverted.equals(price));
    }

}