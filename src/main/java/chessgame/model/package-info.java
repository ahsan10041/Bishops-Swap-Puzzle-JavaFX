/**
 * This package contains the model classes for the Bishops Swapping Puzzle game.
 * The model classes represent the game logic and entities used in the game.
 *
 * <p>The main classes in this package are:</p>
 * <ul>
 *   <li>{@link chessgame.model.ChessGameModel} - Represents the model of a chess game and provides game-related operations.</li>
 *   <li>{@link chessgame.model.Position} - Represents a position on a chess board with row and column coordinates.</li>
 *   <li>{@link chessgame.model.Square} - Represents the possible states of a chess square on the game board.</li>
 * </ul>
 *
 * <p>The {@link chessgame.model.ChessGameModel} class handles the game logic, including the chess board setup, move validation, and game outcome determination.</p>
 *
 * <p>The {@link chessgame.model.Position} class represents a position on the chess board with row and column coordinates.</p>
 *
 * <p>The {@link chessgame.model.Square} enum represents the possible states of a chess square on the game board, including empty, black piece, and white piece.</p>
 *
 * <h2>Usage</h2>
 * To use the model classes in your game implementation, create instances of the {@link chessgame.model.ChessGameModel} class,
 * and utilize its methods for managing the game state and performing game-related operations.
 *
 * <h2>Example</h2>
 * Creating a new ChessGameModel object:
 * <pre>{@code
 * ChessGameModel gameModel = new ChessGameModel();
 * }</pre>
 *
 * Accessing the game board and performing moves:
 * <pre>{@code
 * Square square = gameModel.getSquare(new Position(2, 1));
 * ReadOnlyObjectProperty<Square> squareProperty = gameModel.squareProperty(2, 1);
 * gameModel.move(new Position(0, 1), new Position(1, 2));
 * }</pre>
 *
 * <h2>Classes</h2>
 * This package contains the following classes:
 * <ul>
 *   <li>{@link chessgame.model.ChessGameModel}</li>
 *   <li>{@link chessgame.model.Position}</li>
 *   <li>{@link chessgame.model.Square}</li>
 * </ul>
 */
package chessgame.model;
