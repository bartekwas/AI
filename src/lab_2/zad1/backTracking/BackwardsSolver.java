package lab_2.zad1.backTracking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.Problem;

public class BackwardsSolver {

public final int SIZE;
private final Problem problem;
private BackwardsProblemSolver backwardsProblemSolver;
    public boolean CONTINUE_RECURSION = true;

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

    backwardsProblemSolver.printResult();


}

private void iterateForAllValues(){
    for(int iteration = 0; iteration < backwardsProblemSolver.getIterationsInRow(); iteration++) {
        startIterationFromIndex(0, iteration);
        CONTINUE_RECURSION = true;
    }



}

    private void startIterationFromIndex(int i, int iteration){

        for (; i < SIZE; i++) {
            if (CONTINUE_RECURSION) {
                try {
                    backwardsProblemSolver.rowBacktrackings[i] = backwardsProblemSolver.clearRowAfterReturn(backwardsProblemSolver.rowBacktrackings[i]);
                    backwardsProblemSolver.findAllAllowedFields(i, iteration);
                    if (i == SIZE - 1) {
                        CONTINUE_RECURSION = false;
                    }
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










}
