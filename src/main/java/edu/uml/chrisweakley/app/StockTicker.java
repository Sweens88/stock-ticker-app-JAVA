package edu.uml.chrisweakley.app;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.services.ServiceFactory;
import edu.uml.chrisweakley.services.StockService;
import edu.uml.chrisweakley.services.StockServiceException;
import edu.uml.chrisweakley.utils.TimeConversion;

import java.time.LocalDateTime;
import java.util.List;

/**
 * If XML data has to be persisted to the database, please run PersistDatabase in the
 * utils package before running this class.
 *
 * A class that accepts four arguments: <code>stockSymbol, fromDateString,
 * untilDateString, interval</code> and calls the <code>ServiceFactory</code> to
 * retrieve and implementation of a <code>StockService</code>.  A <code>StockQuote</code>
 * is returned and printed to the console.
 */
public class StockTicker {

    /**
     * Provide a single exit point for the application.
     *
     * @param code    the exit code 0 for success -1 (or any other negative
     *                value for failure)
     * @param message diagnostic message
     */
    private static void exit(int code, String message) {
        System.out.println(message);
        System.exit(code);
    }


    /**
     * If XML data has to be persisted to the database, please run PersistDatabase in the
     * utils package before running this class.
     *
     * Main Method that will output a list of stock quotes. Run this application by
     * providing the four required parameters as arguments to the command line
     * Example: java StockTicker AAPL 09/10/17 09/18/17 open_close
     * Executing this will print a String of stock quotes for the given date range
     * and interval to the console
     *
     * @param args - Four arguments are required to retrieve a stock quote, a stock
     *             symbol, begin date, end date and an interval of time representing
     *             the frequency of generated stock quotes.  Acceptable intervals are:
     *             open_close - two quotes per day, 9:30 AM and 4 PM
     *             hourly - 8 quotes per day, one each hour
     *             midday - one quote each day at 12:45 PM
     */
    public static void main(String[] args)  {

        // Validation of program arguments
        String argumentsRequired = "Please provide 4 arguments: stock symbol, from date," +
                " until date and the interval of time.\nEx: AAPL 9/10/17 9/18/17 hourly";
        if (args.length < 3) {
            exit(-1, "You only provided " + args.length + " arguments!\n" + argumentsRequired);
        }
        if (args.length > 4) {exit(-1, "You provided more than 4 arguments.\n" + argumentsRequired);
        }
        if ((args[0].length() < 1) || (args[0].length() > 4)) {
            exit(-1, "A stock ticker symbol must be one to four characters long");
        }

        try {
            //parsing and setting interval with error checking
            IntervalEnum intervalEnum = IntervalEnum.OPEN_CLOSE;
            if (args[3].equalsIgnoreCase("open_close")) {intervalEnum = IntervalEnum.OPEN_CLOSE;}
            else if (args[3].equalsIgnoreCase("midday")) {intervalEnum = IntervalEnum.MIDDAY;}
            else if (args[3].equalsIgnoreCase("hourly")) {intervalEnum = IntervalEnum.HOURLY;}
            else {exit(-1, "The fourth argument (interval) must be: open_close, daily or weekly");}



            //Set variables
            String stockSymbol = args[0];
            LocalDateTime fromDate = TimeConversion.stringToLDT(args[1]);
            LocalDateTime untilDate = TimeConversion.stringToLDT(args[2]);

            //get an instance of the StockService
            StockService stockService = ServiceFactory.getStockServiceInstance();

            //Single stock quote using the most day and stock price
            StockQuote stockQuoteSingleDay = stockService.getQuote(stockSymbol);
            System.out.println("The most recent stock quote available:");
            System.out.println(stockQuoteSingleDay.toString());
            System.out.println("---------------------");

            //Stock quote for a given range of dates
            List<StockQuote> stockQuoteRange = stockService.getQuote(stockSymbol, fromDate, untilDate);
            System.out.println("All available quotes within the entered range:");
            for (StockQuote quote : stockQuoteRange) {System.out.println(quote.toString());}
            System.out.println("---------------------");

            //Stock quote for a given range of dates and interval
            List<StockQuote> stockQuoteRangeWithInterval =
                    stockService.getQuote(stockSymbol, fromDate, untilDate, intervalEnum);
            System.out.println("All available quotes within the entered range and interval:");
            for (StockQuote quote : stockQuoteRangeWithInterval) {System.out.println(quote.toString());}

        } catch (StockServiceException e) {System.out.println(e);}

        exit(0, "\n---Normal Program Termination---");


    }
}
