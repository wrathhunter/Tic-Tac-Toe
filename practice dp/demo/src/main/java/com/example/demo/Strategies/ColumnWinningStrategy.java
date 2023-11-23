package com.example.demo.Strategies;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.Models.Board;
import com.example.demo.Models.Move;
import com.example.demo.Models.Symbol;

public class ColumnWinningStrategy implements WinningStrategies {
    private Map<Integer, Map<Symbol,Integer>> countMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        Symbol symbol=move.getCell().getPlayer().getSymbol();
        if(!countMap.containsKey(col)){
            countMap.put(col, new HashMap<>());
        }
        Map<Symbol,Integer> colMap=countMap.get(col);
        colMap.put(symbol, colMap.getOrDefault(symbol,0)+1);
        if(colMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
    @Override
    public void handleUndo(Board board, Move move) {
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();
        Map<Symbol,Integer> colMap=countMap.get(col);
        colMap.put(symbol,colMap.get(symbol)-1);
    }
    
}
