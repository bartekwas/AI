package main.java.zadanie3.evaluation;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;

public interface Evaluation {
    int evaluate(Game game, PlayerSymbol color);

    String getName();
}
