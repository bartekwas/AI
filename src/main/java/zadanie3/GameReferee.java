package main.java.zadanie3;

import main.java.zadanie3.player.Player;

class GameReferee {

    static boolean gameOver(PlayerSymbol[][] board) {
        for (PlayerSymbol[] row : board) {
            for (PlayerSymbol cell : row) {
                if (cell == PlayerSymbol.NONE) {
                    return false;
                }
            }
        }
        return true;
    }

    static void printBoardAndScore(PlayerSymbol[][] board, int oPoints, int xPoints) {
        System.out.print("    ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "    ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            PlayerSymbol[] row = board[i];
            System.out.print(i + "   ");
            for (PlayerSymbol cell : row) {
                if(cell.equals(PlayerSymbol.O)){
                    System.out.print("o    ");
                }else if(cell.equals(PlayerSymbol.X)) {
                    System.out.print("x    ");
                } else {
                    System.out.print("⃞   ");
                }

            }

            System.out.println();
        }
        System.out.println();
        System.out.println("  o points: " + oPoints);
        System.out.println("  x points: " + xPoints);
        System.out.println();
    }

    static void printMoveTime(Game game, Player firstPlayer, Player secondPlayer, Long startTime){
        if(startTime.equals(0L))return;
        if (game.getPlayerOnMove() == PlayerSymbol.X) {
            Long moveSelectionTime = System.currentTimeMillis() - startTime;
            System.out.println(firstPlayer.toString() + " Move time: " + moveSelectionTime + " ms.");
        } else {
            Long moveSelectionTime = System.currentTimeMillis() - startTime;
            System.out.println(secondPlayer.toString() + " Move time: " + moveSelectionTime + " ms.");
        }
    }


    static PlayerSymbol nextPlayerFor(PlayerSymbol whoseTurn) {
        if (whoseTurn == PlayerSymbol.X) {
            return PlayerSymbol.O;
        } else {
            return PlayerSymbol.X;
        }
    }

    static int calculatePoints(PlayerSymbol[][] board, int row, int column) {
        int pointsToAdd = 0;

        pointsToAdd += checkVertical(board, column);
        pointsToAdd += checkHorizontal(board, row);
        if (checkCross(board, row, column) > 1) {
            pointsToAdd += checkCross(board, row, column);
        }


        return pointsToAdd;
    }
    private static int checkVertical(PlayerSymbol[][] board, int column) {
        int points = 0;
        for (int i = 0; i < board.length ; i++) {
            if (board[i][column] == PlayerSymbol.NONE) {
                return 0;
            }
            points++;
        }
        return points;
    }

    // this function check if row is full
    private static int checkHorizontal(PlayerSymbol[][] board, int row) {
        int points = 0;
        for (int i = 0; i < board.length ; i++) {
            if (board[row][i] == PlayerSymbol.NONE) {
                return 0;
            }
            points++;
        }
        return points;
    }

    // this function check if cross is full left and right direction
    private static int checkCross(PlayerSymbol[][] board, int row, int column) {

        int points = 0;
        int _points = 0;

        int _row = row;
        int _column = column;


        while (row != 0 && column != 0) {
            row--;
            column--;
        }

        while (column < board.length && row<board.length ) {
            if (board[row][column] == PlayerSymbol.NONE) {
                points = 0;
                break;
            }
            row++;
            column++;
            points++;
        }

        while (_row != 0 && _column<board.length -1) {
            _row--;
            _column++;
        }

        while (_column > -1 && _row < board.length ) {
            if (board[_row][_column] == PlayerSymbol.NONE) {
                _points = 0;
                break;
            }
            _row++;
            _column--;
            _points++;
        }
        return points + _points;
    }

}
