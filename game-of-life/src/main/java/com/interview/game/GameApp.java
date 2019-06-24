package com.interview.game;


public class GameApp {
  
  public static void main(String[] args) {
    GameOfLife game = new GameOfLife();
    game.makeCellAlive(11, 12);
    game.makeCellAlive(12, 13);
    game.makeCellAlive(13, 11);
    game.makeCellAlive(13, 12);
    game.makeCellAlive(13, 13);

    game.displayBoard();
    for (int i = 0; i < 50; i++) {
      game.play();
    }
  }
}
