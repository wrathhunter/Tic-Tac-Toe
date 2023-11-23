package com.example.demo.Strategies;

import com.example.demo.Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy BotPlayingStrategyForDifficulty(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
    }
    
}
