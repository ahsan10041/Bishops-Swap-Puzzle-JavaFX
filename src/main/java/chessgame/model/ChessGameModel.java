package chessgame.model;

import chessgame.Game;
import chessgame.startPageController;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;



/**
 * The ChessGameModel class represents the model of a chess game.
 */
public class ChessGameModel {

    /**
     * The number of rows on the chess board.
     */
    public static final int BOARD_ROW = 5;

    /**
     * The number of columns on the chess board.
     */
    public static final int BOARD_COL = 4;

    private startPageController startpage = new startPageController();
    private Game thisGame = new Game(startpage.getCurrentPlayer());

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_ROW][BOARD_COL];

    /**
     * Constructs a ChessGameModel object and initializes the chess board.
     */
    public ChessGameModel() {
        for (var i = 0; i < BOARD_ROW; i++) {
            for (var j = 0; j < BOARD_COL; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(
                        switch (i) {
                            case 0 ->
                                    switch (j) {
                                                case 1 -> Square.BLACK;
                                                case 3 -> Square.BLACK;
                                                default -> Square.NONE;
                                    };
                            case BOARD_ROW - 1 ->
                                                switch (j) {
                                                      case 1 -> Square.WHITE;
                                                      case 3 -> Square.WHITE;
                                                      default -> Square.NONE;
                                                 };
                            default -> Square.NONE;
                        }
                );
            }
        }
    }

    /**
     * Returns the read-only property for the square at the specified position.
     *
     * @param i the row index of the position
     * @param j the column index of the position
     * @return the read-only property for the square at the specified position
     */
    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    /**
     * Returns the square at the specified position.
     *
     * @param p the position
     * @return the square at the specified position
     */
    public Square getSquare(Position p) {
        return board[p.row()][p.col()].get();
    }

    /**
     * Sets the square at the current position
     *
     * @param p the position
     * @param square the square
     */
    private void setSquare(Position p, Square square) {
        board[p.row()][p.col()].set(square);
    }

    /**
     * Moves a chess piece from the "from" position to the "to" position.
     *
     * @param from the starting position
     * @param to   the destination position
     */
    public void move(Position from, Position to) {
        setSquare(to, getSquare(from));
        setSquare(from, Square.NONE);
    }

    /**
     * Checks if a chess piece can move from the "from" position to the "to" position.
     *
     * @param from the starting position
     * @param to   the destination position
     * @return true if the move is valid, false otherwise
     */
    public boolean canMove(Position from, Position to) {
        return isOnBoard(from) && isOnBoard(to) && !isEmpty(from) && isEmpty(to) && isBishopMove(from, to) &&
                isSafe(from,to);
    }


    /**
     * Checks if a position on the chess board is empty.
     *
     * @param p the position to check
     * @return true if the position is empty, false otherwise
     */
    public boolean isEmpty(Position p) {
        return getSquare(p) == Square.NONE;
    }


    /**
     * Checks if a position is within the bounds of the chess board.
     *
     * @param p the position to check
     * @return true if the position is on the board, false otherwise
     */
    public static boolean isOnBoard(Position p) {
        return 0 <= p.row() && p.row() < BOARD_ROW && 0 <= p.col() && p.col() < BOARD_COL;
    }

    /**
     * Checks if a move from the "from" position to the "to" position is a valid diagonal move.
     *
     * @param from the starting position
     * @param to   the destination position
     * @return true if the move is a valid diagonal(Bishops move) , false otherwise
     */
    public static boolean isBishopMove(Position from, Position to) {
       return isDiagnol(from.row(), from.col(), to.row(),to.col());
    }

    /**
     * Checks if a move from the "from" position to the "to" position is safe,
     * i.e., it does not go under attack of an opponent's piece.
     *
     * @param from the starting position
     * @param to   the destination position
     * @return true if the move is safe, false otherwise
     */
    public boolean isSafe(Position from , Position to){

                 for(var i = 0; i<BOARD_ROW;i++){
                     for(var j= 0; j<BOARD_COL;j++){
                         Position temp = new Position(i,j);
                         if((getSquare(from) == Square.WHITE) && (isDiagnol(to.row(), to.col(),i,j)) && (getSquare(temp)== Square.BLACK)){
                             return false;
                         }
                         else if ((getSquare(from) == Square.BLACK) && (isDiagnol(to.row(), to.col(),i,j)) && (getSquare(temp)== Square.WHITE)) {
                             return false;
                         }
                     }
                 }
                 return true;

    }

    /**
     * Checks if a move from the specified row and column to the specified row and column is a diagonal move.
     *
     * @param row the starting row
     * @param col the starting column
     * @param i   the destination row
     * @param j   the destination column
     * @return true if the move is a diagonal move, false otherwise
     */
    public static boolean isDiagnol(int row, int col, int i, int j){
        var rowDiff = Math.abs(row - i);
        var colDiff = Math.abs(col-j);
        return (rowDiff == colDiff);
    }

    /**
     * Returns a string representation of the chess board.
     *
     * @return a string representation of the chess board
     */
    public String toString() {
        var sb = new StringBuilder();
        for (var i = 0; i < BOARD_ROW; i++) {
            for (var j = 0; j < BOARD_COL; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Checks if the game is lost, i.e., there are no more moves left.
     *
     * @return true if the game is lost, false otherwise
     */
    public boolean isGameLost(){
        return (movesLeft()==0);
    }


    /**
     * Checks if the game is won, i.e., all the required pieces are in their correct positions.
     *
     * @return true if the game is won, false otherwise
     */
    public boolean isGameWon() {
        boolean whiteDone = getSquare(new Position(0,1)) == Square.WHITE && getSquare(new Position(0,3)) == Square.WHITE;
        boolean blackDone = getSquare(new Position(4,1)) == Square.BLACK && getSquare(new Position(4,3)) == Square.BLACK;

        return whiteDone && blackDone;
    }

    /**
     * Returns the name of the current player.
     *
     * @return the name of the current player
     */
    public String playerName(){
        return thisGame.getPlayerName();
    }

    /**
     * Updates and returns the number of moves left for the current player.
     *
     * @return the number of moves left
     */
    public int updateMovesLeft(){
        thisGame.decMoves();
        return movesLeft();
    }

    /**
     * Returns the number of moves left for the current player.
     *
     * @return the number of moves left
     */
    public int movesLeft(){
        return thisGame.getMovesLeft();

    }

    /**
     * Main method to test the ChessGameModel class.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        var model = new ChessGameModel();
        System.out.println(model);
    }

}
