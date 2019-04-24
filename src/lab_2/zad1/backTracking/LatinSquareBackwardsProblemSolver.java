package lab_2.zad1.backTracking;

import lab_2.zad1.NoMorePossibleFieldException;



public class LatinSquareBackwardsProblemSolver extends BackwardsProblemSolver {



    public LatinSquareBackwardsProblemSolver(RowBacktracking[] rowBacktrackings){
        this.rowBacktrackings = rowBacktrackings;
        this.iterationsInRow = rowBacktrackings.length;
        initRows();
    }


    @Override
    public boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration) {
        return !(!(rowBacktrackings[rowIndex].checkIfFieldAlreadyBeenTaken(field)) && !(rowBacktrackings[i].field == field));
    }

    @Override
    public int getIterationsInRow() {
        return iterationsInRow;
    }

    @Override
    public boolean firstRowCondition(int field) {
        return !rowBacktrackings[0].checkIfFieldAlreadyBeenTaken(field);
    }



    public void printResult() {
       System.out.println(counter);
        for(RowBacktracking rowBacktracking : rowBacktrackings){
        for(int value: rowBacktracking.valuesOnFields){
            System.out.print(value + " ");
        }
        System.out.println("");
    }
    }

    @Override
    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException {

        for(int i = rowIndex; i< rowBacktrackings.length; i++) {
            if (rowBacktrackings[rowIndex].availableFields.contains(new Integer(i))){
                return i;
            }
        }
        return rowBacktrackings[rowIndex].getNextPossibleField();
    }

    @Override
    public RowBacktracking clearRowAfterReturn(RowBacktracking rowBacktracking) {
        rowBacktracking.availableFields.clear();
        rowBacktracking.resetCounter();
        return rowBacktracking;
    }
}
