package main.java.zadanie3;

import main.java.zadanie3.algorithm.Algorithm;
import main.java.zadanie3.algorithm.Minimax;
import main.java.zadanie3.algorithm.MinimaxAlphaBeta;
import main.java.zadanie3.choosers.AlgorithmChooser;
import main.java.zadanie3.choosers.EvaluationChooser;
import main.java.zadanie3.choosers.OrderChooser;
import main.java.zadanie3.choosers.PlayerChooser;
import main.java.zadanie3.evaluation.Evaluation;
import main.java.zadanie3.evaluation.PointsEvaluation;
import main.java.zadanie3.order.OrderOfSearch;
import main.java.zadanie3.order.PointsOrder;
import main.java.zadanie3.player.ComputerPlayer;
import main.java.zadanie3.player.HumanPlayer;
import main.java.zadanie3.player.Player;

import java.time.LocalDateTime;

public class App_lab3 {

     /****************************************************************************************
     *********************** G A M E   I N I T I A L   S E T T I N G S ***********************
     ****************************************************************************************/


    private static final int BOARD_SIZE                             =       4;
    private static final MoveTimePrinter moveTimePrinter            =       MoveTimePrinter.YES;

    private static final PlayerChooser playerOne                    =       PlayerChooser.COMPUTER;
    private static final AlgorithmChooser playerOneAlgorithm        =       AlgorithmChooser.MINMAX;
    private static final EvaluationChooser playerOneEvaluation      =       EvaluationChooser.POINTS_EVALUATION;
    private static final OrderChooser playerOneOrder                =       OrderChooser.POINTS_ORDER;
    private static final int playerOneDepth                         =       4;

    private static final PlayerChooser playerTwo                    =       PlayerChooser.COMPUTER;
    private static final AlgorithmChooser playerTwoAlgorithm        =       AlgorithmChooser.ALFABETA;
    private static final EvaluationChooser playerTwoEvaluation      =       EvaluationChooser.POINTS_EVALUATION;
    private static final OrderChooser playerTwoOrder                =       OrderChooser.POINTS_ORDER;
    private static final int playerTwoDepth                         =       4;




    /****************************************************************************************
     ********************************* G A M E    L O G I C *********************************
     ****************************************************************************************/



    public static void main(String[] args) {
        Game game = new Game(BOARD_SIZE);



        Player firstPlayer = AppInitializer.initializePlayer(playerOne, PlayerSymbol.O, playerOneAlgorithm, playerOneEvaluation, playerOneOrder, playerOneDepth);
        Player secondPlayer = AppInitializer.initializePlayer(playerTwo, PlayerSymbol.X, playerTwoAlgorithm, playerTwoEvaluation, playerTwoOrder, playerTwoDepth);


        Long startTime = 0L;
        while (!game.isOver()) {
            if(moveTimePrinter.equals(MoveTimePrinter.YES)){
                GameReferee.printMoveTime(game, firstPlayer,secondPlayer, startTime);
            }
            game.printBoardAndScore();

            startTime = System.currentTimeMillis();
            if (game.getPlayerOnMove() == PlayerSymbol.O) {
                Move move = firstPlayer.nextMove(game);
                game.makeMove(move.getRow(), move.getColumn());
            } else {
                Move move = secondPlayer.nextMove(game);
                game.makeMove(move.getRow(), move.getColumn());
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        game.printBoardAndScore();
        System.out.println("\n\n");
        game.printResults();
    }





}

