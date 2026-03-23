package com.example.snakesladders;

import com.example.snakesladders.game.BoardInitializer;
import com.example.snakesladders.game.Game;
import com.example.snakesladders.model.Board;
import com.example.snakesladders.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Board size (n): ");
        int n = scanner.nextInt();

        System.out.print("Number of players: ");
        int x = scanner.nextInt();

        System.out.print("Difficulty level (EASY/HARD): ");
        DifficultyLevel difficulty = DifficultyLevel.valueOf(scanner.next().toUpperCase());

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            System.out.print("Player " + i + " name: ");
            players.add(new Player(scanner.next()));
        }

        Board board = new BoardInitializer().initialize(n, difficulty);
        new Game(board, players).play();

        scanner.close();
    }
}
