package lab_2.zad1.backTracking;

import lab_2.zad1.NoMorePossibleFieldException;

public class OueensBackwardsProblemSolver extends BackwardsProblemSolver {
    public OueensBackwardsProblemSolver(RowBacktracking[] rowBacktrackings){
        this.rowBacktrackings = rowBacktrackings;
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
        return rowBacktrackings[i].field == field || !validateOnDiagonal(i, rowIndex, field);
    }


    public boolean validateOnDiagonal(int otherIndex, int currentRow, int currentField){
        if(Math.abs(otherIndex - currentRow) == Math.abs(rowBacktrackings[otherIndex].field - currentField)){
            return false;
        }
        return true;
    }



    public void printResult() {
        //System.out.print(counter);
//        for (int i = 0; i < rowBacktrackings.length; i++) {
//            if (rowBacktrackings[i] == null || rowBacktrackings[i].field == null) {
//                for (int k = 0; k < rowBacktrackings.length; k++) {
//                    System.out.print("x ");
//                }
//                System.out.println("");
//            } else {
//                for (int fieldsInRow = 0; fieldsInRow < rowBacktrackings.length; fieldsInRow++) {
//                    if (rowBacktrackings[i].field == fieldsInRow) {
//                        System.out.print("Q ");
//                    } else {
//                        System.out.print("x ");
//                    }
//                }
//                System.out.println("");
//            }
//
//        }
        System.out.println("");
    }

    @Override
    public RowBacktracking clearRowAfterReturn(RowBacktracking rowBacktracking) {
        return new RowBacktracking(rowBacktrackings.length);
    }

    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException {
        return rowBacktrackings[rowIndex].getNextPossibleField();
    }

}
