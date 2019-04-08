package lab_2.zad1.backTracking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.Problem;

public class BackwardsSolver {

public final int SIZE;
private final Problem problem;
private int counter = 0;
private BackwardsProblemSolver backwardsProblemSolver;

public BackwardsSolver(int size, Problem problem) {
    this.SIZE = size;
    this.problem = problem;

    switch(problem){
        case QUEENS_PROBLEM:
            backwardsProblemSolver = new OueensBackwardsProblemSolver(new RowBacktracking[SIZE]);
            break;
        case LATIN_SQUARE_PROBLEM:
            backwardsProblemSolver = new LatinSquareBackwardsProblemSolver(new RowBacktracking[SIZE]);
            break;
    }
}



public void solveProblemBackwardsChecking(){

    iterateForAllValues();
    System.out.println("Iterations: " + counter);
    backwardsProblemSolver.printResult();


}

private void iterateForAllValues(){
    for(int iteration = 0; iteration < backwardsProblemSolver.getIterationsInRow(); iteration++) {
        startIterationFromIndex(0, iteration);
    }



}

    private void startIterationFromIndex(int i, int iteration){
        counter++;
    for (; i < SIZE; i++) {
                try {

                    backwardsProblemSolver.rowBacktrackings[i] = backwardsProblemSolver.clearRowAfterReturn(backwardsProblemSolver.rowBacktrackings[i]);
                    backwardsProblemSolver.findAllAllowedFields(i, iteration);
                } catch (NoMorePossibleFieldException ex) {
                    int j = i - 1;
                    while (j >= 0) {
                        try {
                            backwardsProblemSolver.tryToFixPreviousRow(j, iteration);
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
