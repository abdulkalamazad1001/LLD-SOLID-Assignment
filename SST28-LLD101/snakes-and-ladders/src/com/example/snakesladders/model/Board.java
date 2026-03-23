package com.example.snakesladders.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int size;
    private final Map<Integer, Snake> snakeMap;
    private final Map<Integer, Ladder> ladderMap;

    public Board(int size) {
        this.size = size;
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public int getTotalCells() {
        return size * size;
    }

    public void addSnake(Snake snake) {
        snakeMap.put(snake.getHead(), snake);
    }

    public void addLadder(Ladder ladder) {
        ladderMap.put(ladder.getStart(), ladder);
    }

    public boolean hasSnake(int position) {
        return snakeMap.containsKey(position);
    }

    public boolean hasLadder(int position) {
        return ladderMap.containsKey(position);
    }

    public int getSnakeTail(int head) {
        return snakeMap.get(head).getTail();
    }

    public int getLadderEnd(int start) {
        return ladderMap.get(start).getEnd();
    }

    public Map<Integer, Snake> getSnakeMap() {
        return Collections.unmodifiableMap(snakeMap);
    }

    public Map<Integer, Ladder> getLadderMap() {
        return Collections.unmodifiableMap(ladderMap);
    }
}
