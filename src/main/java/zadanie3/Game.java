package main.java.zadanie3;

public class Game {

    private PlayerSymbol[][] board;

    private int oPoints;
    private int xPoints;

    private PlayerSymbol playerOnMove;

    private Move lastMove;

    private Game() {}

    public Game(int boardSize) {
        board = initBoard(boardSize);
        playerOnMove = PlayerSymbol.O;
        oPoints = 0;
        xPoints = 0;
    }

    private PlayerSymbol[][] initBoard(int size) {
        PlayerSymbol[][] board = new PlayerSymbol[size][size];
        for (int m = 0; m < size; m++) {
            for (int n = 0; n < size; n++) {
                board[m][n] = PlayerSymbol.NONE;
            }
        }
        return board;
    }

    public void makeMove(int row, int column) {
        if (!isCellAllowedForMove(row, column)) {
            return;
        }

        board[row][column] = playerOnMove;
        addPointsBasedOnMove(row, column);
        lastMove = new Move(row, column);

        playerOnMove = GameReferee.nextPlayerFor(playerOnMove);
    }

    public void addPointsBasedOnMove(int row, int column) {
        int pointsToAdd = GameReferee.calculatePoints(board, row, column);

        if (playerOnMove == PlayerSymbol.X) {
            oPoints += pointsToAdd;
        } else if (playerOnMove == PlayerSymbol.O) {
            xPoints += pointsToAdd;
        }

    }

    public boolean isCellAllowedForMove(int row, int column) {
        if (row  >= board.length || column >= board.length) {
            return false;
        }
        return board[row][column] == PlayerSymbol.NONE;
    }

    public boolean isOver() {
        return GameReferee.gameOver(board);
    }

    void printBoardAndScore() {
        GameReferee.printBoardAndScore(board, oPoints, xPoints);
    }

    public Game clone() {
        Game gameToClone = new Game();

        gameToClone.setPlayerOnMove(playerOnMove);
        gameToClone.setoPoints(oPoints);
        gameToClone.setxPoints(xPoints);
        gameToClone.setBoard(copy(board));

        return gameToClone;
    }

    private PlayerSymbol[][] copy(PlayerSymbol[][] board) {
        PlayerSymbol[][] newBoard = new PlayerSymbol[board.length][board.length];
        for (int i = 0; i < newBoard.length; i++) {
            for(int j = 0; j < newBoard.length; j++){
                newBoard[i][j] = board[i][j];

            }
        }
        return newBoard;
    }


    public void printResults() {

        System.out.println("Final score o : x is: " + oPoints + ":" + xPoints);
        if (oPoints > xPoints) {
            System.out.println("o wins!");
        } else {
            System.out.println("x wins!");
        }
    }

    public PlayerSymbol[][] getBoard() {
        return board;
    }

    public void setBoard(PlayerSymbol[][] board) {
        this.board = board;
    }

    public int getoPoints() {
        return oPoints;
    }

    public void setoPoints(int oPoints) {
        this.oPoints = oPoints;
    }

    public int getxPoints() {
        return xPoints;
    }

    public void setxPoints(int xPoints) {
        this.xPoints = xPoints;
    }

    public PlayerSymbol getPlayerOnMove() {
        return playerOnMove;
    }

    public void setPlayerOnMove(PlayerSymbol playerOnMove) {
        this.playerOnMove = playerOnMove;
    }

    public Move getLastMove() {
        return lastMove;
    }
}
