package main.java.zadanie3.evaluation;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;

public class NoEvaluation implements Evaluation {

    public NoEvaluation() {
    }

    @Override
    public int evaluate(Game game, PlayerSymbol color) {
        return 0;
    }

    @Override
    public String getName() {
        return "noEval";
    }

}
