package lab_2.zad1;

import lab_2.zad1.backTracking.BackwardsSolver;
import lab_2.zad1.forwardChecking.ForwardCheckingSolver;

public class App {

    public static int SIZE =4;
    public static Problem problem = Problem.LATIN_SQUARE_PROBLEM;
    public static Method method = Method.FORWARDCHECKING;

    public static void main(String[] args){

        switch(method){
            case BACKTRACKING:
                BackwardsSolver backwardsSolver = new BackwardsSolver(SIZE, problem);
                backwardsSolver.solveProblemBackwardsChecking();
                break;
            case FORWARDCHECKING:
                ForwardCheckingSolver forwardCheckingSolver = new ForwardCheckingSolver(SIZE, problem);
                forwardCheckingSolver.solveProblemForwardcheckingChecking();
                break;
        }

    }


}
