package com.example.snakesladders.game;

import com.example.snakesladders.model.Board;
import com.example.snakesladders.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final List<Player> activePlayers;
    private final List<Player> winners;
    private final Dice dice;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.activePlayers = new ArrayList<>(players);
        this.winners = new ArrayList<>();
        this.dice = new Dice();
    }

    public void play() {
        printBoardInfo();

        int index = 0;

        while (activePlayers.size() > 1) {
            if (index >= activePlayers.size()) {
                index = 0;
            }

            Player player = activePlayers.get(index);
            int roll = dice.roll();
            int currentPos = player.getPosition();
            int newPos = currentPos + roll;
            int totalCells = board.getTotalCells();

            System.out.println(player.getName() + " rolled " + roll + " | position: " + currentPos);

            if (newPos > totalCells) {
                System.out.println(player.getName() + " stays at " + currentPos + " (needs " + (totalCells - currentPos)
                        + " or less)");
                index++;
                continue;
            }

            player.setPosition(newPos);

            if (board.hasSnake(newPos)) {
                int tail = board.getSnakeTail(newPos);
                System.out.println("Snake! " + player.getName() + " slides from " + newPos + " -> " + tail);
                player.setPosition(tail);
            } else if (board.hasLadder(newPos)) {
                int end = board.getLadderEnd(newPos);
                System.out.println("Ladder! " + player.getName() + " climbs from " + newPos + " -> " + end);
                player.setPosition(end);
            }

            if (player.getPosition() == totalCells) {
                winners.add(player);
                activePlayers.remove(index);
                System.out.println(player.getName() + " wins! Rank #" + winners.size());
            } else {
                index++;
            }
        }

        printResults();
    }

    private void printBoardInfo() {
        int total = board.getTotalCells();
        System.out.println("\n=== Snakes & Ladders ===");
        System.out.println("Board : " + board.getSize() + "x" + board.getSize() + " (" + total + " cells)");
        System.out.println("Snakes: " + board.getSnakeMap().size());
        board.getSnakeMap()
                .forEach((head, snake) -> System.out.println("  head=" + head + " -> tail=" + snake.getTail()));
        System.out.println("Ladders: " + board.getLadderMap().size());
        board.getLadderMap()
                .forEach((start, ladder) -> System.out.println("  start=" + start + " -> end=" + ladder.getEnd()));
        System.out.println("========================\n");
    }

    private void printResults() {
        System.out.println("\n--- Game Over ---");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println("Rank #" + (i + 1) + " : " + winners.get(i).getName());
        }
        if (!activePlayers.isEmpty()) {
            System.out.println("Last  : " + activePlayers.get(0).getName());
        }
    }
}
