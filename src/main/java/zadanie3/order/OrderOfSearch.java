package main.java.zadanie3.order;

import main.java.zadanie3.Game;

import java.util.ArrayList;

public interface OrderOfSearch {
    // TODO: 27/04/2019 is there a sense to make clone of array or we can play on referenced array?
    ArrayList<Game> getOrderedMoves(ArrayList<Game> moves);

    String getName();
}
