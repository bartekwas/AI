package lab_2.zad1;

public class LatinSquareProblemSolver extends ProblemSolver {


    public LatinSquareProblemSolver(Row[] rows){
        this.rows = rows;
        this.iterationsInRow = rows.length;
        initRows();
    }


    @Override
    public boolean validateInSpecificProblem(int field, int i, int rowIndex, int iteration) {
        return !(!(rows[rowIndex].checkIfFieldAlreadyBeenTaken(field)) && !(rows[i].field == field));
    }

    @Override
    public int getIterationsInRow() {
        return iterationsInRow;
    }

    @Override
    public boolean firstRowCondition(int field) {
        return !rows[0].checkIfFieldAlreadyBeenTaken(field);
    }



    public void printResult() {
    for(Row row : rows){
        for(int value: row.valuesOnFields){
            System.out.print(value + " ");
        }
        System.out.println("");
    }
    }

    @Override
    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException {

        for(int i = rowIndex; i<rows.length; i++) {
            if (rows[rowIndex].availableFields.contains(new Integer(i))){
                return i;
            }
        }
        return rows[rowIndex].getNextPossibleField();
    }

    @Override
    public Row clearRowAfterReturn(Row row) {
        row.availableFields.clear();
        row.resetCounter();
        return row;
    }
}
