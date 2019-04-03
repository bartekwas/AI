package lab_2.zad1;

public class QueensProblemSolver {

public final int SIZE;
private final Problem problem;
Queen [] queens;

public QueensProblemSolver(int size, Problem problem) {
    this.SIZE = size;
    queens = new Queen[SIZE];
    this.problem = problem;

}



public void solveQueensProblem(){
    try {
        startIterationFromIndex(0);
    } catch (NoMorePossibleFieldException e) {
        e.printStackTrace();
    }
    String justForTest = "test";
    printChessboard();


}

    private void startIterationFromIndex(int i) throws NoMorePossibleFieldException {
        for(; i<SIZE; i++){
            try{
                queens[i] = new Queen();
                findPlaceForQueen(i);
            } catch (NoMorePossibleFieldException ex){
                int j = i-1;
                while(j>=0){
                    try {
                        queens[j].field = queens[j].getNextPossibleField();
                        startIterationFromIndex(j+1);
                        break;
                    }catch (NoMorePossibleFieldException ex1){
                        j--;
                    }
                }
            }
        }
    }



public boolean findPlaceForQueen(int queenRowIndex) throws NoMorePossibleFieldException{

    fetchPossibleFieldsForQueen(queenRowIndex);
    try {
        queens[queenRowIndex].field = queens[queenRowIndex].getNextPossibleField();
    }catch (NoMorePossibleFieldException e){
        throw e;
    }

    return false;

}


public void fetchPossibleFieldsForQueen(int queenRowIndex){
    for(int field = 0; field < SIZE; field++) {
        if (validateFieldForQueen(queenRowIndex, field)) {
            queens[queenRowIndex].addAvailableField(field);
        }
    }
}


public boolean validateFieldForQueen(int queenRowIndex, int field){
    if(queenRowIndex == 0){
        return true;
    } else{
        for(int i = 0; i<queenRowIndex; i++){
            if(queens[i].field == null){
                return true;
            }
            if(validateLatinSquareProblem(field, i, queenRowIndex)){
                return false;
            }
        }
        return true;
    }
}

public boolean validateLatinSquareProblem(int field, int i, int queenRowIndex){
    return queens[i].field == field;
}

public boolean validateQueensProblem(int field, int i, int queenRowIndex){
    return queens[i].field == field || !validateOnDiagonal(i, queenRowIndex, field);
}


public boolean validateOnDiagonal(int otherQueenIndex, int currentRow, int currentField){
    if(Math.abs(otherQueenIndex - currentRow) == Math.abs(queens[otherQueenIndex].field - currentField)){
        return false;
    }
    return true;
}


public void printChessboard() {
    for (int i = 0; i < SIZE; i++) {
        if (queens[i] == null || queens[i].field == null) {
            for (int k = 0; k < SIZE; k++) {
                System.out.print("x ");
            }
            System.out.println("");
        } else {
            for (int fieldsInRow = 0; fieldsInRow < SIZE; fieldsInRow++) {
                if (queens[i].field == fieldsInRow) {
                    System.out.print("1 ");
                } else {
                    System.out.print("x ");
                }
            }
            System.out.println("");
        }

    }
    System.out.println("");
}










}
