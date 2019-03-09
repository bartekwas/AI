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
    private final String FINAL_FILE_PATH;
    private final File CSV_FILE_WHOLE_POPULATION;
    private final File LOG_FILE;
    private final File CSV_FILE_BEST;

    public LogWriter(Configuration configuration){
        PROBLEM_NAME = configuration.getProblemName();
        FINAL_FILE_PATH = FILE_PATH + PROBLEM_NAME + "/";
        CSV_NAME_WHOLE_POPULATION = PROBLEM_NAME + "_POPULATIONS.csv";
        CSV_NAME_BEST = PROBLEM_NAME + "_BEST.csv";
        LOG_NAME = PROBLEM_NAME + ".log";
        new File(FINAL_FILE_PATH).mkdir();
        CSV_FILE_WHOLE_POPULATION = new File(FINAL_FILE_PATH+ CSV_NAME_WHOLE_POPULATION);
        LOG_FILE = new File(FINAL_FILE_PATH+LOG_NAME);
        CSV_FILE_BEST = new File(FINAL_FILE_PATH+CSV_NAME_BEST);
        clearLogs();
    }



    public void addBestResult(ArrayList<Individual> population){
        String result = "";
        result += makeCSVColumn();
        result += makeCSVLines(population);
        writeOutput(result, CSV_NAME_BEST);
    }



    public void makeCSVWholePopulation(ArrayList<Individual> population){
        String result = "";
        result += makeCSVColumn();
        result += makeCSVLines(population);
        writeOutput(result, CSV_NAME_WHOLE_POPULATION);
    }

    public void writeToLog(String log){
        writeOutput(log, LOG_NAME);
    }

    public String makeCSVColumn() {

        String semicolon = ";";

        StringBuilder builder = new StringBuilder();
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

    public String makeCSVLines(ArrayList<Individual> population) {

        String semicolon = ";";

        StringBuilder builder = new StringBuilder();

        for (Individual indyvidual:population
        ) {
            builder.append(indyvidual.getFinalRatio());
            builder.append(semicolon);
            builder.append(indyvidual.getTotalDistance());
            builder.append(semicolon);
            builder.append(indyvidual.getTotalTime());
            builder.append(semicolon);
            builder.append(indyvidual.getTotalProfit());
            builder.append(semicolon);
            builder.append(indyvidual.getTotalWeight());
            builder.append(semicolon);
            builder.append("\n");
        }
        return builder.toString();
    }

    public void writeOutput(String log, String fileName){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FINAL_FILE_PATH+fileName, true)));
            out.println(log);
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
