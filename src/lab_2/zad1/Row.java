package lab_2.zad1;

import java.util.ArrayList;

public class Row {
    private int columnCounter = 0;
    public Integer field;
    public Integer[] valuesOnFields;
    public ArrayList<Integer> availableFields;
    public ArrayList<Integer> takenFields;


    public Row(int size){
        availableFields = new ArrayList<>();
        takenFields = new ArrayList<>();
        valuesOnFields = new Integer[size];
    }

    public void addAvailableField(int field){
        availableFields.add(field);
    }

    public void addTakenField(int filed){ takenFields.add(filed);}

    public void releaseTakenField(int field){takenFields.remove(new Integer(field));}

    public boolean checkIfFieldAlreadyBeenTaken(int field){
        return takenFields.contains(field);
    }

    public void addValueToField(int value, int field){
        valuesOnFields[field] = value;
    }

    public void releaseValueFromField(int field){
        valuesOnFields[field] = null;
    }

    public Integer getNextPossibleField() throws NoMorePossibleFieldException{
        try {
            return availableFields.get(columnCounter++);
        }catch (IndexOutOfBoundsException ex){
            columnCounter =0;
            throw new NoMorePossibleFieldException();
        }
    }

    public void resetCounter(){
        this.columnCounter =0;
    }
}
