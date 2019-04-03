package lab_1;

import lab_1.model.Configuration;
import lab_1.utils.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.OptionalDouble;

public class Main {

    private static final String FILE_NAME = "easy_0.ttp";
    private static final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/resources/zadanie1/";
    private static final String[] allFiles = new String[]{"trivial_0.ttp"}; //"trivial_1.ttp", "easy_0.ttp", "easy_1.ttp", "easy_2.ttp", "easy_3.ttp", "easy_4.ttp", "medium_0.ttp", "medium_1.ttp", "medium_2.ttp", "medium_3.ttp", "medium_4.ttp", "hard_0.ttp",  "hard_1.ttp",  "hard_2.ttp",  "hard_3.ttp",  "hard_4.ttp"};
    private static final int POPULATION_SIZE = 100;
    private static final int ITERATIONS = 200;
    private static final int BEST_RESULTS_ON_DIAGRAM = 1;

    public static final CollectingAlgorithm COLLECTIONG_ALGORITHM = CollectingAlgorithm.PROFIT_WEIGHT_RATIO;
    public static final ParentsChooseAlgorithm PARENTS_CHOOSE_ALGORITHM = ParentsChooseAlgorithm.TOURNAMENT;

    public static ArrayList<Double> bestsOfAll;



    public static void main(String[] args) throws Exception {
        bestsOfAll = new ArrayList<>();

        for(String file : allFiles) {
            for (int i = 0; i < 10; i++) {
                bestsOfAll.add(runAlgorithm(file));
            }
            saveResults(file, bestsOfAll);
            bestsOfAll.clear();
        }


    }



    public static Double runAlgorithm(String fileName){
        Configuration configuration = ParserUtils.parseInputFile(FILE_PATH, fileName);
        LogWriter logWriter = new LogWriter(configuration);

        logWriter.writeToLog(configuration.toString());

        AlgorithmUtil algorithmUtil = new AlgorithmUtil(configuration, POPULATION_SIZE, COLLECTIONG_ALGORITHM, PARENTS_CHOOSE_ALGORITHM, ITERATIONS, BEST_RESULTS_ON_DIAGRAM, logWriter);
        ArrayList<Double> bests = algorithmUtil.initialiazeGeneticAlgorithm();
        OptionalDouble bestOfAlgorithm = bests.stream().mapToDouble(p -> p).max();
        return bestOfAlgorithm.getAsDouble();
    }

    public static void saveResults(String fileName, ArrayList<Double> bestsOfAll){
        StringBuilder sb = new StringBuilder();
        sb.append(fileName);
        sb.append("\n");
        for(int i =0; i< bestsOfAll.size(); i++){
            sb.append(bestsOfAll.get(i));
            sb.append("\n");
        }


        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/Bartek/IdeaProjects/AI/src/lab_1/output/bests.csv", true)));
            out.print(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
