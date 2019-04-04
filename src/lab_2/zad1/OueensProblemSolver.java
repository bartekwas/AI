package lab_2.zad1;

public class OueensProblemSolver extends ProblemSolver {


    public OueensProblemSolver(Row[] rows){
        this.rows = rows;
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
        return rows[i].field == field || !validateOnDiagonal(i, rowIndex, field);
    }


    public boolean validateOnDiagonal(int otherIndex, int currentRow, int currentField){
        if(Math.abs(otherIndex - currentRow) == Math.abs(rows[otherIndex].field - currentField)){
            return false;
        }
        return true;
    }



    public void printResult() {
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null || rows[i].field == null) {
                for (int k = 0; k < rows.length; k++) {
                    System.out.print("x ");
                }
                System.out.println("");
            } else {
                for (int fieldsInRow = 0; fieldsInRow < rows.length; fieldsInRow++) {
                    if (rows[i].field == fieldsInRow) {
                        System.out.print("Q ");
                    } else {
                        System.out.print("x ");
                    }
                }
                System.out.println("");
            }

        }
        System.out.println("");
    }

    @Override
    public Row clearRowAfterReturn(Row row) {
        return new Row(rows.length);
    }

    public int getNextPossibleField(int rowIndex) throws NoMorePossibleFieldException{
        return rows[rowIndex].getNextPossibleField();
    }

}
