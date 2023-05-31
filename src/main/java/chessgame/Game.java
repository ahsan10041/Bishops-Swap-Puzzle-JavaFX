package chessgame;

import java.time.ZonedDateTime;


public class Game {
    private String playerName;
    private int movesLeft;

    private boolean isSolved;

    private int movesMade;

    private ZonedDateTime createdAt;


    public Game(){
        playerName = "";
        movesLeft = 50;
    }

    public Game(String playerName, int movesLeft, boolean isSolved, int movesMade, ZonedDateTime createdAt) {
        this.playerName = playerName;
        this.movesLeft = movesLeft;
        this.isSolved = isSolved;
        this.movesMade = movesMade;
        this.createdAt = createdAt;
    }


    public Game(Game game){
        this.playerName = game.playerName;
        this.movesLeft = game.movesLeft;
        this.isSolved = game.isSolved;
        this.createdAt = game.createdAt;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }


    public String getPlayerName() {
        return this.playerName;
    }



    public int getMovesLeft() {
        return this.movesLeft;
    }

    public boolean getIsSolved() {
        return this.isSolved;
    }

    public void setIsSolved(boolean value) {
        this.isSolved = value;
    }

    public void setCreatedAt(ZonedDateTime value) { this.createdAt = value; }

    public ZonedDateTime getCreatedAt() { return this.createdAt; }

    /**
     * Decreases the number of moves left by 1.
     */
    public void decMoves(){
        movesLeft--;
    }


}
