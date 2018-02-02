package edu.uml.chrisweakley.services;

import edu.uml.chrisweakley.orm.Person;
import edu.uml.chrisweakley.orm.Stock;

import java.util.List;

public interface PersonService {

    /**
     * A getter method for a person
     * @return a list of person objects
     */
    List<Person> getPerson();

    /**
     * A method for updating or adding a person to the database
     * @param person the person to be added or updated
     */
    void addOrUpdatePerson(Person person);

    /**
     * A method for retrieving the list of stocks that a person is interested in
     * @param person a person in the database
     * @return a list of stocks
     */
    List<String> getStockList(Person person);



    void addOrUpdateStock(Stock stock);
}
