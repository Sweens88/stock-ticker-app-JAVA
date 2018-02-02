package edu.uml.chrisweakley.servlets;


import edu.uml.chrisweakley.model.IntervalEnum;
import edu.uml.chrisweakley.model.StockQuote;
import edu.uml.chrisweakley.services.ServiceFactory;
import edu.uml.chrisweakley.services.StockService;
import edu.uml.chrisweakley.services.StockServiceException;
import edu.uml.chrisweakley.utils.TimeConversion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Example servlet from the apache tomcat distribution
 */
public class StockSearch extends HttpServlet {
    private static final String SYMBOL_PARAMETER_KEY = "symbol";
    private static final String FROM_PARAMETER_KEY = "from";
    private static final String TO_PARAMETER_KEY = "to";
    private static final String INTERVAL_PARAMETER_KEY = "interval";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String symbol = request.getParameter(SYMBOL_PARAMETER_KEY);
        String from = request.getParameter(FROM_PARAMETER_KEY);
        String to = request.getParameter(TO_PARAMETER_KEY);
        String interval = request.getParameter(INTERVAL_PARAMETER_KEY);

        LocalDateTime fromDate = TimeConversion.stringToLDT(from);
        LocalDateTime toDate = TimeConversion.stringToLDT(to);

        IntervalEnum intervalEnum;
        if (interval.equalsIgnoreCase("open close")) {
            intervalEnum = IntervalEnum.OPEN_CLOSE;
        } else if (interval.equalsIgnoreCase("midday")) {
            intervalEnum = IntervalEnum.MIDDAY;
        } else {
            intervalEnum = IntervalEnum.HOURLY;
        }

        StockService stockService = ServiceFactory.getStockServiceInstance();

        List<StockQuote> stockQuoteList = new ArrayList<>();
        try {
            stockQuoteList = stockService.getQuote(symbol, fromDate, toDate, intervalEnum);
        } catch (StockServiceException e) {
            System.out.println("not able to get quote in StockSearch");
        }

        HttpSession session = request.getSession();

        session.setAttribute("stockQuoteList", stockQuoteList);

        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher("/stockQuoteResults.jsp");
        dispatcher.forward(request, response);

    }
}