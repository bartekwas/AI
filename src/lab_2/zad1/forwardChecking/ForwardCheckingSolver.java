package lab_2.zad1.forwardChecking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.Problem;

public class ForwardCheckingSolver {

public final int SIZE;
private final Problem problem;
private ForwardCheckingProblemSolver forwardCheckingProblemSolverProblemSolver;
public boolean CONTINUE_RECURSION = true;

public ForwardCheckingSolver(int size, Problem problem) {
    this.SIZE = size;
    this.problem = problem;

    switch(problem){
        case QUEENS_PROBLEM:
            forwardCheckingProblemSolverProblemSolver = new OueensForwardCheckingProblemSolver(new RowForward[SIZE]);
            break;
        case LATIN_SQUARE_PROBLEM:
            forwardCheckingProblemSolverProblemSolver = new LatinSquareForwardCheckingProblemSolver(new RowForward[SIZE]);
            break;
    }
}



public void solveProblemForwardcheckingChecking(){

    iterateForAllValues();
    String justForTest = "test";
    forwardCheckingProblemSolverProblemSolver.printResult();


}

private void iterateForAllValues(){
    for(int iteration = 0; iteration < forwardCheckingProblemSolverProblemSolver.getIterationsInRow(); iteration++) {
        try{
            startIterationFromIndex(0, iteration);
        }catch (Exception ex){
            System.out.println("test");
        }
    }



}

    private void startIterationFromIndex(int i, int iteration) throws Exception{
        if(CONTINUE_RECURSION) {
            for (; i < SIZE; i++) {

                try {
                    forwardCheckingProblemSolverProblemSolver.findAllAllowedFields(i, iteration);
                    if (i == SIZE - 1) {
                        CONTINUE_RECURSION = false;
                    }
                } catch (NoMorePossibleFieldException ex) {
                    int j = i - 1;
                    while (j >= 0) {
                        try {
                            forwardCheckingProblemSolverProblemSolver.tryToFixPreviousRow(j, iteration);
                            startIterationFromIndex(j, iteration);
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
