package logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberJudgmentTest {

    @Test
    public void testShould1A2BWhenCompare1234And1325() throws Exception {
        assertEquals("1A2B",  new NumberJudgment().judge("1234", "1325"));
    }

    @Test
    public void testShould0A0BWhenCompare1234And5678() throws Exception {
        assertEquals("0A0B", new NumberJudgment().judge("1234", "5678"));
    }

    @Test
    public void testShouldThrowExceptionWhenLengthNot4WhenCompare1234And56789() throws Exception {
        try {
            new NumberJudgment().judge("1234", "56789");
            fail();
        } catch (RuntimeException e) {
            assertEquals(NumberJudgment.lengthError, e.getMessage());
        }
    }

    @Test
    public void testShouldThrowExceptionWhenHaveRepeatNumberWhenCompare5555And1234() throws Exception {
        try {
            new NumberJudgment().judge("5555", "1234");
            fail();
        } catch (RuntimeException e) {
            assertEquals(NumberJudgment.repeatError, e.getMessage());
        }
    }
}