package lab_2.zad1;

import java.util.ArrayList;

public class Solver {

public final int SIZE;
private final Problem problem;
private ProblemSolver problemSolver;

public Solver(int size, Problem problem) {
    this.SIZE = size;
    this.problem = problem;

    switch(problem){
        case QUEENS_PROBLEM:
            problemSolver = new OueensProblemSolver(new Row[SIZE]);
            break;
        case LATIN_SQUARE_PROBLEM:
            problemSolver = new LatinSquareProblemSolver(new Row[SIZE]);
            break;
    }
}



public void solveProblemBackwardsChecking(){

    iterateForAllValues();
    String justForTest = "test";
    problemSolver.printResult();


}

private void iterateForAllValues(){
    for(int iteration = 0; iteration < problemSolver.getIterationsInRow(); iteration++) {
        startIterationFromIndex(0, iteration);
    }



}

    private void startIterationFromIndex(int i, int iteration){
            for (; i < SIZE; i++) {
                try {
                    problemSolver.rows[i] = problemSolver.clearRowAfterReturn(problemSolver.rows[i]);
                    problemSolver.findAllAllowedFields(i, iteration);
                } catch (NoMorePossibleFieldException ex) {
                    int j = i - 1;
                    while (j >= 0) {
                        try {
                            problemSolver.tryToFixPreviousRow(j, iteration);
                            startIterationFromIndex(j + 1, iteration);
                            break;
                        } catch (NoMorePossibleFieldException ex1) {
                            j--;
                        }
                    }
                }
            }
    }










}
