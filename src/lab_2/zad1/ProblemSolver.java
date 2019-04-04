package lab_2.zad1;

public abstract class ProblemSolver {

    public Row[] rows;
    public int iterationsInRow;



    public abstract boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration);
    public abstract int getIterationsInRow();
    public abstract boolean firstRowCondition(int field);
    public abstract void printResult();
    public abstract int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException;



    public boolean findAllAllowedFields(int rowIndex, int iteration) throws NoMorePossibleFieldException{

        fetchPossibleFields(rowIndex, iteration);
        try {
            int nextPossibleField = getNextPossibleField(rowIndex);
            rows[rowIndex].field = nextPossibleField;
            rows[rowIndex].addTakenField(nextPossibleField);
            rows[rowIndex].addValueToField(iteration, nextPossibleField);
        }catch (NoMorePossibleFieldException e){
            throw e;
        }

        return false;

    }


    public void fetchPossibleFields(int rowIndex, int iteration){
        for(int field = 0; field < rows.length; field++) {
            if (validateField(rowIndex, field, iteration)) {
                rows[rowIndex].addAvailableField(field);
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

    public abstract Row clearRowAfterReturn(Row row);

    public void initRows(){
        for (int i = 0; i < rows.length; i++){
            rows[i] = new Row(rows.length);
        }
    }

    public void tryToFixPreviousRow(int previousRowIndex, int iteration) throws NoMorePossibleFieldException{
        int nextPossibleField = rows[previousRowIndex].getNextPossibleField();
        int previouslyTakenField = rows[previousRowIndex].field;

        rows[previousRowIndex].field = nextPossibleField;
        rows[previousRowIndex].releaseTakenField(previouslyTakenField);
        rows[previousRowIndex].releaseValueFromField(previouslyTakenField);
        rows[previousRowIndex].addTakenField(nextPossibleField);
        rows[previousRowIndex].addValueToField(iteration, nextPossibleField);
    }

}
