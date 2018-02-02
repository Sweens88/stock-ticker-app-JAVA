package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.utils.TimeConversion;
import edu.uml.chrisweakley.xml.Stocks;
import org.junit.Test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TimeConversionTest {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private LocalDate from = LocalDate.parse("10/25/2017", df);
    private LocalDateTime fromDate = LocalDateTime.of(from, LocalTime.MIDNIGHT);

    Stocks stocks;


    public static Timestamp convertTime(Stocks.Stock stock) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(stock.getTime());
        Timestamp time = new java.sql.Timestamp(parsedDate.getTime());
        return time;
    }


    @Test
    public void testLocalDateTimeToCalendar() throws Exception {
        Calendar date = TimeConversion.localDateTimeToCalendar(fromDate);
        assertEquals("Year should be 2017", date.get(1), 2017);
        assertEquals("Month should be 9", date.get(2), 9);
        assertEquals("Day should be 25", date.get(5), 25);
    }

    @Test
    public void testStringToLocalDateTime1() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("10/25/17");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 9", ldt.getMonthValue(), 10);
        assertEquals("Day should be 25", ldt.getDayOfMonth(), 25);
    }

    @Test
    public void testStringToLocalDateTime2() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("10/25/2017");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 9", ldt.getMonthValue(), 10);
        assertEquals("Day should be 25", ldt.getDayOfMonth(), 25);
    }

    @Test
    public void testStringToLocalDateTime3() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("10/2/17");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 9", ldt.getMonthValue(), 10);
        assertEquals("Day should be 2", ldt.getDayOfMonth(), 2);
    }

    @Test
    public void testStringToLocalDateTime4() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("10/2/2017");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 9", ldt.getMonthValue(), 10);
        assertEquals("Day should be 2", ldt.getDayOfMonth(), 2);
    }

    @Test
    public void testStringToLocalDateTime5() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("8/02/17");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 8", ldt.getMonthValue(), 8);
        assertEquals("Day should be 2", ldt.getDayOfMonth(), 2);
    }

    @Test
    public void testStringToLocalDateTime6() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("8/2/2017");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 8", ldt.getMonthValue(), 8);
        assertEquals("Day should be 2", ldt.getDayOfMonth(), 2);
    }

    @Test
    public void testStringToLocalDateTime7() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("8/2/17");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 8", ldt.getMonthValue(), 8);
        assertEquals("Day should be 2", ldt.getDayOfMonth(), 2);
    }

    @Test
    public void testStringToLocalDateTime8() throws Exception {
        LocalDateTime ldt = TimeConversion.stringToLDT("8/25/2017");
        assertEquals("Year should be 2017", ldt.getYear(), 2017);
        assertEquals("Month should be 8", ldt.getMonthValue(), 8);
        assertEquals("Day should be 25", ldt.getDayOfMonth(), 25);
    }

    @Test
    public void testConvertTime() {
        final String FILE_NAME = "src\\main\\resources\\xml\\stock_info.xml";
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Stocks.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Stocks stocks = (Stocks) unmarshaller.unmarshal(new File(FILE_NAME));
            List<Stocks.Stock> stockList = stocks.getStock();
            assertTrue("creates a timestamp object",
                    TimeConversion.convertTime(stockList.get(0)) instanceof java.sql.Timestamp);

        } catch (JAXBException | ParseException e) {
            System.out.println(e);
        }

    }

}