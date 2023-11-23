package com.example.demo.Models;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private long id;
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private Scanner scanner;
    public Player(long id, Symbol symbol, String name, PlayerType playerType) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
        this.scanner=new Scanner(System.in);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Move makeMove( Board board){
        System.out.println("please tell row no where you want to move expecting a 0 based index: ");
        int row=scanner.nextInt();
        System.out.println("please tell column no where you want to move expecting a 0 based index: ");
        int col=scanner.nextInt();
        return new Move(new Cell(row,col), this);
    }
}
