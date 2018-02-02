package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.utils.DatabaseInitializationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseInitializationExceptionTest {

    private final String message = "exception";
    final DatabaseInitializationException databaseInitializationException =
            new DatabaseInitializationException("exception", new NullPointerException());



    @Test
    public void testPersonServiceException() {
        assertEquals(databaseInitializationException.getMessage(), message);
    }

}