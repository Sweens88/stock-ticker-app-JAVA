package edu.uml.chrisweakley.services;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.utils.DatabaseConnectionException;
import edu.uml.chrisweakley.utils.DatabaseUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {

    /**
     * Return the most recent price available for a share of stock for the given symbol.
     * If there is no data available an exception will be thrown back to the caller to
     * catch.  In this case the caught exception will only print a message and not interupt
     * the flow of the program.
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. AAPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception. If this happens,
     *                               trying the service may work, depending on the actual cause of
     *                               the error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        StockQuote stockQuote = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "'" +
                    "and time in ( SELECT MAX(time)from quotes group by symbol)";

            ResultSet resultSet = statement.executeQuery(queryString);
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuote = new StockQuote(symbolValue, time, price);
            }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuote == null) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }
        return stockQuote;
    }

    /**
     * Get a historical list of stock quotes for the provided symbol.
     * If there is no data available an exception will be thrown back to the caller to
     * catch.  In this case the caught exception will only print a message and not interupt
     * the flow of the program.
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances within the date range
     * @throws   StockServiceException if using the service generates an exception. If this
     *                                 happens, trying the service may work, depending on the
     *                                 actual cause of the error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until) throws StockServiceException {
        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            until = until.plusDays(1);
            String queryString = "select * from quotes where symbol = '" + symbol + "'" +
                                 "and time BETWEEN '" + from + "' and '" + until + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(symbolValue, time, price));
                }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for: " + symbol);
        }
        return stockQuotes;
    }

    /**
     * Get a historical list of stock quotes for the provided symbol at the specified interval.
     * If there is no data available an exception will be thrown back to the caller to
     * catch.  In this case the caught exception will only print a message and not interupt
     * the flow of the program.
     *
     * @param symbol ​the stock symbol to search for
     * @param from ​the date of the first stock quote
     * @param until ​the date of the last stock quote
     * @param interval ​­ the number of StockQuotes to get. E.g. if IntervalEnum.MIDDAY was
     * specified, only one stock quote would be returned per day
     * @return a list of stock quotes within the range at the given interval
     * @throws StockServiceException if using the service generates an exception. If this
     *                               happens, trying the service may work, depending on the
     *                               actual cause of the error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, LocalDateTime from, LocalDateTime until, IntervalEnum interval)
            throws StockServiceException {
        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            until = until.plusDays(1);
            String queryString = "select * from quotes where symbol = '" + symbol + "'" +
                    "and time BETWEEN '" + from + "' and '" + until + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                BigDecimal price = resultSet.getBigDecimal("price");

                if (interval == IntervalEnum.OPEN_CLOSE) {
                    if (time.getHour() == 9 || ((time.getHour() == 16) && time.getMinute() == 30)) {
                        stockQuotes.add(new StockQuote(symbolValue, time, price));
                    }
                }else if (interval == IntervalEnum.MIDDAY) {
                    if (time.getHour() == 12 && time.getMinute() == 45) {
                        stockQuotes.add(new StockQuote(symbolValue, time, price));
                    }
                }else if (interval == IntervalEnum.HOURLY) {
                    if (time.getHour() == 9 || time.getHour() == 10 || time.getHour() == 11 ||
                          time.getHour() == 12 || time.getHour() == 13 || time.getHour() == 14 ||
                          time.getHour() == 15 || time.getHour() == 16 || (time.getMinute() == 16
                          && time.getMinute() == 30)) {

                        stockQuotes.add(new StockQuote(symbolValue, time, price));
                    }
                }

            }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("No data available for " + symbol);
        }
        return stockQuotes;
    }


}
