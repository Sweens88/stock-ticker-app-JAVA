package edu.uml.chrisweakley.tests.services;


import edu.uml.chrisweakley.orm.Person;
import edu.uml.chrisweakley.services.PersonService;
import edu.uml.chrisweakley.services.PersonServiceException;
import edu.uml.chrisweakley.services.ServiceFactory;
import edu.uml.chrisweakley.tests.orm.PersonTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabasePersonServiceTest extends AbstractDatabaseInit {
    private PersonService personService = ServiceFactory.getPersonServiceInstance();
    private String firstName = "Chris";
    private String lastName= "Weakley";

    @Test
    public void testGetInstance() {
        assertNotNull("Make sure activitiesService is available", personService);
    }

    @Test
    public void testGetPerson() throws Exception {
        List<Person> personList = personService.getPerson();
        assertFalse("Make sure we get some Person objects from service", personList.isEmpty());

    }


    @Test
    public void testAddOrUpdatePerson()throws PersonServiceException {
        Person newPerson = PersonTest.createPerson();
        personService.addOrUpdatePerson(newPerson);
        List<Person> personList = personService.getPerson();
        boolean found = false;
        for (Person person : personList) {
            if (person.getLastName().equals(lastName)
                && person.getFirstName().equals(firstName)) {
                found = true;
                break;
            }
        }
        assertTrue("Found the person we added", found);
    }

    @Test
    public void testGetStocksByPerson() throws PersonServiceException {
        Person person = PersonTest.createPerson();
        String stock = "AAPL";
        person.setSymbol(stock);
        personService.addOrUpdatePerson(person);
        List<String> stockList = personService.getStockList(person);
        for (String stocks : stockList) {
            boolean removed = stockList.remove(stocks);
            assertTrue("Verify that the stock was found on the list", removed);
        }
        // if  stockList is empty then we know the lists matched.
        assertTrue("Verify the list of returned stocks match what was expected ", stockList.isEmpty());

    }

}