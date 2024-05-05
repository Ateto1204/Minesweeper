package PicCollage.assignment.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import PicCollage.assignment.model.Engine;
import PicCollage.assignment.model.properties.Coordinate;

public class Presenter {

    private Scanner scanner;
    private Engine engine;

    private Coordinate init() {
        scanner = new Scanner(System.in);
        System.out.println("This is the Game of Minesweeper!!!");

        System.out.print("Enter the height of grid: ");
        int inputHeight = getInput();

        System.out.print("Enter the width of grid: ");
        int inputWidth = getInput();

        System.out.print("Enter the number of mines: ");
        int inputNumMine = getInput(inputHeight * inputWidth);

        engine = Engine.getInstance(inputHeight, inputWidth, inputNumMine);

        System.out.print("Enter the row of first click: ");
        int inputRow = getInput(inputHeight);

        System.out.print("Enter the col of first click: ");
        int inputCol = getInput(inputWidth);

        return new Coordinate(inputRow, inputCol);
    }

    public void start() {
        Coordinate firstClick = init();

        List<Coordinate> minePositions = engine.firstClick(firstClick);

        System.out.println("\nBoard: ");
        engine.showBoard();

        System.out.println("\nMine Postions: ");
        for (Coordinate position : minePositions) {
            System.out.println("x: " + position.getRow() + ", y: " + position.getCol());
        }
    }

    private int getInput() {
        int input = 0;
        boolean nextStep = false;
        while (!nextStep) {
            try {
                input = scanner.nextInt();
                nextStep = true;
            } catch (InputMismatchException exception) {
                System.out.print("Invalid input, try again: ");
                scanner.next();
            }
        }
        return input;
    }

    private int getInput(int bound) {
        int input = 0;
        boolean nextStep = false;
        while (!nextStep) {
            try {
                boolean first = true;
                do {
                    if (first) first = false;
                    else System.out.println("Input out of bound, try again: ");
                    input = scanner.nextInt();
                } while (input >= bound);
                nextStep = true;
            } catch (InputMismatchException exception) {
                System.out.print("Invalid input, try again: ");
                scanner.next();
            }
        }
        return input;
    }
}
