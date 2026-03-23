# Snakes & Ladders

A command-line Snakes & Ladders game with configurable board size, number of players, and difficulty level.

## Requirements
- Take input `n` (board size to be `n`x`n`), `x` (number of players), `difficulty_level` (easy/hard).
- Exactly `n` snakes and `n` ladders placed randomly in the board.
- Each snake has its head at some number and its tail at a smaller number.
- Each ladder has its start position at some number and end position at a larger number.
- The board has numbers from 1 to `n^2`.
- Players make their move turn-by-turn starting from position 0.
- A 6-sided dice gives a random number from 1 to 6.
- Based on dice value, the player moves forward that number of cells.
- A player wins if they reach the last cell (`n^2`). If a roll exceeds `n^2`, the player does not move.
- Landing on a snake head moves the player to the tail. Landing on a ladder start moves the player to the end.
- Game continues until there is only 1 player left.
- Snakes and Ladders do not create a cycle.

## Features

- n×n board (cells 1 to n²)
- Multiple players with turn-based play
- n snakes and n ladders placed randomly
- Difficulty levels: EASY and HARD
- No snake/ladder cycles

## Difficulty

| Level | Snakes     | Ladders    |
|-------|------------|------------|
| EASY  | Short drop | Long climb |
| HARD  | Long drop  | Short climb|

## Rules

- Players start at position 0
- Roll a 6-sided die each turn
- Land on a snake head → slide to its tail
- Land on a ladder start → climb to its end
- Cannot move past the last cell (n²)
- First to reach n² wins
- Game ends when only one player has not yet won

## Build & Run

```bash
cd snakes-and-ladders/src
javac com/example/snakesladders/model/*.java com/example/snakesladders/game/*.java com/example/snakesladders/*.java
java com.example.snakesladders.App
```

## Sample Input

```
Board size (n): 10
Number of players: 3
Difficulty level (EASY/HARD): HARD
Player 1 name: Alice
Player 2 name: Bob
Player 3 name: Carol
```
