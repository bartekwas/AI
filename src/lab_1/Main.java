package lab_1;

import lab_1.model.Configuration;
import lab_1.utils.ParserUtils;

public class Main {

    private static final String FILE_NAME = "easy_0.ttp";
    private static final String FILE_PATH = "/Users/Bartek/IdeaProjects/AI/src/lab_1/resources/zadanie1/";

    public static void main(String[] args) throws Exception {

        Configuration config = ParserUtils.parseInputFile(FILE_PATH, FILE_NAME);
        System.out.println(config); //print config to make sure everything is ok. just for debugging

        //Here is where algorithm should be written.
    }
}
