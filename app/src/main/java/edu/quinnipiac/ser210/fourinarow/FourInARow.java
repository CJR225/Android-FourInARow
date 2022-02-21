/**
 * FourInARow - Implements IGame interface methods backend for gameactivity
 * @author Chris Rocco
 * @date 2/20/2022
 */


package edu.quinnipiac.ser210.fourinarow;

public class FourInARow implements IGame {
    // The game board and the game status
    private static final int ROWS = 6, COLS = 6; // number of rows and columns
    private int[][] board = new int[ROWS][COLS]; // game board in 2D array
    private int player, location;

    /**
     * clear board and set current player
     */
    public FourInARow() {

    }

    @Override
    public void clearBoard() {
        // TODO Auto-generated method stub

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {

                board[row][col] = EMPTY;
            }
        }
    }



    @Override
    public void setMove(int player, int location) { // location is number between 0-35
        // take location(15) and calculate

        int row = location / 6;
        int col = location % 6;

        this.player = player;
        this.location = location;

        if (board[row][col] == EMPTY) {
            board[row][col] = player;
        }



    }

    @Override
    public int getComputerMove() {
        // TODO Auto-generated method stub

        int ranLocation = (1 + (int) (Math.random() * ((35 - 1) + 1)));


        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == board[row][col + 1] && board[row][col] == board[row][col + 2]
                        && board[row][col] != EMPTY) {
                    if (col < 5 && col > 0 && row < 5 && row > 0) {
                        board[row][col + 3] = BLUE;
                    } else if (col == 5 || col == 0) {
                        return ranLocation;
                    }

                }

            }

        }

        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col]
                        && board[row][col] != EMPTY) {
                    if (col < 5 && col > 0 && row < 5 && row > 0) {
                        board[row + 3][col] = BLUE;
                    } else if (row == 5 || row == 0) {
                        return ranLocation;
                    }
                }
            }
        }
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == board[row - 1][col + 1] && board[row][col] == board[row - 2][col + 2]
                        && board[row][col] != EMPTY) {
                    if (col < 5 && col > 0 && row < 5 && row > 0) {
                        board[row - 3][col + 3] = BLUE;
                    } else if (row == 5 || row == 0 && col == 5 || col == 0) {
                        return ranLocation;
                    }
                }
            }
        }
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2]
                        && board[row][col] != EMPTY) {
                    if (col < 5 && col > 0 && row < 5 && row > 0) {
                        board[row + 3][col + 3] = BLUE;
                    } else if (row == 5 || row == 0 && col == 5 || col == 0) {
                        return ranLocation;
                    }
                }
            }
        }

        return ranLocation;
    }

    @Override
    public int checkForWinner() {
        // TODO Auto-generated method stub

        int tiecount = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {

                if (board[row][col] == board[row][col + 1] && board[row][col] == board[row][col + 2]
                        && board[row][col] == board[row][col + 3]) {

                    if (board[row][col] == BLUE) {

                        return 3;
                    } else if (board[row][col] == RED) {
                        return 2;
                    }

                }

                if (board[row][col] != EMPTY) {
                    tiecount++;
                }
                if (tiecount == 36) {
                    return 1;
                }

            }
        }

        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col]
                        && board[row][col] == board[row + 3][col]) {
                    if (board[row][col] == BLUE) {
                        return 3;
                    } else if (board[row][col] == RED) {
                        return 2;
                    }
                }
            }
        }

        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == board[row - 1][col + 1] && board[row][col] == board[row - 2][col + 2]
                        && board[row][col] == board[row - 3][col + 3]) {
                    if (board[row][col] == BLUE) {
                        return 3;
                    } else if (board[row][col] == RED) {
                        return 2;
                    }
                }
            }
        }

        for (int row = 0; row < board.length - 3; row++) {

            for (int col = 0; col < board[0].length - 3; col++) {

                if (board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2]
                        && board[row][col] == board[row + 3][col + 3]) {
                    if (board[row][col] == BLUE) {
                        return 3;
                    } else if (board[row][col] == RED) {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }
}
