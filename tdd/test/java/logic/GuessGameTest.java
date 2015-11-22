package logic;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuessGameTest {

    @Test
    public void shouldBuild0123AndGet1A2BWhenInput8203() {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(6, random);
        String result = guessGame.guess("8203");

        assertEquals("1A2B", result);
    }

    @Test
    public void testCongratulations() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(6, random);
        String result = guessGame.guess("0123");

        assertEquals(GuessGame.win, result);
    }

    @Test
    public void testHit() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(6, random);

        assertEquals("Please input your number(6):", guessGame.getHit());
    }

    @Test
    public void testGameNotEnded() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(6, random);
        guessGame.guess("8203");
        assertFalse(guessGame.isEnded());
    }

    @Test
    public void testGameIsEndedWhenLose() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(1, random);
        guessGame.guess("0132");

        assertTrue(guessGame.isEnded());
    }

    @Test
    public void testGameIsEndedWhenWin() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(6, random);
        guessGame.guess("0123");

        assertTrue(guessGame.isEnded());
    }

    @Test
    public void testHitDecreaseWhenGuessAWrongNumber() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(5, random);
        guessGame.guess("0132");

        assertEquals("Please input your number(4):", guessGame.getHit());
    }

    @Test
    public void testGameOver() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(1, random);
        String result = guessGame.guess("0132");

        assertEquals(GuessGame.lose, result);
    }

    @Test
    public void testCongratulationsWhenOnLastChance() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        GuessGame guessGame = new GuessGame(1, random);
        String result = guessGame.guess("0123");

        assertEquals(GuessGame.win, result);
    }

    @Test
    public void testRepeatNumber() throws Exception {
        GuessGame guessGame = new GuessGame(1, new Random());
        assertEquals(GuessGame.inputHaveRepeat, guessGame.guess("1123"));
    }
}