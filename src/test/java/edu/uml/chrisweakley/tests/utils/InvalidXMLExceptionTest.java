package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.utils.InvalidXMLException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidXMLExceptionTest {

    private final String message = "exception";
    final InvalidXMLException invalidXMLException =
            new InvalidXMLException("exception", new NullPointerException());



    @Test
    public void testPersonServiceException() {
        assertEquals(invalidXMLException.getMessage(), message);
    }

}