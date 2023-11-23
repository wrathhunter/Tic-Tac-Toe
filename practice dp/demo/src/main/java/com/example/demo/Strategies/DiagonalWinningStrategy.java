package com.example.demo.Strategies;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.Models.Board;
import com.example.demo.Models.Move;
import com.example.demo.Models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategies{
    private Map<Symbol,Integer> leftDiagonal=new HashMap<>();
    private Map<Symbol,Integer> rightDiagonal=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Symbol symbol=move.getCell().getPlayer().getSymbol();
        if(row==col){//left diagonal
            leftDiagonal.put(symbol, leftDiagonal.getOrDefault(symbol,0)+1); 
            if(leftDiagonal.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        if((row+col)==board.getSize()-1){
            rightDiagonal.put(symbol, rightDiagonal.getOrDefault(symbol,0)+1); 
            if(rightDiagonal.get(symbol).equals(board.getSize())){
                return true;
            }
        }
        return false;    
    }
    @Override
    public void handleUndo(Board board, Move move) {
        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        if(row==col){
            leftDiagonal.put(symbol,leftDiagonal.get(symbol)-1);
        }
        if((row+col)==board.getSize()-1){
            rightDiagonal.put(symbol,rightDiagonal.get(symbol)-1);
        }
    }
    
}
