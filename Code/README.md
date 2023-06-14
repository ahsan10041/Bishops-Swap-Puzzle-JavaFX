# Bishops Swapping Puzzle

This project is a puzzle game called "Bishops Swapping" that challenges players to swap the positions of black and white bishops on a game board. The game is implemented using Java programming language and the JavaFX framework for the user interface. Player and score information is stored using JSON (Jackson library).

## Game Rules

1. The game board consists of 5 rows and 4 columns.
2. There are 2 black bishops and 2 white bishops placed on the board initially.
3. The black bishops are placed in the top row, while the white bishops are placed in the bottom row.
4. The goal is to swap the positions of the black and white bishops on the board.
5. Bishops move according to the rules of chess.
6. A bishop cannot move to a square that is under attack by any piece of the opposite color.
7. Black and white pieces are not required to move in turn.
8. Any of the pieces can be moved in the first move.

## User Interface

The user interface consists of three screens:

1. **Start Page**: This is the first screen displayed when the game is started. The player is prompted to enter their name.
2. **Game UI**: After entering the player's name, the game board is displayed, and the player can make moves to solve the puzzle.
3. **High Scores**: This screen displays the top 10 results, including the date and time when each game was started/finished, the player's name, the number of moves made by the player during the game, and the outcome (solved or given up). The information is retrieved from a JSON file where the player and score information is stored.

## Dependencies

The project uses the following dependencies:

- JavaFX: A framework for building Java applications with a graphical user interface.
- JSON (Jackson library): Used to store player and score information in a JSON format.
- JUNIT 5(Jupiter): Used to test the functionality of the Game model, using Unit tests
- TinyLog 2: For Logging

## Plugins

The project uses the following plugins:

- Maven Javadoc Plugin : For Documentation.
- Maven JXR Plugin
- Maven CheckStyle Plugin
- Maven Surefire report Plugin
- JoCoCo Maven Plugin

## Implementation Details

The project is implemented using Java programming language, JavaFX framework, and JSON (Jackson library) for data storage. The game logic is implemented based on the rules described above, and the user interface is designed using JavaFX to provide an interactive gaming experience.

Player and score information is stored in a JSON file. Each game's details, including the date and time, player's name, number of moves, and outcome, are recorded and stored in the JSON file. The high scores screen retrieves this information and displays the top 10 results.

## Conclusion

"Bishops Swapping Puzzle" is an engaging game that challenges players to swap the positions of black and white bishops on a game board. The project utilizes Java, JavaFX, and JSON to provide a user-friendly interface and store player and score information. The high scores feature adds competitiveness and allows players to track their progress. Enjoy playing the game and aim for the top of the high score table!
