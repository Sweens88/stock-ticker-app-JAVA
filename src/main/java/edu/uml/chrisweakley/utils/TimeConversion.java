package edu.uml.chrisweakley.utils;

import edu.uml.chrisweakley.xml.Stocks;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeConversion {

    /**
     * Coverts a LocalDateTime to a Calendar object for use by the YahooFinance API
     * @param date LocalDateTime to be converted
     * @return a Calendar object
     */
    public static Calendar localDateTimeToCalendar(LocalDateTime date) {
        Calendar outDate = Calendar.getInstance();
        outDate.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        return outDate;
    }

    /**
     * Converts a time String from a XML stock object to a Timestamp to create
     * a DBStockQuote object
     * @param stock a XML stock object
     * @return a TimeStamp representation of the stock quotes time
     * @throws ParseException
     */
    public static Timestamp convertTime(Stocks.Stock stock) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(stock.getTime());
        Timestamp time = new java.sql.Timestamp(parsedDate.getTime());
        return time;
    }

    /**
     * Converts a string patter MM/dd/YYYY to a LocalDateTime object
     * @param dateString string to be converted
     * @return a LocalDateTime object
     */
    public static LocalDateTime stringToLDT(String dateString) {
        DateTimeFormatter df;
        String[] date = dateString.split("/");
        if        (date[0].length()==(2) && date[1].length()==(2) && date[2].length()==(2)) {
            df = DateTimeFormatter.ofPattern("MM/dd/yy");
        } else if (date[0].length()==(2) && date[1].length()==(2) && date[2].length()==(4)) {
            df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        } else if (date[0].length()==(2) && date[1].length()==(1) && date[2].length()==(2)) {
            df = DateTimeFormatter.ofPattern("MM/d/yy");
        } else if (date[0].length()==(2) && date[1].length()==(1) && date[2].length()==(4)) {
            df = DateTimeFormatter.ofPattern("MM/d/yyyy");
        } else if (date[0].length()==(1) && date[1].length()==(2) && date[2].length()==(2)) {
            df = DateTimeFormatter.ofPattern("M/dd/yy");
        } else if (date[0].length()==(1) && date[1].length()==(2) && date[2].length()==(4)) {
            df = DateTimeFormatter.ofPattern("M/dd/yyyy");
        } else if (date[0].length()==(1) && date[1].length()==(1) && date[2].length()==(4)) {
            df = DateTimeFormatter.ofPattern("M/d/yyyy");
        } else {df = DateTimeFormatter.ofPattern("M/d/yy");}

        LocalDate dateLD = LocalDate.parse(dateString, df);
        LocalDateTime dateLDT = LocalDateTime.of(dateLD, LocalTime.MIDNIGHT);
        return dateLDT;
    }


}
