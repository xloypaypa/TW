package logic;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberBuilderAndJudgementTest {

    @Test
    public void shouldBuild0123AndGet1A2BWhenInput8203() {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        String number = new NumberBuilder(random).buildNumber();
        String result = new NumberJudgment().judge(number, "8203");

        assertEquals("0123", number);
        assertEquals("1A2B", result);
    }

    @Test
    public void shouldBuild8021AndGet1A2BWhenInput8203() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(1, 3, 2, 4, 5, 8, 6, 7, 0, 9);

        String number = new NumberBuilder(random).buildNumber();
        String result = new NumberJudgment().judge(number, "8203");

        assertEquals("8021", number);
        assertEquals("1A2B", result);
    }

}