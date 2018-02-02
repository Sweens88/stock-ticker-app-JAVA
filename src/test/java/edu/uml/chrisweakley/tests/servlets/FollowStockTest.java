package edu.uml.chrisweakley.tests.servlets;

import edu.uml.chrisweakley.servlets.FollowStock;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class FollowStockTest {

    @Test
    public void testFollowStockServlet() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        FollowStock followStock = mock(FollowStock.class);

        given(req.getParameter("symbol")).willReturn("aapl");
        given(req.getParameter("firstName")).willReturn("chris");
        given(req.getParameter("lastName")).willReturn("weakley");

        String symbol = req.getParameter("symbol");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        assertEquals("symbol should be aapl", symbol, "aapl");
        assertEquals("first name should be chris", firstName, "chris");
        assertEquals("last name should be weakley", lastName, "weakley");

    }

}