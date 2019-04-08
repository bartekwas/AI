package lab_2.zad1.forwardChecking;

import lab_2.zad1.NoMorePossibleFieldException;
import lab_2.zad1.backTracking.RowBacktracking;

public abstract class ForwardCheckingProblemSolver {

    public RowForward[] rowsForward;
    public int iterationsInRow;



    public abstract boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration);
    public abstract int getIterationsInRow();
    public abstract boolean firstRowCondition(int field);
    public abstract void printResult();
    public abstract int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException;



    public boolean findAllAllowedFields(int rowIndex, int iteration) throws NoMorePossibleFieldException{

        fetchPossibleFieldsForFirstRow(rowIndex, iteration);
        try {
            int nextPossibleField = getNextPossibleField(rowIndex);
            rowsForward[rowIndex].field = nextPossibleField;
            for(int row = rowIndex+1; row < rowsForward.length; row++){
                rowsForward[row] = clearRowAfterReturn(rowsForward[row]);
                fetchPossibleFields(row, iteration);
            }


            // for latin problem
            rowsForward[rowIndex].addTakenField(nextPossibleField);
            rowsForward[rowIndex].addValueToField(iteration, nextPossibleField);
        }catch (NoMorePossibleFieldException e){
            throw e;
        }

        return false;

    }


    public void fetchPossibleFields(int rowIndex, int iteration){
        for(int field = 0; field < rowsForward.length; field++) {
            if (validateField(rowIndex, field, iteration)) {
                rowsForward[rowIndex].addAvailableField(field);
            }
        }
    }

    public void fetchPossibleFieldsForFirstRow(int rowIndex, int iteration){
        if(rowIndex == 0){
            fetchPossibleFields(rowIndex, iteration);
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

    public abstract RowForward clearRowAfterReturn(RowForward rowForward);

    public void initRows(){
        for (int i = 0; i < rowsForward.length; i++){
            rowsForward[i] = new RowForward(rowsForward.length);
        }
    }

    public void tryToFixPreviousRow(int previousRowIndex, int iteration) throws NoMorePossibleFieldException{
 //       rowsForward[previousRowIndex].releaseValueFromAvailableField();

//        int nextPossibleField = rowsForward[previousRowIndex].getNextPossibleField();
//        int previouslyTakenField = rowsForward[previousRowIndex].field;
//
//        rowsForward[previousRowIndex].field = nextPossibleField;
//        rowsForward[previousRowIndex].releaseTakenField(previouslyTakenField);
//        rowsForward[previousRowIndex].releaseValueFromField(previouslyTakenField);
//        rowsForward[previousRowIndex].addTakenField(nextPossibleField);
//        rowsForward[previousRowIndex].addValueToField(iteration, nextPossibleField);
    }

}
