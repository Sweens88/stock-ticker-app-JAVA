package edu.uml.chrisweakley.servlets;

import edu.uml.chrisweakley.orm.Person;
import edu.uml.chrisweakley.services.PersonService;
import edu.uml.chrisweakley.services.ServiceFactory;
import edu.uml.chrisweakley.utils.DatabaseConnectionException;
import edu.uml.chrisweakley.utils.DatabaseUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FollowStock extends HttpServlet {

    private static final String SYMBOL_PARAMETER_KEY = "symbol";
    private static final String FIRSTNAME_PARAMETER_KEY = "firstName";
    private static final String LASTNAME_PARAMETER_KEY = "lastName";


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String symbol = request.getParameter(SYMBOL_PARAMETER_KEY).toUpperCase();
        String firstName = request.getParameter(FIRSTNAME_PARAMETER_KEY);
        String lastName = request.getParameter(LASTNAME_PARAMETER_KEY);

        HttpSession session = request.getSession();

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setSymbol(symbol);
        PersonService personService = ServiceFactory.getPersonServiceInstance();

        Boolean alreadyFollow = false;
        //Fill array with a unique list of stocks that are being followed
        ArrayList<String> stockList = new ArrayList<>();
        try{
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select symbol from person where first_name = '" + firstName + "'" +
                    "and last_name = '" + lastName + "'";
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String stockSymbol = resultSet.getString("symbol");
                if (!stockList.contains(stockSymbol)) {
                    stockList.add(stockSymbol);
                    if (stockSymbol.equalsIgnoreCase(symbol)) {
                        alreadyFollow = true;
                    }
                }
            }
        } catch (DatabaseConnectionException | SQLException e) {
            stockList.add("There was an error connecting to the database.");
        }

        if (alreadyFollow){
            symbol = "You are already following " + symbol + "!";
        } else {
            symbol = symbol + " was successfully followed.";
        }

        personService.addOrUpdatePerson(person);
        session.setAttribute("symbol", symbol);
        session.setAttribute("stockList", stockList);

        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/followStockResults.jsp");
        dispatcher.forward(request, response);


    }
}
