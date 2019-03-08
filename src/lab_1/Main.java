package lab_1;

import lab_1.model.Configuration;
import lab_1.utils.AlgorithmUtil;
import lab_1.utils.ParserUtils;

public class Main {

    private static final String FILE_NAME = "easy_0.ttp";
    private static final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/resources/zadanie1/";
    private static final int POPULATION_SIZE = 100;
    private static final boolean LOGGING = false;
    private static final boolean BACKPACK_ALGORITHM_LOGGING = false;

    public static void main(String[] args) throws Exception {

        Configuration configuration = ParserUtils.parseInputFile(FILE_PATH, FILE_NAME);

        if(LOGGING) {
            System.out.println(configuration);
        }
        AlgorithmUtil algorithmUtil = new AlgorithmUtil(configuration, POPULATION_SIZE, LOGGING, BACKPACK_ALGORITHM_LOGGING);
        algorithmUtil.initialiazeGeneticAlgorithm();

    }
}
