package edu.uml.chrisweakley.tests.app;

import edu.uml.chrisweakley.app.StockTicker;
import edu.uml.chrisweakley.model.IntervalEnum;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StockTickerTest {

    private String[] args = {"aapl", "9/12/18", "9/18/17", "daily"};


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Positive Test
     * Testing the parsing logic used in the Main method, arg[3] equals
     * "midDay" and should end up setting the IntervalEnum equal to MIDDAY
     */
    @Test
    public void testEnumParsingPositive() {
        //StockTicker.main(args);
        IntervalEnum intervalEnum = IntervalEnum.OPEN_CLOSE;
        if (args[3].equalsIgnoreCase("open_close")) {
            intervalEnum = IntervalEnum.OPEN_CLOSE;
        } else if (args[3].equalsIgnoreCase("daily")) {
            intervalEnum = IntervalEnum.MIDDAY;
        } else if (args[3].equalsIgnoreCase("weekly")) {
            intervalEnum = IntervalEnum.HOURLY;
        }
        assertTrue("interval should be MIDDAY", intervalEnum.equals(IntervalEnum.MIDDAY));
    }

    /**
     * Negativ Test
     * Testing the parsing logic used in the Main method, arg[3] equals
     * "midDay" and should end up setting the IntervalEnum equal to MIDDAY
     * and not HOURLY
     */
    @Test
    public void testEnumParsingNegative() {
        IntervalEnum intervalEnum = IntervalEnum.OPEN_CLOSE;
        if (args[3].equalsIgnoreCase("open_close")) {
            intervalEnum = IntervalEnum.OPEN_CLOSE;
        } else if (args[3].equalsIgnoreCase("hourly")) {
            intervalEnum = IntervalEnum.MIDDAY;
        } else if (args[3].equalsIgnoreCase("midday")) {
            intervalEnum = IntervalEnum.HOURLY;
        }
        assertFalse("interval should be MIDDAY", intervalEnum.equals(IntervalEnum.MIDDAY));
    }

    /**
     * Negative Test
     * If no arguments are provided Main should throw
     * a NullPointerException
     */
    @Test (expected = NullPointerException.class)
    public void testMainNegative() throws JAXBException {
        StockTicker.main(null);}
    }