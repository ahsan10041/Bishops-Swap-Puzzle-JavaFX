package chessgame.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ChessGameModel {

    public static final int BOARD_ROW = 5;
    public static final int BOARD_COL = 4;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_ROW][BOARD_COL];

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

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(Position p) {
        return board[p.row()][p.col()].get();
    }

    private void setSquare(Position p, Square square) {
        board[p.row()][p.col()].set(square);
    }

    public void move(Position from, Position to) {
        setSquare(to, getSquare(from));
        setSquare(from, Square.NONE);
    }

    public boolean canMove(Position from, Position to) {
        return isOnBoard(from) && isOnBoard(to) && !isEmpty(from) && isEmpty(to) && isPawnMove(from, to) &&
                isSafe(from,to);
    }

    public boolean isEmpty(Position p) {
        return getSquare(p) == Square.NONE;
    }

    public static boolean isOnBoard(Position p) {
        return 0 <= p.row() && p.row() < BOARD_ROW && 0 <= p.col() && p.col() < BOARD_COL;
    }

    public static boolean isPawnMove(Position from, Position to) {
        var rowDiff = Math.abs(from.row() - to.row());
        var colDiff = Math.abs(from.col() - to.col());
        return rowDiff == colDiff;
    }



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

    public static boolean isDiagnol(int row, int col, int i, int j){
        var rowDiff = Math.abs(row - i);
        var colDiff = Math.abs(col-j);
        return (rowDiff == colDiff);
    }

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

    public static void main(String[] args) {
        var model = new ChessGameModel();
        System.out.println(model);
    }

}
