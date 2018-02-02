package edu.uml.chrisweakley.tests.utils;

import edu.uml.chrisweakley.utils.DatabaseInitializationException;
import edu.uml.chrisweakley.utils.DatabaseUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *  Tests for the DatabaseUtils class
 */
public class DatabaseUtilsTest {

    @Before
    public void setUp() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    @After
    public void tearDown() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }


    @Test
    public void testGoodInitFile() throws Exception {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    @Test
    public void testGetSessionFactory() throws Exception {
        SessionFactory sessionFactory = DatabaseUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
    }


    @Test(expected = DatabaseInitializationException.class)
    public void testBadInitFile() throws Exception {
        DatabaseUtils.initializeDatabase("bogus");
    }

    @Test
    public void testGetConnection() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("verify that we can get a connection ok",connection);
    }


    @Test
    public void testGetConnectionWorks() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from stocks.quotes");
        assertTrue("verify that we can execute a statement against quote table",execute);
        execute = statement.execute("select * from stocks.stock");
        assertTrue("verify that we can execute a statement against stock_symbol table",execute);
        execute = statement.execute("select * from stocks.person");
        assertTrue("verify that we can execute a statement against person_stocks table",execute);

    }
}