package lab_2.zad1.backTracking;

import lab_2.zad1.NoMorePossibleFieldException;

public abstract class BackwardsProblemSolver {

    public RowBacktracking[] rowBacktrackings;
    public int iterationsInRow;
    public int counter = 0;



    public abstract boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration);
    public abstract int getIterationsInRow();
    public abstract boolean firstRowCondition(int field);
    public abstract void printResult();
    public abstract int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException;



    public boolean findAllAllowedFields(int rowIndex, int iteration) throws NoMorePossibleFieldException{

        fetchPossibleFields(rowIndex, iteration);
        try {
            int nextPossibleField = getNextPossibleField(rowIndex);
            counter ++;
            rowBacktrackings[rowIndex].field = nextPossibleField;
            rowBacktrackings[rowIndex].addTakenField(nextPossibleField);
            rowBacktrackings[rowIndex].addValueToField(iteration, nextPossibleField);
        }catch (NoMorePossibleFieldException e){
            throw e;
        }

        return false;

    }


    public void fetchPossibleFields(int rowIndex, int iteration){
        for(int field = 0; field < rowBacktrackings.length; field++) {
            if (validateField(rowIndex, field, iteration)) {
                rowBacktrackings[rowIndex].addAvailableField(field);
            }
        }
    }



    public boolean validateField(int rowIndex, int field, int iteration){
        if(rowIndex == 0){
            return firstRowCondition(field);
        } else{
            for(int i = 0; i<rowIndex; i++){
                if(validateInSpecificProblem(field, i, rowIndex, iteration)){
                    return false;
                }
            }
            return true;
        }
    }

    public abstract RowBacktracking clearRowAfterReturn(RowBacktracking rowBacktracking);

    public void initRows(){
        for (int i = 0; i < rowBacktrackings.length; i++){
            rowBacktrackings[i] = new RowBacktracking(rowBacktrackings.length);
        }
    }

    public void tryToFixPreviousRow(int previousRowIndex, int iteration) throws NoMorePossibleFieldException{
        int previouslyTakenField = rowBacktrackings[previousRowIndex].field;
        rowBacktrackings[previousRowIndex].releaseTakenField(previouslyTakenField);
        rowBacktrackings[previousRowIndex].releaseValueFromField(previouslyTakenField);

        int nextPossibleField = rowBacktrackings[previousRowIndex].getNextPossibleField();
        rowBacktrackings[previousRowIndex].field = nextPossibleField;

        rowBacktrackings[previousRowIndex].addTakenField(nextPossibleField);
        rowBacktrackings[previousRowIndex].addValueToField(iteration, nextPossibleField);
    }

}
