package edu.uml.chrisweakley.tests.model;

import edu.uml.chrisweakley.utils.DatabaseInitializationException;
import edu.uml.chrisweakley.utils.DatabaseUtils;
import org.junit.After;
import org.junit.Before;

public class AbstractDatabaseInit {
    @Before
    public void setUp() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }

    @After
    public void tearDown() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    }
}
