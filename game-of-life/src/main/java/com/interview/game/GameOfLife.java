/**
 * 
 */
package com.interview.game;

import com.interview.game.Cell.State;

public class GameOfLife {

  Cell[][] board;
  private static final String SEPERATOR = " ";

  GameOfLife() {
    board = new Cell[25][25];
    initlializeBoard();
  }

  GameOfLife(int rows, int columns) {
    board = new Cell[rows][columns];
    initlializeBoard();
  }

  private void initlializeBoard() {
    for (int row = 0; row < board.length; row++) {
      for (int column = 0; column < board[0].length; column++) {
        board[row][column] = new Cell();
      }
    }
  }

  public void makeCellAlive(int row, int column) {
    Cell cell = board[row][column];
    cell.setState(State.ALIVE);
  }

  public void play() {
    nextGeneration();
    displayBoard();
  }

  public void nextGeneration() {

    if (board == null || board.length == 0 || board[0].length == 0) {
      return;
    }

    for (int row = 0; row < board.length; row++) {
      for (int column = 0; column < board[0].length; column++) {

        Cell cell = board[row][column];

        // Count Neighbors : For this cell we count the number of live neighbors.
        int countOfLiveNeighbors = countCurrentLiveNeighbors(board, row, column);

        /*
         * Cell state (current state):
         * 
         * if 1: and live neighbor count is 2 or 3 then as per game rule we mark the future state of cell as 0. 
         * if 0: and live neighbor count is 3 then as per reproduction we mark future state of cell as 1.
         */
        if (cell.getState() == State.ALIVE) {
          if (countOfLiveNeighbors == 2 || countOfLiveNeighbors == 3) {
            cell.setFutureState(State.ALIVE);
          }
        } else {
          if (countOfLiveNeighbors == 3) {
            cell.setFutureState(State.ALIVE);
          }
        }
      }
    }

    /*
     * Board is being refreshed with all cells state replaced with their future
     * state before exiting the method.
     * 
     * We replace cell's state attribute with cell futureState calculated in above
     * for loop. After replacing cell state we mark future state as 0 for all cells
     * for next iteration.
     */
    refreshBoard();
  }

  /**
   * This method calculates count of live neighbors for the cell at position
   * board[row][column].
   * 
   * 
   * @param board
   * @param row
   * @param column
   * @return Count of live neighbors for the cell.
   */
  public int countCurrentLiveNeighbors(Cell[][] board, int row, int column) {
    int count = 0;
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = column - 1; j <= column + 1; j++) {

        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
          Cell cell = board[i][j];
          count += cell.getState().getValue() & 1;
        }
      }
    }
    Cell cell = board[row][column];
    count -= cell.getState().getValue() & 1;
    return count;
  }

  /**
   * This method replaces the state of all cells with their future state that was
   * calculated.
   */
  public void refreshBoard() {
    for (int row = 0; row < board.length; row++) {
      for (int column = 0; column < board[0].length; column++) {
        Cell cell = board[row][column];
        State future = cell.getFutureState();
        cell.setState(future);
        cell.setFutureState(State.DEAD);
      }
    }
  }

  /**
   * Prints the current state of the board on console.
   */
  public void displayBoard() {
    for (Cell[] row : board) {
      for (Cell cell : row) {
        System.out.print(cell.getState().getValue() + SEPERATOR);
      }
      System.out.println();
    }
    System.out.println("--------------------------------------------------");
  }
}

/**
 * This cell class constitutes one position on the board. The class comprises of
 * two attributes
 * 
 * state: which specifies current state of Cell. futureState: which specifies
 * the state of the cell after the rules of the GameOfLife have been run on it.
 * 
 * state : cell is DEAD, value 0
 * state : cell is ALIVE, value 1
 *
 */
class Cell {

  private State state;
  private State futureState;

  Cell() {
    this.state = State.DEAD;
    this.futureState = State.DEAD;
  }

  Cell(State state) {
    this.state = state;
    this.futureState = State.DEAD;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getFutureState() {
    return futureState;
  }

  public void setFutureState(State future) {
    this.futureState = future;
  }
  
  enum State {
    
    DEAD(0), ALIVE(1);
    
    private int value;
    private State(int value) {
      this.value = value;
    }
    
    public int getValue() {
      return this.value;
    }
  }; 
}
