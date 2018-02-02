package edu.uml.chrisweakley.tests.xml;

import edu.uml.chrisweakley.xml.ObjectFactory;
import edu.uml.chrisweakley.xml.Stocks;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class ObjectFactoryTest {

    @Test
    public void ObjectFactoryTest() {
        ObjectFactory obj = new ObjectFactory();
        assertNotNull(obj);

    }

    @Test
    public void createStocksTest()  {
        ObjectFactory obj = new ObjectFactory();
        Stocks stocks = obj.createStocks();
        assertNotNull(stocks);
    }

    @Test
    public void createStocksStockTest()  {
        ObjectFactory obj = new ObjectFactory();
        Stocks.Stock stock = obj.createStocksStock();
        assertNotNull(stock);
    }


}