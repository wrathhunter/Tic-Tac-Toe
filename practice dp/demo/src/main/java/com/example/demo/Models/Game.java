package com.example.demo.Models;

import java.util.*;

import com.example.demo.Exceptions.DuplicateSymbolException;
import com.example.demo.Exceptions.MoreThanOneBotException;
import com.example.demo.Exceptions.PlayersCountDimensionsMismatchException;
import com.example.demo.Strategies.WinningStrategies;

public class Game {
    private List<Player> players;
    private Board board;
    private List<WinningStrategies> winningStrategies;
    private int nextPLayerMoveIndex;
    private Player winner;
    private GameState gameState;
    private List<Move> moves;

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public Board getBoard() {
        return board;
    }
    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }
    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public int getNextPLayerMoveIndex() {
        return nextPLayerMoveIndex;
    }
    public void setNextPLayerMoveIndex(int nextPLayerMoveIndex) {
        this.nextPLayerMoveIndex = nextPLayerMoveIndex;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    
    private Game(int dimension, List<WinningStrategies> winningStrategies,List<Player> players){//constructor
        //as this constructor is private , so this can not be called directly by parent class.. so we need a builder class which will 
        //manage all the valiadtions and finally call this constructor to create the object of the game class. 
        this.players=players;
        this.winningStrategies=winningStrategies;
        this.nextPLayerMoveIndex=0;
        this.gameState=GameState.IN_PROGRESS;
        this.moves=new ArrayList<>();
        this.board=new Board(dimension);
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public void printBoard(){
        board.printBoard();
    }
    public boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        if(row>board.getSize()){
            return false;
        }
        if(col>board.getSize()){
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return true;
        }
        return false;
    }
    public void makeMove(){
        Player curretnMovePlayer=players.get(nextPLayerMoveIndex);
        System.out.println("current player is "+ curretnMovePlayer.getName()+" is going to make move");
        Move move=curretnMovePlayer.makeMove(board);
        System.out.println("current player want to move in to row: "+move.getCell().getRow()+" and column: "+move.getCell().getCol());
        //validations
        if(!validateMove(move)){
            System.out.println("Invalid move. Please try again");
            return;
        }
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        Cell cellToBeUpdatedInBoard=board.getBoard().get(row).get(col);
        cellToBeUpdatedInBoard.setCellState(CellState.FILLED);
        cellToBeUpdatedInBoard.setPlayer(curretnMovePlayer);
        Move finalMove=new Move(cellToBeUpdatedInBoard,curretnMovePlayer);
        moves.add(finalMove);
        nextPLayerMoveIndex++;
        nextPLayerMoveIndex%=players.size();
        if(checkWinner(board, finalMove)){
            gameState=GameState.WINNER;
            winner=curretnMovePlayer;
        }
        else if(moves.size()==board.getSize()*board.getSize() ){
            gameState=GameState.DRAW;
        }
    }
    private boolean checkWinner(Board board, Move move){
        for(WinningStrategies ws:winningStrategies){
            if(ws.checkWinner(board, move)){
                return true;
            }
        }
        return false; 
    }
    public static class Builder{//builder class checks all the validations and call the constrcutor of game class to create a game object. 
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;
        private int dimensions;
        
        public Builder setdimensions(int dimension){
            this.dimensions=dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies){
            this.winningStrategies=winningStrategies;
            return this;
        }
        public Builder addWinningStrategies(WinningStrategies winningStrategies){
            this.winningStrategies.add(winningStrategies);
            return this;
        }
        public Game build() throws MoreThanOneBotException, PlayersCountDimensionsMismatchException, DuplicateSymbolException{//validate and call the game constructor to create the game object
            validate();
            return new Game(dimensions, winningStrategies, players);
        }
        private void validate() throws MoreThanOneBotException, PlayersCountDimensionsMismatchException, DuplicateSymbolException{
            validateBotCount();
            validateDimensionsAndPlayerCount();
            validateUniqueSymboldForPlayers();
        }
        private void validateBotCount() throws MoreThanOneBotException{
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new MoreThanOneBotException();
            }
        }
        private void validateDimensionsAndPlayerCount() throws PlayersCountDimensionsMismatchException{
             if(players.size()!=dimensions-1)
                throw new PlayersCountDimensionsMismatchException();
        }
        private void validateUniqueSymboldForPlayers() throws DuplicateSymbolException{
            Map<Character,Integer> symbolCount=new HashMap<>();
            for(Player player:players){
                if(!symbolCount.containsKey(player.getSymbol().getaChar())){
                    symbolCount.put(player.getSymbol().getaChar(),0);
                }
                symbolCount.put(player.getSymbol().getaChar(), symbolCount.get(player.getSymbol().getaChar())+1);
                if(symbolCount.get(player.getSymbol().getaChar())>1){
                    throw new  DuplicateSymbolException();
                }
            }
        }
    }
    public void Undo(){
        if(moves.size()==0){
            System.out.println("no Move to Undo");
            return;
        }
        Move lastMove=moves.get(moves.size()-1);
        moves.remove(lastMove);
        Cell cell=lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
        for(WinningStrategies winningStrategies:winningStrategies){
            winningStrategies.handleUndo(board, lastMove);
        }
        nextPLayerMoveIndex--;
        nextPLayerMoveIndex=(nextPLayerMoveIndex+players.size())%players.size();
    }
}
