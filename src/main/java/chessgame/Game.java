package chessgame;

import java.time.ZonedDateTime;
/**
 * Represents a game in the chess application.
 */
public class Game {
    private String playerName;
    private int movesLeft;

    private boolean isSolved;

    private int movesMade;

    private ZonedDateTime createdAt;



    /**
     * Constructs a new Game object with default values.
     */
    public Game(){
        playerName = "";
        movesLeft = 50;
    }

    /**
     * Constructs a new Game object with specified values.
     *
     * @param playerName  the player's name
     * @param movesLeft   the number of moves left
     * @param isSolved    indicates if the game is solved
     * @param movesMade   the number of moves made
     * @param createdAt   the creation timestamp of the game
     */
    public Game(String playerName, int movesLeft, boolean isSolved, int movesMade, ZonedDateTime createdAt) {
        this.playerName = playerName;
        this.movesLeft = movesLeft;
        this.isSolved = isSolved;
        this.movesMade = movesMade;
        this.createdAt = createdAt;
    }

    /**
     * Constructs a new Game object by copying an existing game.
     *
     * @param game  the game to be copied
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
     * @param playerName  the player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets the number of moves left.
     *
     * @param movesLeft  the number of moves left
     */
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }


    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Gets the number of moves left.
     *
     * @return the number of moves left
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
     * Gets whether the game is solved.
     *
     * @return true if the game is solved, false otherwise
     */
    public boolean getIsSolved() {
        return this.isSolved;
    }

    /**
     * Sets whether the game is solved.
     *
     * @param value  true if the game is solved, false otherwise
     */
    public void setIsSolved(boolean value) {
        this.isSolved = value;
    }

    /**
     * Sets the creation timestamp of the game.
     *
     * @param value  the creation timestamp
     */
    public void setCreatedAt(ZonedDateTime value) { this.createdAt = value; }

    /**
     * Gets the creation timestamp of the game.
     *
     * @return the creation timestamp
     */
    public ZonedDateTime getCreatedAt() { return this.createdAt; }

    /**
     * Decreases the number of moves left by 1.
     */
    public void decMoves(){
        movesLeft--;
    }


}
