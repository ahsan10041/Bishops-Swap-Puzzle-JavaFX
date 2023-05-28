package chessgame.model;

/**
 * The Position class represents a position on a chess board.
 * It consists of a row and column coordinate.
 */
public record Position(int row, int col) {
    /**
     * Returns a string representation of the Position object.
     *
     * @return a string representation of the Position object
     */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}