package main.java.zadanie3.player;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;
import main.java.zadanie3.Move;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private static final Scanner s = new Scanner(System.in);
    private PlayerSymbol playerSymbol;

    public HumanPlayer(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }


    @Override
    public Move nextMove(Game game) {
        System.out.println("Row");
        int row = s.nextInt();

        System.out.println("Column");
        int column = s.nextInt();

        return new Move(row, column);
    }

    public String toString(){
        return "Human, using brain.";
    }
}
