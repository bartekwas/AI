package lab_1.utils;

import lab_1.model.Configuration;
import lab_1.model.Individual;

import java.io.*;
import java.util.ArrayList;

public class LogWriter {

    private final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/output/";
    private final String PROBLEM_NAME;
    private final String CSV_NAME_WHOLE_POPULATION;
    private final String CSV_NAME_BEST;
    private final String LOG_NAME;
    private final String BESTS_NAME;
    private final String FINAL_FILE_PATH;
    private final File CSV_FILE_WHOLE_POPULATION;
    private final File LOG_FILE;
    private final File CSV_FILE_BEST;
    private final File CSV_FILE_BESTS;
    private int counter_whole = 1;
    private int counter_best = 1;

    public LogWriter(Configuration configuration){
        PROBLEM_NAME = configuration.getProblemName();
        FINAL_FILE_PATH = FILE_PATH + PROBLEM_NAME + "/";
        CSV_NAME_WHOLE_POPULATION = PROBLEM_NAME + "_POPULATIONS.csv";
        CSV_NAME_BEST = PROBLEM_NAME + "_BEST.csv";
        LOG_NAME = PROBLEM_NAME + ".log";
        BESTS_NAME = "bests.csv";
        new File(FINAL_FILE_PATH).mkdir();
        CSV_FILE_WHOLE_POPULATION = new File(FINAL_FILE_PATH+ CSV_NAME_WHOLE_POPULATION);
        LOG_FILE = new File(FINAL_FILE_PATH+LOG_NAME);
        CSV_FILE_BEST = new File(FINAL_FILE_PATH+CSV_NAME_BEST);
        CSV_FILE_BESTS = new File("/Users/Bartek/IdeaProjects/AI/src/lab_1/output/bests.csv");
        clearLogs();
        initializeLogFile();


    }

    public void initializeLogFile(){
        String result = "";
        result += makeCSVColumn();
        writeOutput(result, CSV_NAME_WHOLE_POPULATION);
        writeOutput(result, CSV_NAME_BEST);


    }



    public void addBestResult(ArrayList<Individual> population){
        String result = "";
        result += makeCSVLines(population, true);
        writeOutput(result, CSV_NAME_BEST);
    }



    public void makeCSVWholePopulation(ArrayList<Individual> population){
        String result = "";
        result += makeCSVLines(population, false);
        writeOutput(result, CSV_NAME_WHOLE_POPULATION);
    }

    public void writeToLog(String log){
        writeOutput(log, LOG_NAME);
    }

    public String makeCSVColumn() {

        String semicolon = ";";

        StringBuilder builder = new StringBuilder();
        builder.append("L.p.");
        builder.append(semicolon);
        builder.append("Final ratio");
        builder.append(semicolon);
        builder.append("Overall distance");
        builder.append(semicolon);
        builder.append("Overall time");
        builder.append(semicolon);
        builder.append("Total profit");
        builder.append(semicolon);
        builder.append("Total weight");
        builder.append(semicolon);
        builder.append("\n");

        return builder.toString();
    }


    public String makeCSVColumnForBests() {

        String semicolon = ";";

        StringBuilder builder = new StringBuilder();
        builder.append("L.p.");
        builder.append(semicolon);
        builder.append("Best final ratio");
        builder.append("\n");

        return builder.toString();
    }

    public String makeCSVLines(ArrayList<Individual> population, boolean best) {

        String semicolon = ";";

        StringBuilder builder = new StringBuilder();

        for (Individual indyvidual:population
        ) {
            String finalRatio = String.valueOf(indyvidual.getFinalRatio());
            finalRatio =finalRatio.replace(".", ",");

            String totalTime = String.valueOf(indyvidual.getTotalTime());
            totalTime =totalTime.replace(".", ",");

            String totalProfit = String.valueOf(indyvidual.getTotalProfit());
            totalProfit =totalProfit.replace(".", ",");

            String totalWeight = String.valueOf(indyvidual.getTotalWeight());
            totalWeight =totalWeight.replace(".",   ",");

            if(best){
                builder.append(counter_best++);
            } else {
                builder.append(counter_whole++);
            }
            builder.append(semicolon);
            builder.append(finalRatio);
            builder.append(semicolon);
            builder.append(indyvidual.getTotalDistance());
            builder.append(semicolon);
            builder.append(totalTime);
            builder.append(semicolon);
            builder.append(totalProfit);
            builder.append(semicolon);
            builder.append(totalWeight);
            builder.append(semicolon);
            builder.append("\n");
        }
        return builder.toString();
    }

    public void writeOutput(String log, String fileName){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FINAL_FILE_PATH+fileName, true)));
            out.print(log);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void clearLogs(){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FINAL_FILE_PATH+LOG_NAME, false)));
            PrintWriter out_csv = new PrintWriter(new BufferedWriter(new FileWriter(FINAL_FILE_PATH+CSV_NAME_WHOLE_POPULATION, false)));
            PrintWriter out_best_csv = new PrintWriter(new BufferedWriter(new FileWriter(FINAL_FILE_PATH+CSV_NAME_BEST, false)));
                        out.close();
            out_csv.close();
            out_best_csv.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
