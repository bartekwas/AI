package lab_1;

import lab_1.model.Configuration;
import lab_1.utils.*;

import java.util.Collections;

public class Main {

    private static final String FILE_NAME = "easy_0.ttp";
    private static final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/resources/zadanie1/";

    private static final int POPULATION_SIZE = 100;
    private static final int ITERATIONS = 20;
    private static final int BEST_RESULTS_ON_DIAGRAM = 4;

    public static final CollectingAlgorithm COLLECTIONG_ALGORITHM = CollectingAlgorithm.PROFIT_WEIGHT_RATIO;
    public static final ParentsChooseAlgorithm PARENTS_CHOOSE_ALGORITHM = ParentsChooseAlgorithm.TOURNAMENT;



    public static void main(String[] args) throws Exception {
        Configuration configuration = ParserUtils.parseInputFile(FILE_PATH, FILE_NAME);
        LogWriter logWriter = new LogWriter(configuration);

        logWriter.writeToLog(configuration.toString());

        AlgorithmUtil algorithmUtil = new AlgorithmUtil(configuration, POPULATION_SIZE, COLLECTIONG_ALGORITHM, PARENTS_CHOOSE_ALGORITHM, ITERATIONS, BEST_RESULTS_ON_DIAGRAM, logWriter);
        algorithmUtil.initialiazeGeneticAlgorithm();
    }
}
