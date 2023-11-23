package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Exceptions.DuplicateSymbolException;
import com.example.demo.Exceptions.MoreThanOneBotException;
import com.example.demo.Exceptions.PlayersCountDimensionsMismatchException;
import com.example.demo.Models.Game;
import com.example.demo.Models.GameState;
import com.example.demo.Models.Player;
import com.example.demo.Strategies.WinningStrategies;

public class GameController {//supposed to be stateless. 
    public Game startgame(int dimensions, List<Player> players, List<WinningStrategies> winningStrategies) throws MoreThanOneBotException, PlayersCountDimensionsMismatchException, DuplicateSymbolException{
        return Game.getBuilder().setPlayers(players).setWinningStrategies(winningStrategies).setdimensions(dimensions).build();
    }

    public void printBoard(Game game){
		game.printBoard();
	}

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo(Game game){
        game.Undo();
    }
}
