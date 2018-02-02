package edu.uml.chrisweakley.tests.orm;


import edu.uml.chrisweakley.orm.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for the Person class
 */
public class PersonTest {

    static final String firstName = "Weakley";
    private  static final String lastName = "Chris";
    private static final String stock = "AAPL";


    /**
     * Testing helper method for generating Person test data
     *
     * @return a Person object that uses static constants for data.
     */
    public static Person createPerson() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    @Test
    public void testPersonGettersAndSettersPositive() {
        Person person = createPerson();
        int id = 10;
        person.setId(id);
        assertEquals("first name matches", firstName, person.getFirstName());
        assertEquals("last name matches", lastName, person.getLastName());
        assertEquals("id matches", id, person.getId());
    }

    @Test
    public void testPersonGettersAndSettersNegative() {
        Person person = createPerson();
        int id = 10;
        int wrongId = 11;
        String wrongFirst = "Todd";
        String wrongLast = "Hays";
        person.setId(id);
        assertFalse("first name matches", wrongFirst.equals(person.getFirstName()));
        assertFalse("last name matches", wrongLast.equals(person.getLastName()));
        assertFalse("id matches", wrongId == person.getId());
    }

    @Test
    public void testEqualsMethod() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        Person person2 = person;
        Person person3 = new Person();
        assertTrue("should be equal", person2.equals(person));
        assertFalse("should not be equal", person.equals(person3));
    }

    @Test
    public void testHashCode() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        Person person2 = person;
        assertEquals("hash codes should be equal", person.hashCode(), person2.hashCode());
    }

    @Test
    public void testToString() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setSymbol(stock);
        assertTrue("should return string", person.toString() != null);
    }

}
