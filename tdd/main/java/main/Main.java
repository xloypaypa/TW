package main;

import logic.GuessGame;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame(6, new Random());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!\r\n");

        while (!guessGame.isEnded()) {
            System.out.println(guessGame.getHit());
            System.out.println(guessGame.guess(scanner.next()));
            System.out.println();
        }
        scanner.close();
    }
}
