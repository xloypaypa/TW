package logic;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class NumberBuilderTest {

    private String result;

    @Before
    public void setUp() throws Exception {
        result = new NumberBuilder(new Random()).buildNumber();
    }

    @Test
    public void testBuildNumberLength() throws Exception {
        assertEquals(4, result.length());
    }

    @Test
    public void testBuildNumberIsDigit() throws Exception {
        for (int i = 0; i < 4; i++) {
            assertTrue(Character.isDigit(result.charAt(i)));
        }
    }

    @Test
    public void testBuildNumberNotRepeat() throws Exception {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            set.add(result.charAt(i));
        }
        assertEquals(4, set.size());
    }

}