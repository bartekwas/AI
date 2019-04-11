package lab_2.zad1.forwardChecking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.backTracking.RowBacktracking;

public class OueensForwardCheckingProblemSolver extends ForwardCheckingProblemSolver {


    public OueensForwardCheckingProblemSolver(RowForward[] rowBacktrackings){
        this.rowsForward = rowBacktrackings;
        this.iterationsInRow = 1;
        initRows();
    }

    @Override
    public int getIterationsInRow() {
        return iterationsInRow;
    }

    @Override
    public boolean firstRowCondition(int field){
        return true;
    }

    public boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration){
        if(rowsForward[i].field == null){
            return false;
        }
        return rowsForward[i].field == field || !validateOnDiagonal(i, rowIndex, field);
    }


    public boolean validateOnDiagonal(int otherIndex, int currentRow, int currentField){
        if(Math.abs(otherIndex - currentRow) == Math.abs(rowsForward[otherIndex].field - currentField)){
            return false;
        }
        return true;
    }



    public void printResult() {
        //System.out.print(counter);
//        for (int i = 0; i < rowsForward.length; i++) {
//            if (rowsForward[i] == null || rowsForward[i].field == null) {
//                for (int k = 0; k < rowsForward.length; k++) {
//                    System.out.print("x ");
//                }
//                System.out.println("");
//            } else {
//                for (int fieldsInRow = 0; fieldsInRow < rowsForward.length; fieldsInRow++) {
//                    if (rowsForward[i].field == fieldsInRow) {
//                        System.out.print("Q ");
//                    } else {
//                        System.out.print("x ");
//                    }
//                }
//                System.out.println("");
//            }
//
//        }
//        System.out.println("");
    }

    @Override
    public RowForward clearRowAfterReturn(RowForward rowBacktracking) {
        return new RowForward(rowsForward.length);
    }

    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException {
        return rowsForward[rowIndex].getNextPossibleField();
    }

}
