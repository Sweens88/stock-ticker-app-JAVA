package edu.uml.chrisweakley.tests.services;

import edu.uml.chrisweakley.services.PersonServiceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonServiceExceptionTest {

    private final String message = "exception";
    private final PersonServiceException personServiceException =
            new PersonServiceException("exception", new NullPointerException());

    @Test
    public void testPersonServiceException() {
        assertEquals(personServiceException.getMessage(), message);
    }
}
