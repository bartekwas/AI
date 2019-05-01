package main.java.zadanie3.algorithm;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;
import main.java.zadanie3.Move;
import main.java.zadanie3.evaluation.Evaluation;
import main.java.zadanie3.order.OrderOfSearch;

public interface Algorithm {
    Move nextMove(Game game, PlayerSymbol color);

    public void setEvaluation(Evaluation evaluation);
    public void setOrder(OrderOfSearch order);
}
