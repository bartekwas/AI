package main.java.zadanie3.algorithm;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;
import main.java.zadanie3.Move;
import main.java.zadanie3.evaluation.Evaluation;
import main.java.zadanie3.order.OrderOfSearch;

import java.util.ArrayList;

public class Minimax implements Algorithm {

    protected final int DEPTH;

    protected Evaluation evaluation;
    protected OrderOfSearch order;
    protected ArrayList<Move> nextPossibleMoves;

    public Minimax(int depth, Evaluation evaluation, OrderOfSearch order) {
        this.DEPTH = depth;
        this.evaluation = evaluation;
        this.order = order;
        this.nextPossibleMoves = new ArrayList<>();
    }

    public Minimax(int depth) {
        this.DEPTH = depth;
        this.nextPossibleMoves = new ArrayList<>();
    }


    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void setOrder(OrderOfSearch order) {
        this.order = order;
    }

    @Override
    public Move nextMove(Game game, PlayerSymbol color) {

        minimaxWithPossibleAlfaBeta(game, DEPTH, true, color, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        //TODO take into account selected order
        return nextPossibleMoves.get(0);
    }


    protected int minimaxWithPossibleAlfaBeta(Game game, int depth, boolean max, PlayerSymbol symbol, int alpha, int beta, boolean alfaBeta) {
        if (depth == 0 || game.isOver()) {
            return evaluation.evaluate(game, symbol);
        }

        if (max) {
            int maxEval = Integer.MIN_VALUE;

            for (Game nextBoard : getNextMoves(game)) {
                int eval = minimaxWithPossibleAlfaBeta(nextBoard, depth - 1, false, symbol, alpha, beta, alfaBeta);
                if (eval >= maxEval && depth == DEPTH) {
                    if (eval > maxEval) {
                        nextPossibleMoves = new ArrayList<>();
                        nextPossibleMoves.add(nextBoard.getLastMove());
                    } else {
                        nextPossibleMoves.add(nextBoard.getLastMove());
                    }
                }
                if(eval > maxEval){
                    maxEval = eval;
                }
                if(alfaBeta) {
                    alpha = Math.max(alpha, eval);

                    if (beta <= alpha) {
                        break;
                    }
                }

            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Game nextBoard : getNextMoves(game)) {
                int eval = minimaxWithPossibleAlfaBeta(nextBoard, depth - 1, true, symbol, alpha, beta, alfaBeta);
                if (eval <= minEval && depth == DEPTH) {
                    if (eval < minEval) {
                        nextPossibleMoves = new ArrayList<>();
                        nextPossibleMoves.add(nextBoard.getLastMove());
                    } else {
                        nextPossibleMoves.add(nextBoard.getLastMove());
                    }
                }
                if(eval < minEval){
                    minEval = eval;
                }

                if(alfaBeta) {
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minEval;
        }
    }

    protected ArrayList<Game> getNextMoves(Game game) {
        ArrayList<Game> nextPossibleGames = new ArrayList<>();

        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard().length; j++) {
                if (game.isCellAllowedForMove(i, j)) {
                    Game newGame = game.clone();
                    newGame.makeMove(i, j);
                    nextPossibleGames.add(newGame);
                }
            }
        }
        return nextPossibleGames;
    }

    public String toString(){
        return "MinMax, depth: "+ DEPTH+".";
    }
}
