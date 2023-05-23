package chessgame;

public class Game {
    public static String playerName;
    public static int movesLeft;

    public Game(){
        playerName = "";
        movesLeft = 50;
    }
    public Game(String name){
        playerName = name;
        movesLeft = 50;
    }

    public Game(Game game){
        this.playerName = game.playerName;
        this.movesLeft = game.movesLeft;
    }

    public static void setPlayerName(String playerName) {
        Game.playerName = playerName;
    }

    public static void setMovesLeft(int movesLeft) {
        Game.movesLeft = movesLeft;
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static int getMovesLeft() {
        return movesLeft;
    }

    public void decMoves(){
        movesLeft--;
    }


}
