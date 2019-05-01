package main.java.zadanie3.order;

import main.java.zadanie3.Game;

import java.util.ArrayList;
import java.util.Collections;

public class RandomOrder implements OrderOfSearch {

    public RandomOrder() {
    }


    @Override
    public ArrayList<Game> getOrderedMoves(ArrayList<Game> oldMoves) {
        ArrayList<Game> moves = new ArrayList<>(oldMoves);
        Collections.shuffle(moves);
        return moves;
    }

    @Override
    public String getName() {
        return "random";
    }
}
