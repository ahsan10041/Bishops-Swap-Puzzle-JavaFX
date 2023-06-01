/**
 * This package contains utility classes for the Bishops Swapping Puzzle game.
 * It provides functionalities for saving and loading game data in JSON format.
 *
 * <p>The main class in this package is the {@link util.JsonHelper} class,
 * which contains methods for saving and loading game data.</p>
 *
 * <h2>Usage</h2>
 * To save a game object, use the {@link util.JsonHelper#saveGame(chessgame.Game)} ()} method.
 * To load a list of game objects, use the {@link util.JsonHelper#loadGame()} method.
 *
 * <h2>Example</h2>
 * Saving a game object:
 * <pre>{@code
 * Game game = new Game();
 * // Set game properties
 * util.JsonHelper.saveGame(game);
 * }</pre>
 *
 * Loading game objects:
 * <pre>{@code
 * List<Game> games = util.JsonHelper.loadGame();
 * // Process the list of games
 * }</pre>
 */
package util;
