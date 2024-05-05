package PicCollage.assignment.model;

import java.util.List;

import PicCollage.assignment.model.repositories.Minesweeper;
import PicCollage.assignment.model.properties.Coordinate;

public class Engine {
    private Minesweeper game;
    private static Engine instance;

    private Engine(int gridWidth, int gridHeight, int numMines) {
        game = new Minesweeper(gridWidth, gridHeight, numMines);
    }

    public List<Coordinate> firstClick(Coordinate firstClick) {
        List<Coordinate> minePositions = game.placeMines(firstClick);
        return minePositions;
    }

    public static Engine getInstance(int gridWidth, int gridHeight, int numMines) {
        if (instance == null) {
            instance = new Engine(gridWidth, gridHeight, numMines);
        }
        return instance;
    }

    public void showBoard() {
        int[][] board = game.getBoard();
        int row = game.getGridHeight();
        int col = game.getGridWidth();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (board[x][y] == -1) {
                    System.out.print("X");
                } else if (board[x][y] == -2) {
                    System.out.print("O");
                } else if (board[x][y] == 0) {
                    System.out.print("_");
                } else {
                    System.out.print(board[x][y]);
                }
                if (y+1 < col) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
