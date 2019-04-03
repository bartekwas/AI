package lab_2.zad1;

import java.util.ArrayList;

public class Queen {
    private int columnCounter = 0;
    public Integer field;
    public ArrayList<Integer> availableFields;


    public Queen(){
        availableFields = new ArrayList<>();
    }

    public void addAvailableField(int field){
        availableFields.add(field);
    }

    public Integer getNextPossibleField() throws NoMorePossibleFieldException{
        try {
            return availableFields.get(columnCounter++);
        }catch (IndexOutOfBoundsException ex){
            columnCounter =0;
            throw new NoMorePossibleFieldException();
        }

    }


}
