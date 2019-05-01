package main.java.zadanie3.order;

import main.java.zadanie3.Game;
import main.java.zadanie3.evaluation.Evaluation;

import java.util.ArrayList;

public class PointsOrder implements OrderOfSearch {

    private final Evaluation evaluation;

    public PointsOrder(Evaluation evaluation) {
        this.evaluation = evaluation;
    }


    @Override
    public ArrayList<Game> getOrderedMoves(ArrayList<Game> moves) {
        moves.sort((game1, game2) -> {

            if (!canBeComparable(game1, game2)) {
                throw new IllegalArgumentException();
            }

            int gamePointsOne = evaluation.evaluate(game1, game1.getPlayerOnMove());
            int gamePointsTwo = evaluation.evaluate(game2, game2.getPlayerOnMove());

            return gamePointsOne - gamePointsTwo;
        });
        return moves;
    }

    private boolean canBeComparable(Game game1, Game game2) {
        return game1.getPlayerOnMove() == game2.getPlayerOnMove();
    }

    @Override
    public String getName() {
        return "ptsOrder";
    }
}
