package chessgamemodelTest;

import chessgame.model.ChessGameModel;
import chessgame.model.Position;
import chessgame.model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameModelTest {

    private ChessGameModel chessGameModel;

    @BeforeEach
    public void setup() {
        chessGameModel = new ChessGameModel();
    }

    @Test
    public void testSquareProperty() {
        assertEquals(Square.BLACK, chessGameModel.squareProperty(0, 1).get());
        assertEquals(Square.NONE, chessGameModel.squareProperty(1, 1).get());
        assertEquals(Square.WHITE, chessGameModel.squareProperty(4, 1).get());
    }

    @Test
    public void testGetSquare() {
        assertEquals(Square.BLACK, chessGameModel.getSquare(new Position(0, 1)));
        assertEquals(Square.NONE, chessGameModel.getSquare(new Position(1, 1)));
        assertEquals(Square.WHITE, chessGameModel.getSquare(new Position(4, 1)));
    }

    @Test
    public void testMove() {
        Position from = new Position(0, 1);
        Position to = new Position(1, 1);
        chessGameModel.move(from, to);
        assertEquals(Square.NONE, chessGameModel.getSquare(from));
        assertEquals(Square.BLACK, chessGameModel.getSquare(to));
    }

    @Test
    public void testCanMove() {
        Position validFrom = new Position(0, 1);
        Position validTo = new Position(1, 2);
        Position invalidTo = new Position(3, 2);

        assertTrue(chessGameModel.canMove(validFrom, validTo));
        assertFalse(chessGameModel.canMove(validFrom, invalidTo));
    }

    @Test
    public void testIsEmpty() {
        Position emptyPosition = new Position(1, 1);
        Position nonEmptyPosition = new Position(0, 1);

        assertTrue(chessGameModel.isEmpty(emptyPosition));
        assertFalse(chessGameModel.isEmpty(nonEmptyPosition));
    }

    @Test
    public void testIsOnBoard() {
        Position validPosition = new Position(3, 2);
        Position invalidPosition = new Position(5, 1);

        assertTrue(chessGameModel.isOnBoard(validPosition));
        assertFalse(chessGameModel.isOnBoard(invalidPosition));
    }

    @Test
    public void testIsBishopMove() {
        Position fromValidMove = new Position(0, 1);
        Position toValidMove = new Position(1, 2);
        Position toInValidMove = new Position(2,1);

        assertTrue(chessGameModel.isBishopMove(fromValidMove, toValidMove));
        assertFalse(chessGameModel.isBishopMove(fromValidMove, toInValidMove));
    }

    @Test
    public void testIsSafe() {
        Position from = new Position(0, 1);
        Position to = new Position(2, 3);

        assertFalse(chessGameModel.isSafe(from, to));
    }

    @Test
    public void testIsDiagonal() {
        int row = 0;
        int col = 1;
        int diagonalRow = 2;
        int diagonalCol = 3;

        assertTrue(chessGameModel.isDiagnol(row, col, diagonalRow, diagonalCol));
    }


    @Test
    public void testIsGameWon() {
        assertFalse(chessGameModel.isGameWon());
    }





}
