package com.example.demo.Strategies;

import com.example.demo.Models.Board;
import com.example.demo.Models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
