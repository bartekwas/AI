package main.java.zadanie3;

import main.java.zadanie3.algorithm.Algorithm;
import main.java.zadanie3.algorithm.Minimax;
import main.java.zadanie3.algorithm.MinimaxAlphaBeta;
import main.java.zadanie3.choosers.AlgorithmChooser;
import main.java.zadanie3.choosers.EvaluationChooser;
import main.java.zadanie3.choosers.OrderChooser;
import main.java.zadanie3.choosers.PlayerChooser;
import main.java.zadanie3.evaluation.Evaluation;
import main.java.zadanie3.evaluation.NoEvaluation;
import main.java.zadanie3.evaluation.PointsEvaluation;
import main.java.zadanie3.order.DefaultOrder;
import main.java.zadanie3.order.OrderOfSearch;
import main.java.zadanie3.order.PointsOrder;
import main.java.zadanie3.order.RandomOrder;
import main.java.zadanie3.player.ComputerPlayer;
import main.java.zadanie3.player.HumanPlayer;
import main.java.zadanie3.player.Player;

public class AppInitializer {


    public static Player initializePlayer(PlayerChooser player, PlayerSymbol playerSymbol, AlgorithmChooser playerAlgorithm, EvaluationChooser playerEvaluation,OrderChooser playerOrder, int playerDepth) {

        switch (player) {
            case HUMAN:
                return new HumanPlayer(playerSymbol);
            default:
                Algorithm algorithm;
                switch (playerAlgorithm) {
                    case MINMAX:
                        algorithm = new Minimax(playerDepth);
                        algorithm = setAlgorithm(algorithm, playerEvaluation, playerOrder);
                        break;
                    default:
                        algorithm = new MinimaxAlphaBeta(playerDepth);
                        algorithm = setAlgorithm(algorithm, playerEvaluation, playerOrder);
                        break;
                }
             return new ComputerPlayer(playerSymbol, algorithm);
        }
    }

    private static Algorithm setAlgorithm(Algorithm algorithm, EvaluationChooser playerEvaluation,OrderChooser playerOrder) {
        Evaluation evaluation;
        switch (playerEvaluation) {
            case POINTS_EVALUATION:
                evaluation = new PointsEvaluation();
                break;
            case NO_EVALUATION:
                evaluation = new NoEvaluation();
                break;
            default:
                evaluation = new PointsEvaluation();
                break;
        }
        algorithm.setEvaluation(evaluation);
        switch (playerOrder) {
            case POINTS_ORDER:
                algorithm.setOrder(new PointsOrder(evaluation));
                break;
            case RANDOM_ORDER:
                algorithm.setOrder(new RandomOrder());
                break;
            case DEFAULT_ORDER:
                algorithm.setOrder(new DefaultOrder(evaluation));
                break;
            default:
                algorithm.setOrder(new PointsOrder(evaluation));
                break;
        }
        return algorithm;
    }
}
