package fr.pantheonsorbonne.miage.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TicTacToe {
    private static final Random rand = new Random();
    private char[][] board;

    public TicTacToe(int size) {
        board = new char[size][];
        for (int i = 0; i < board.length; i++) {
            board[i] = new char[size];
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '.';
            }
        }
    }

    public TicTacToe(String flat) {
        var len = (int) Math.sqrt(flat.length());
        board = new char[len][];
        for (int i = 0; i < len; i++) {
            board[i] = new char[len];
            for (int j = 0; j < len; j++) {
                board[i][j] = flat.charAt(i * len + j);
            }
        }
    }

    public void addRand(char c) throws FullBoardException {

        boolean empty = false;
        if (isFull()) {
            throw new FullBoardException();
        }
        for (; ; ) {
            int x = rand.nextInt(board.length);
            int y = rand.nextInt(board.length);
            if (board[x][y] == '.') {
                board[x][y] = c;
                break;
            }

        }


    }

    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public String toFlatString() {
        return this.toString().replaceAll("\n", "");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] line : board) {
            for (char c : line) {
                sb.append(c);
            }
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public char nextPlayer() {
        Map<Character, Integer> counts = new HashMap<>(Map.of('X', 0, 'O', 0));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (counts.containsKey(board[i][j])) {
                    counts.put(board[i][j], counts.get(board[i][j]) + 1);
                }
            }
        }
        Map.Entry<Character, Integer> minEntry = null;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (minEntry == null || entry.getValue()
                    .compareTo(minEntry.getValue()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry.getKey();
    }

    public char getWinner() {

        for (char[] line : board) {
            char first = line[0];
            boolean same = true;
            for (int i = 1; i < line.length; i++) {
                same &= (line[i] == first);
                if (!same)
                    break;
            }
            if (same)
                return first;
        }
        for (int i = 0; i < board.length; i++) {
            char first = board[i][0];
            boolean same = true;
            for (int j = 1; j < board.length; j++) {
                same &= board[i][j] == first;
                if (!same)
                    break;
            }
            if (same)
                return first;
        }
        {
            char first = board[0][0];
            boolean same = true;
            for (int i = 1; i < board.length; i++) {
                same &= board[i][i] == first;
                if (!same)
                    break;

            }
            if (same)
                return first;
        }
        {
            char first = board[board.length - 1][0];
            boolean same = true;
            for (int i = 1; i < board.length; i++) {
                same &= board[board.length - i - 1][i] == first;
                if (!same)
                    break;

            }
            if (same)
                return first;
        }

        return '.';
    }
}
