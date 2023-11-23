package com.example.demo.Models;

import java.util.*;

public class Board {
    private int size;
    private List<List<Cell>> board;
    public Board(int size) {
        this.size = size;
        this.board=new ArrayList<>();//just an outer arrayList[[cell], [cell]]
        for(int i=0;i<size;i++){//size 3 for 3 * 3 matrix
            board.add(new ArrayList<>());//[ [cell,cell,cell] , [cell,cell,cell] , [cell,cell,cell] ]
            for(int j=0;j<size;j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<List<Cell>> getBoard() {
        return board;
    }
    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
    public void printBoard(){
        for(List<Cell> row:board){
            for(Cell cell:row){
                cell.display();
            }
             System.out.println();
        }
        // System.out.println();
    }
}
