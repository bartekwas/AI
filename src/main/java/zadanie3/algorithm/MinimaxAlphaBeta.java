package main.java.zadanie3.algorithm;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;
import main.java.zadanie3.Move;
import main.java.zadanie3.evaluation.Evaluation;
import main.java.zadanie3.order.OrderOfSearch;

public class MinimaxAlphaBeta extends Minimax implements Algorithm {


    public MinimaxAlphaBeta(int depth, Evaluation evaluation, OrderOfSearch order) {
        super(depth, evaluation, order);
    }

    public MinimaxAlphaBeta(int depth) {
        super(depth);
    }

    @Override
    public Move nextMove(Game game, PlayerSymbol symbol) {
        minimaxWithPossibleAlfaBeta(game, DEPTH, true, symbol, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return nextPossibleMoves.get(0);
    }

    public String toString(){
        return "AlphaBeta, depth: "+ DEPTH+".";
    }
}
