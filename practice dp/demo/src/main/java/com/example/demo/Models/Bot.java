package com.example.demo.Models;

import com.example.demo.Strategies.BotPlayingStrategy;
import com.example.demo.Strategies.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    
    public Bot(long id, Symbol symbol, String name, BotDifficultyLevel botDifficultyLevel) {
        super(id, symbol, name, PlayerType.BOT);
        this.botPlayingStrategy = BotPlayingStrategyFactory.BotPlayingStrategyForDifficulty(botDifficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }
    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }
    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
    @Override
    public Move makeMove(Board board){
        Move move= botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;

    }
    
}
