package com.ichuvilin.tictactoe;

import java.util.*;

public class Main {

    private static List<Integer> playerPos = new ArrayList<>();
    private static List<Integer> cpuPos = new ArrayList<>();

    private static char[][] board = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
    };

    private static void createGameBoard(char[][] board) {
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        createGameBoard(board);
        while (true) {
            System.out.print("Enter your placement (1-9): ");
            int pPosition = scan.nextInt();
            while (playerPos.contains(pPosition) || cpuPos.contains(pPosition)) {
                System.out.println("Position taken! Enter new placement");
                pPosition = scan.nextInt();
            }
            place(board, pPosition, "player");

            String result = checkWinner();

            if (result.length() > 1) {
                System.out.println(result);
                System.exit(1);
            }

            int cpuPosition = rand.nextInt(9) + 1;
            while (playerPos.contains(cpuPosition) || cpuPos.contains(cpuPosition)) {
                cpuPosition = rand.nextInt(9) + 1;
            }
            place(board, cpuPosition, "cpu");
            createGameBoard(board);

            result = checkWinner();

            if (result.length() > 1) {
                System.out.println(result);
                System.exit(1);
            }
        }
    }

    private static void place(char[][] board, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPos.add(pos);
        } else {
            symbol = 'O';
            cpuPos.add(pos);
        }

        switch (pos) {
            case 1 -> {
                board[0][0] = symbol;
                break;
            }
            case 2 -> {
                board[0][2] = symbol;
                break;
            }
            case 3 -> {
                board[0][4] = symbol;
                break;
            }
            case 4 -> {
                board[2][0] = symbol;
                break;
            }
            case 5 -> {
                board[2][2] = symbol;
                break;
            }
            case 6 -> {
                board[2][4] = symbol;
                break;
            }
            case 7 -> {
                board[4][0] = symbol;
                break;
            }
            case 8 -> {
                board[4][2] = symbol;
                break;
            }
            case 9 -> {
                board[4][4] = symbol;
                break;
            }
            default -> {
                break;
            }
        }
    }

    private static String checkWinner() {

        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);

        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> middleCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);

        List<Integer> firstCross = Arrays.asList(1, 5, 9);
        List<Integer> secondCross = Arrays.asList(3, 5, 7);

        List<List<Integer>> listValidMoves = Arrays.asList(topRow, middleRow, bottomRow,
                leftCol, middleCol, rightCol,
                firstCross, secondCross);

        for (List<Integer> l : listValidMoves) {
            if (playerPos.containsAll(l)) return "You won!";
            else if (cpuPos.containsAll(l)) return "Cpu wins!";
            else if (playerPos.size() + cpuPos.size() == 9) return "DRAW";
        }
        return "";
    }
}