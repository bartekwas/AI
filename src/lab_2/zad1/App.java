package lab_2.zad1;

import lab_2.zad1.backTracking.BackwardsSolver;
import lab_2.zad1.forwardChecking.ForwardCheckingSolver;

import java.time.LocalDateTime;

public class App {

    public static int SIZE =10;
    public static Problem problem = Problem.LATIN_SQUARE_PROBLEM;
    public static Method method = Method.BACKTRACKING;

    public static void main(String[] args) {


        System.out.println(problem.toString() + " " + method.toString() + " "+ SIZE);
        switch (method) {
            case BACKTRACKING:
                BackwardsSolver backwardsSolver = new BackwardsSolver(SIZE, problem);
                backwardsSolver.solveProblemBackwardsChecking();
                break;
            case FORWARDCHECKING:
                ForwardCheckingSolver forwardCheckingSolver = new ForwardCheckingSolver(SIZE, problem);
                forwardCheckingSolver.solveProblemForwardcheckingChecking();
                break;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

//        for(int i =4; i<17; i++) {
//            long startDateTime = System.currentTimeMillis();
//            SIZE = i;
//            //System.out.print(SIZE + " ");
//            switch (method) {
//                case BACKTRACKING:
//                    BackwardsSolver backwardsSolver = new BackwardsSolver(SIZE, problem);
//                    backwardsSolver.solveProblemBackwardsChecking();
//                    break;
//                case FORWARDCHECKING:
//                    ForwardCheckingSolver forwardCheckingSolver = new ForwardCheckingSolver(SIZE, problem);
//                    forwardCheckingSolver.solveProblemForwardcheckingChecking();
//                    break;
//            }
//            long endDateTime = System.currentTimeMillis();
//            //System.out.println(" " + (endDateTime - startDateTime));
//        }
    }


}
