package main.java.zadanie3.player;

import main.java.zadanie3.PlayerSymbol;
import main.java.zadanie3.Game;
import main.java.zadanie3.Move;
import main.java.zadanie3.algorithm.Algorithm;

public class ComputerPlayer implements Player {

    private PlayerSymbol symbol;
    private Algorithm algorithm;

    public ComputerPlayer(PlayerSymbol symbol, Algorithm algorithm) {
        this.algorithm = algorithm;
        this.symbol = symbol;
    }

    @Override
    public Move nextMove(Game game) {
        return algorithm.nextMove(game, symbol);
    }

    public String toString(){
        return "Computer, " + algorithm.toString();
    }
}
