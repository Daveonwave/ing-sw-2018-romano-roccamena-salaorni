package base;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

public abstract class BaseTest {
    //Test base

    //Funzioni di asserzione per test
    public void testAssumeError(String message) {
        assumeTrue(message, false);
    }
    public void testAssertError(String message) {
        assertTrue(message, false);
    }

}
