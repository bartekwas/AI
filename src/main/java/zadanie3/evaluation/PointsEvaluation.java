package main.java.zadanie3.evaluation;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;

public class PointsEvaluation implements Evaluation {

    public PointsEvaluation() {
    }

    @Override
    public int evaluate(Game game, PlayerSymbol color) {
        int white = game.getoPoints();
        int black = game.getxPoints();

        if (color == PlayerSymbol.X) {
            return white - black;
        } else if (color == PlayerSymbol.O) {
            return black - white;
        }

        throw new IllegalArgumentException("Bad player color");
    }

    @Override
    public String getName() {
        return "pointsDiff";
    }
}
