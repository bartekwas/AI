package lab_1;

import lab_1.model.Configuration;
import lab_1.utils.AlgorithmUtil;
import lab_1.utils.ParserUtils;

public class Main {

    private static final String FILE_NAME = "easy_0.ttp";
    private static final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/resources/zadanie1/";
    private static final int POPULATION_SIZE = 100;

    public static void main(String[] args) throws Exception {

        Configuration configuration = ParserUtils.parseInputFile(FILE_PATH, FILE_NAME);
        System.out.println(configuration);

        AlgorithmUtil algorithmUtil = new AlgorithmUtil(POPULATION_SIZE);
        algorithmUtil.initialiazeGeneticAlgorithm(configuration);

    }
}
