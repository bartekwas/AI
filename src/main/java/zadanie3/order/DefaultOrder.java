package main.java.zadanie3.order;

import main.java.zadanie3.Game;
import main.java.zadanie3.evaluation.Evaluation;

import java.util.ArrayList;

public class DefaultOrder implements OrderOfSearch {

    private final Evaluation evaluation;

    public DefaultOrder(Evaluation evaluation) {
        this.evaluation = evaluation;
    }


    @Override
    public ArrayList<Game> getOrderedMoves(ArrayList<Game> moves) {
        return moves;
    }

    @Override
    public String getName() {
        return "first";
    }
}
