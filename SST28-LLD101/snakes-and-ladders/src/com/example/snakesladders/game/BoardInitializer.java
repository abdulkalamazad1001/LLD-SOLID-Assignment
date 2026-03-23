package com.example.snakesladders.game;

import com.example.snakesladders.DifficultyLevel;
import com.example.snakesladders.model.Board;
import com.example.snakesladders.model.Ladder;
import com.example.snakesladders.model.Snake;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardInitializer {

    private static final int MAX_ATTEMPTS = 50000;
    private final Random random = new Random();

    public Board initialize(int n, DifficultyLevel difficulty) {
        Board board = new Board(n);
        int total = n * n;
        Set<Integer> occupied = new HashSet<>();
        occupied.add(1);
        occupied.add(total);

        placeSnakes(board, n, total, difficulty, occupied);
        placeLadders(board, n, total, difficulty, occupied);
        return board;
    }

    private void placeSnakes(Board board, int count, int total, DifficultyLevel difficulty, Set<Integer> occupied) {
        int minDrop = difficulty == DifficultyLevel.HARD ? Math.max(2, total / 5) : 2;
        int maxDrop = difficulty == DifficultyLevel.EASY ? Math.max(3, total / 5) : total / 2;

        int placed = 0;
        int attempts = 0;

        while (placed < count && attempts < MAX_ATTEMPTS) {
            attempts++;
            int head = 2 + random.nextInt(total - 2);
            if (occupied.contains(head))
                continue;

            int lowerBound = Math.max(1, head - maxDrop);
            int upperBound = head - minDrop;
            if (upperBound <= lowerBound)
                continue;

            int tail = lowerBound + random.nextInt(upperBound - lowerBound);
            if (occupied.contains(tail))
                continue;

            board.addSnake(new Snake(head, tail));
            occupied.add(head);
            occupied.add(tail);
            placed++;
        }
    }

    private void placeLadders(Board board, int count, int total, DifficultyLevel difficulty, Set<Integer> occupied) {
        int minClimb = difficulty == DifficultyLevel.EASY ? Math.max(2, total / 5) : 2;
        int maxClimb = difficulty == DifficultyLevel.HARD ? Math.max(3, total / 5) : total / 2;

        int placed = 0;
        int attempts = 0;

        while (placed < count && attempts < MAX_ATTEMPTS) {
            attempts++;
            int start = 2 + random.nextInt(total - 2);
            if (occupied.contains(start))
                continue;

            int lowerBound = start + minClimb;
            int upperBound = Math.min(total - 1, start + maxClimb);
            if (lowerBound >= upperBound)
                continue;

            int end = lowerBound + random.nextInt(upperBound - lowerBound);
            if (occupied.contains(end))
                continue;

            board.addLadder(new Ladder(start, end));
            occupied.add(start);
            occupied.add(end);
            placed++;
        }
    }
}
