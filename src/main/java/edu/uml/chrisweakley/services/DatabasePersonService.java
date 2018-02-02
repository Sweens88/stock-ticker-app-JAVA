package edu.uml.chrisweakley.services;

import edu.uml.chrisweakley.orm.Person;
import edu.uml.chrisweakley.orm.Stock;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static edu.uml.chrisweakley.utils.DatabaseUtils.getSessionFactory;

public class DatabasePersonService implements PersonService {

    /**
     * Get a list of all people
     *
     * @return a list of Person instances
     */
    @Override
    public List<Person> getPerson() {
        Session session = getSessionFactory().openSession();
        List<Person> returnValue = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            returnValue = criteria.list();

        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return returnValue;
    }

    /**
     * Add a new person or update an existing Person's data
     *
     * @param person a person object to either update or create
     */
    @Override
    public void addOrUpdatePerson(Person person) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    /**
     * Add a new person or update an existing Person's data
     *
     * @param stock a person object to either update or create
     */
    @Override
    public void addOrUpdateStock(Stock stock) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(stock);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    /**
     * Get a list of all a person's stocks.
     *
     * @param person the person
     * @return a list of stock symbols
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getStockList(Person person) {
        Session session =  getSessionFactory().openSession();
        Transaction transaction = null;
        List<String> stockList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            criteria.add(Restrictions.eq("person", person));
            List<Person> list = criteria.list();
            for (Person stock : list) {
                stockList.add(stock.getSymbol());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // close transaction
            }
        } finally {
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
        return stockList;

    }

}
