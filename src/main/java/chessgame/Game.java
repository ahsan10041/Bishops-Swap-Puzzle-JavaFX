package chessgame;
/**
 * The Game class represents a chess game.
 * It stores the player's name and the number of moves left.
 */
public class Game {
    public static String playerName;
    public static int movesLeft;

    /**
     * Default constructor for the Game class.
     * Initializes the player's name as an empty string and the number of moves left to 50.
     */
    public Game(){
        playerName = "";
        movesLeft = 50;
    }
    /**
     * Constructor for the Game class with a player name.
     * Initializes the player's name with the given name and the number of moves left to 50.
     *
     * @param name The player's name.
     */
    public Game(String name){
        playerName = name;
        movesLeft = 50;
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
    }

    /**
     * Sets the player's name.
     *
     * @param playerName The player's name.
     */
    public static void setPlayerName(String playerName) {
        Game.playerName = playerName;
    }

    /**
     * Sets the number of moves left.
     *
     * @param movesLeft The number of moves left.
     */
    public static void setMovesLeft(int movesLeft) {
        Game.movesLeft = movesLeft;
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public static String getPlayerName() {
        return playerName;
    }


    /**
     * Retrieves the number of moves left.
     *
     * @return The number of moves left.
     */
    public static int getMovesLeft() {
        return movesLeft;
    }

    /**
     * Decreases the number of moves left by 1.
     */
    public void decMoves(){
        movesLeft--;
    }


}
