package com.example.demo.Strategies;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.Models.Board;
import com.example.demo.Models.Move;
import com.example.demo.Models.Symbol;

public class RowWinningStrategy implements WinningStrategies {
    private Map<Integer, Map<Symbol,Integer>> countMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        Symbol symbol=move.getCell().getPlayer().getSymbol();
        if(!countMap.containsKey(row)){
            countMap.put(row, new HashMap<>());
        }
        Map<Symbol,Integer> rowMap=countMap.get(row);
        rowMap.put(symbol, rowMap.getOrDefault(symbol,0)+1);
        if(rowMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }
    @Override
    public void handleUndo(Board board, Move move) {
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        Map<Symbol,Integer> rowMap=countMap.get(row);
        rowMap.put(symbol,rowMap.get(symbol)-1);
    }
    
}
