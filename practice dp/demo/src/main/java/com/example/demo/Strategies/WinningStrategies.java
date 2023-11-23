package com.example.demo.Strategies;

import com.example.demo.Models.Board;
import com.example.demo.Models.Move;

public interface WinningStrategies {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move move);
} 
