package lab_2.zad1.forwardChecking;

import lab_2.zad1.NoMorePossibleFieldException;

import java.util.ArrayList;

public class RowForward {
    private int columnCounter = 0;
    public Integer field;
    public Integer[] valuesOnFields;
    public ArrayList<Integer> availableFields;
    public ArrayList<Integer> takenFields;


    public RowForward(int size){
        availableFields = new ArrayList<>();
        //initAvailableFields(size);

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

    public void releaseValueFromAvailableField(){
        availableFields.remove(0);
    }

    public Integer getNextPossibleField() throws NoMorePossibleFieldException {
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

    public void initAvailableFields(int size){
        for(int i = 0; i < size; i++){
            availableFields.add(i);
        }
    }
}
