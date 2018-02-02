package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.utils.DatabaseConnectionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseConnectionExceptionTest {


    private final String message = "exception";
    final DatabaseConnectionException databaseConnectionException =
            new DatabaseConnectionException("exception", new NullPointerException());



    @Test
    public void testPersonServiceException() {
        assertEquals(databaseConnectionException.getMessage(), message);
    }

}