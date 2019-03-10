package lab_1.model;

import java.util.Comparator;

public class IndyvidualsComparator implements Comparator<Individual> {
    @Override
    public int compare(Individual o1, Individual o2) {
        if(o1.getFinalRatio() > o2.getFinalRatio()) {
            return -1;
        }
        else if (o1.getFinalRatio() < o2.getFinalRatio()) {
            return 1;
        } else {
            return 0;
        }
    }
}
