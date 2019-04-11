package lab_2.zad1.forwardChecking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.backTracking.RowBacktracking;

public class LatinSquareForwardCheckingProblemSolver extends ForwardCheckingProblemSolver {


    public LatinSquareForwardCheckingProblemSolver(RowForward[] rowBacktrackings){
        this.rowsForward = rowBacktrackings;
        this.iterationsInRow = rowBacktrackings.length;
        initRows();
    }


    @Override
    public boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration) {
        if(rowsForward[i].field == null){
            return false;
        }
        return !(!(rowsForward[rowIndex].checkIfFieldAlreadyBeenTaken(field)) && !(rowsForward[i].field == field));
    }

    @Override
    public int getIterationsInRow() {
        return iterationsInRow;
    }

    @Override
    public boolean firstRowCondition(int field) {
        return !rowsForward[0].checkIfFieldAlreadyBeenTaken(field);
    }



    public void printResult() {
        System.out.println(+ counter);
        for(RowForward rowBacktracking : rowsForward){
        for(int value: rowBacktracking.valuesOnFields){
            System.out.print(value + " ");
        }
        System.out.println("");
    }
    }

    @Override
    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException {
//
//        for(int i = rowIndex; i< rowsForward.length; i++) {
//            if (rowsForward[rowIndex].availableFields.contains(new Integer(i))){
//                return i;
//            }
//        }
        return rowsForward[rowIndex].getNextPossibleField();
    }

    @Override
    public RowForward clearRowAfterReturn(RowForward rowBacktracking) {
        rowBacktracking.availableFields.clear();
        rowBacktracking.resetCounter();
        return rowBacktracking;
    }
}
