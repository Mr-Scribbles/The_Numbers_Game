package com.example.thenumbersgame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testAssertList(){
        int high = 999;
        int low = 101;
        int result = GameActivity.setRandomNum();
        assertTrue("Number is not more then" + high, high >= result);
        assertTrue("Number is more then " + low, low <= result);
    }
}
