package chessgame;

import java.time.ZonedDateTime;

/**
 * The Game class represents a chess game.
 * It stores the player's name and the number of moves left.
 */
public class Game {
    private String playerName;
    private int movesLeft;

    private boolean isSolved;

    private ZonedDateTime createdAt;

    /**
     * Default constructor for the Game class.
     * Initializes the player's name as an empty string and the number of moves left to 50.
     */
    public Game(){
        playerName = "";
        movesLeft = 50;
    }

    public Game(String playerName, int movesLeft, boolean isSolved, ZonedDateTime createdAt) {
        this.playerName = playerName;
        this.movesLeft = movesLeft;
        this.isSolved = isSolved;
        this.createdAt = createdAt;
    }

    /**
     * Copy constructor for the Game class.
     * Creates a new Game object with the same player name and number of moves left as the given game.
     *
     * @param game The game to be copied.
     */
    public Game(Game game){
        this.playerName = game.playerName;
        this.movesLeft = game.movesLeft;
        this.isSolved = game.isSolved;
        this.createdAt = game.createdAt;
    }

    /**
     * Sets the player's name.
     *
     * @param playerName The player's name.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets the number of moves left.
     *
     * @param movesLeft The number of moves left.
     */
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }


    /**
     * Retrieves the number of moves left.
     *
     * @return The number of moves left.
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    public boolean getIsSolved() {
        return this.isSolved;
    }

    public void setIsSolved(boolean value) {
        this.isSolved = value;
    }

    /**
     * Decreases the number of moves left by 1.
     */
    public void decMoves(){
        movesLeft--;
    }


}
