package PicCollage.assignment.model.repositories;

import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;

import PicCollage.assignment.model.properties.Coordinate;

public class Minesweeper implements GameLogic {
    private int gridWidth;
    private int gridHeight;
    private int numMines;
    private int[][] board;
    private static final SecureRandom randomNumber = new SecureRandom();

    public Minesweeper(int gridHeight, int gridWidth, int numMines) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.numMines = numMines;
        this.board = new int[gridHeight][gridWidth];
    }

    public void generateBoard() {
        // Initialize board with empty cells
        cleanup();

        // Place mines randomly on the board
        int tmp = numMines;
        while (tmp > 0) {
            int mineRow = randomNumber.nextInt(gridHeight);
            int mineCol = randomNumber.nextInt(gridWidth);
            if (board[mineRow][mineCol] != -1 && board[mineRow][mineCol] != -2) {
                tmp--;
            }
            board[mineRow][mineCol] = -1;
        }
    }

    @Override
    public void update(List<Coordinate> minePositions) {
        int squareDirection[][] = {{-1, -1}, {-1, 0}, {-1, 1},
                                    {0, -1}, {0, 1},
                                    {1, -1}, {1, 0}, {1, 1}};
        final int WAY_NUM = squareDirection.length;
        for (Coordinate position : minePositions) {
            int x = position.getRow();
            int y = position.getCol();
            for (int i = 0; i < WAY_NUM; i++) {
                int nx = x + squareDirection[i][0];
                int ny = y + squareDirection[i][1];
                if (validCheck(new Coordinate(nx, ny), gridHeight, gridWidth)) {
                    board[nx][ny]++;
                }
            }
        }
    }

    @Override
    public void cleanup() {
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                board[i][j] = 0;
            }
        }
    }

    @Override
    public boolean validCheck(Coordinate pos, int height, int width) {
        int x = pos.getRow();
        int y = pos.getCol();
        if (x < 0 || x >= height) return false;
        if (y < 0 || y >= width) return false;
        if (board[x][y] == -1 || board[x][y] == -2) return false;
        return true;
    }

    public List<Coordinate> placeMines(Coordinate firstClick) {
        // Avoid placing a mine in the first clicked cell
        int x = firstClick.getRow();
        int y = firstClick.getCol();
        board[x][y] = -2;

        generateBoard();

        // Find mine positions
        List<Coordinate> minePositions = new ArrayList<>();
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (board[i][j] == -1) {
                    minePositions.add(new Coordinate(i, j));
                }
            }
        }
        return minePositions;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }
}