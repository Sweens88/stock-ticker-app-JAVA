package edu.uml.chrisweakley.tests.servlets;

import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.servlets.StockSearch;
import edu.uml.chrisweakley.utils.TimeConversion;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class StockSearchTest {

    @Test
    public void testStockSearchServlet() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        StockSearch stockSearch = mock(StockSearch.class);

        given(req.getParameter("symbol")).willReturn("goog");
        given(req.getParameter("from")).willReturn("08/20/2004");
        given(req.getParameter("to")).willReturn("08/23/2004");
        given(req.getParameter("interval")).willReturn("open close");

        String symbol = req.getParameter("symbol");
        LocalDateTime fromDate = TimeConversion.stringToLDT(req.getParameter("from"));
        LocalDateTime toDate = TimeConversion.stringToLDT(req.getParameter("to"));
        IntervalEnum intervalEnum;

        if (req.getParameter("interval").equalsIgnoreCase("open close")) {
            intervalEnum = IntervalEnum.OPEN_CLOSE;
        } else if (req.getParameter("interval").equalsIgnoreCase("midday")) {
            intervalEnum = IntervalEnum.MIDDAY;
        } else {
            intervalEnum = IntervalEnum.HOURLY;
        }
        StockQuote stockQuote = new StockQuote(symbol, fromDate, toDate, intervalEnum);



        assertEquals("symbol should be goog", stockQuote.getSymbol(), "goog");
        assertEquals("from should be 08/20/2004", stockQuote.getFrom(),
                TimeConversion.stringToLDT("08/20/2004"));
        assertEquals("to should be 08/23/2004", stockQuote.getUntil(),
                TimeConversion.stringToLDT("08/23/2004"));
        assertEquals("interval should be open close", stockQuote.getIntervalEnum(), IntervalEnum.OPEN_CLOSE);

    }

}