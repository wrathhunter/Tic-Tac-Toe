package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Controller.GameController;
import com.example.demo.Exceptions.DuplicateSymbolException;
import com.example.demo.Exceptions.MoreThanOneBotException;
import com.example.demo.Exceptions.PlayersCountDimensionsMismatchException;
import com.example.demo.Models.Bot;
import com.example.demo.Models.BotDifficultyLevel;
import com.example.demo.Models.Game;
import com.example.demo.Models.GameState;
import com.example.demo.Models.Player;
import com.example.demo.Models.PlayerType;
import com.example.demo.Models.Symbol;
import com.example.demo.Strategies.ColumnWinningStrategy;
import com.example.demo.Strategies.DiagonalWinningStrategy;
import com.example.demo.Strategies.RowWinningStrategy;
import com.example.demo.Strategies.WinningStrategies;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args)
			throws MoreThanOneBotException, PlayersCountDimensionsMismatchException, DuplicateSymbolException {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("hello");

		GameController gameController = new GameController();

		// Game game=gameController.startgame(3,playerList, new ArrayList<>());

		Scanner scanner = new Scanner(System.in);
		int dimension = 3;
		Player p1 = new Player(1, new Symbol('X'), "siba", PlayerType.HUMAN);
		Player p2 = new Bot(2, new Symbol('O'), "panda",BotDifficultyLevel.EASY);
		List<Player> playerList = new ArrayList<>();
		List<WinningStrategies> winningStrategies = List.of(new RowWinningStrategy(), new ColumnWinningStrategy(),
				new DiagonalWinningStrategy());
		playerList.add(p1);
		playerList.add(p2);
		Game game = gameController.startgame(dimension, playerList, winningStrategies);
		while (gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
			gameController.printBoard(game);
			if(game.getPlayers().get(game.getNextPLayerMoveIndex()).getPlayerType()==PlayerType.HUMAN && game.getMoves().size()!=0){
				System.out.println("do you want to do undo?");
				String undoAnswer=scanner.next();
				if(undoAnswer.equals("y")){
					gameController.undo(game);
					continue;
				}
			}
			gameController.makeMove(game);
		}
		System.out.println("Game is over");
		gameController.printBoard(game);
		if(gameController.checkState(game).equals(GameState.DRAW)){
			System.out.println("Game is draw");
		}
		else{
			System.out.println("we have a winner: "+ game.getWinner().getName()+ " "+game.getWinner().getSymbol().getaChar());
		}
	}
}
