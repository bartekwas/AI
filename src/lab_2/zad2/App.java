package lab_2.zad2;

import lab_2.zad1.Problem;
import lab_2.zad1.backTracking.BackwardsSolver;

public class App {

    public static int SIZE = 15;
    public static Problem problem = Problem.QUEENS_PROBLEM;

    public static void main(String[] args){

        BackwardsSolver backwardsSolver = new BackwardsSolver(SIZE, problem);
        backwardsSolver.solveProblemBackwardsChecking();
    }


}
