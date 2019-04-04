package lab_2.zad1;

public class App {

    public static int SIZE =8;
    public static Problem problem = Problem.LATIN_SQUARE_PROBLEM;

    public static void main(String[] args){

        Solver solver = new Solver(SIZE, problem);
        solver.solveProblemBackwardsChecking();
    }


}
