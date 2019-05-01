package main.java.zadanie3.player;

import main.java.zadanie3.Game;
import main.java.zadanie3.Move;

public interface Player {
    Move nextMove(Game game);
}
