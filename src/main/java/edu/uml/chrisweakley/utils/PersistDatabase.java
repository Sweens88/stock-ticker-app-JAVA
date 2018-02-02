package edu.uml.chrisweakley.utils;

import edu.uml.chrisweakley.orm.DBStockQuote;
import edu.uml.chrisweakley.services.StockServiceException;
import edu.uml.chrisweakley.xml.Stocks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersistDatabase {

    /**
     * Coverts a price String from a XML stock object to a BigDecimal to create
     * a DBStockQuote object
     * @param stock a XML stock object
     * @return a BigDecimal representation of the stock price
     */
    public static BigDecimal convertPrice(Stocks.Stock stock) {
        BigDecimal price = new BigDecimal(stock.getPrice());
        return price;
    }

    /**
     * A main method for persisting XML to JAXB database objects to our stocks
     * Database.  This only has to be ran when new XML data is added to the project.
     * @param args
     * @throws StockServiceException
     */
    public static void main(String[] args) throws StockServiceException {

        //Location of stock_info.xml
        final String FILE_NAME = "src\\main\\resources\\xml\\stock_info.xml";

        try {
            //Convert XML to JAXB object
            JAXBContext jaxbContext = JAXBContext.newInstance(Stocks.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Stocks stocks = (Stocks) unmarshaller.unmarshal(new File(FILE_NAME));

           //Create list of ORN stock quotes from our list of JAXB quotes
            List<Stocks.Stock> stockList = stocks.getStock();
            List<DBStockQuote> quotes = new ArrayList<>(stockList.size());
            for (Stocks.Stock stock : stockList) {
                try {
                    quotes.add(new DBStockQuote(TimeConversion.convertTime(stock), convertPrice(stock), stock.getSymbol()));
                } catch (ParseException e) {
                    System.out.println("Was not able to parse xml time to timestamp.");
                } catch (Exception e) {
                    System.out.println("Error when adding quote to list.");
                }

            }

            Connection connection = DatabaseUtils.getConnection();
            String query = "INSERT INTO stocks.quotes (symbol, time, price) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            for (DBStockQuote quote : quotes) {
                ps.setString(1, quote.getSymbol());
                ps.setTimestamp(2, quote.getTime());
                ps.setBigDecimal(3, quote.getPrice());
                ps.addBatch();
            }
            DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
            ps.executeBatch();

            if (quotes == null) {
                throw new StockServiceException("There is no stock data available");
            }
            Statement statement = connection.createStatement();

            String queryString = "select * from quotes";
            ResultSet resultSet = statement.executeQuery(queryString);
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String symbolValue = resultSet.getString("symbol");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                BigDecimal price = resultSet.getBigDecimal("price");
                System.out.println(id + " " + symbolValue + " " + time + " " + price);
            }


        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        } catch (DatabaseInitializationException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            System.out.println(e);
        }

    }
}
