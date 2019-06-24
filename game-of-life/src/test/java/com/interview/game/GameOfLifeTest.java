package com.interview.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.interview.game.Cell.State;


public class GameOfLifeTest {
  GameOfLife game;

  /*
   * Sample Game Board for test
   * 
   * 0 0 0 0 0 
   * 0 0 1 0 0 
   * 0 0 0 1 0 
   * 0 1 1 1 0 
   * 0 0 0 0 0
   */
  @Before
  public void initialize() {
    game = new GameOfLife(5, 5);
    game.makeCellAlive(1, 2);
    game.makeCellAlive(2, 3);
    game.makeCellAlive(3, 1);
    game.makeCellAlive(3, 2);
    game.makeCellAlive(3, 3);
  }

  @Test
  public void testCountOfLiveNeighborsEqualZero() {
    // Count live neighbors of cell at position 0,0 on the above sample board.
    int count = game.countCurrentLiveNeighbors(game.board, 0, 0);
    assertEquals(0, count);
  }

  @Test
  public void testCountOfLiveNeighborsEqualOne() {
    // Count live neighbors of cell at position 1,2 on the above sample board.
    int count = game.countCurrentLiveNeighbors(game.board, 1, 2);
    assertEquals(1, count);
  }

  @Test
  public void testCountOfLiveNeighborsEqualThree() {
    // Count live neighbors of cell at position 2,3 on the above sample board.
    int count = game.countCurrentLiveNeighbors(game.board, 2, 3);
    assertEquals(3, count);
  }
  
  @Test
  public void testCountOfLiveNeighborsEqualTwo() {
    // Count live neighbors of cell at position 3,3 on the above sample board.
    int count = game.countCurrentLiveNeighbors(game.board, 3, 3);
    assertEquals(2, count);
  }

  @Test
  public void testCountOfLiveNeighborsForDeadCellEqualThree() {
    // Count live neighbors of cell at position 4,2 on the above sample board.
    int count = game.countCurrentLiveNeighbors(game.board, 4, 2);
    assertEquals(3, count);
  }

  @Test
  public void testCellStateAfterNextGeneration() {
    game.displayBoard();
    
    Cell cell32 = game.board[3][2];
    Cell cell33 = game.board[3][3];
        
    game.nextGeneration();
    Cell[][] refreshedBoard = game.board;
    assertTrue(refreshedBoard[3][1].getState() == State.DEAD);
    assertTrue(refreshedBoard[1][2].getState() == State.DEAD);

    assertTrue(refreshedBoard[2][1].getState() == State.ALIVE);
    assertTrue(refreshedBoard[2][3].getState() == State.ALIVE);
    assertTrue(refreshedBoard[4][2].getState() == State.ALIVE);
    
    assertEquals(refreshedBoard[3][2].getState(), cell32.getState());
    assertEquals(refreshedBoard[3][3].getState(), cell33.getState());
    
    game.displayBoard();
  }
}
