package com.example.demo.Strategies;

import java.util.List;

import com.example.demo.Models.Board;
import com.example.demo.Models.Cell;
import com.example.demo.Models.CellState;
import com.example.demo.Models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row:board.getBoard()){
            for(Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
    
}
