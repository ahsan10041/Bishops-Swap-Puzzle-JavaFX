package chessgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The controller class for the Start Page of the chess game.
 * Handles user interactions and navigation between scenes.
 */
public class StartPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Game game = new Game();

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Button exitGameButton;

    @FXML
    private Label warningLabel;

    String PlayerName;


    /**
     * Switches to the game scene when the "Start Game" button is clicked.
     * Validates the player name input and initializes the game.
     *
     * @param event The action event triggered by clicking the "Start Game" button.
     * @throws IOException If an I/O error occurs while loading the game scene.
     */
    public void SwitchToGame(ActionEvent event) throws IOException {

        if(playerNameTextField.getText().isBlank()){
            warningLabel.setText("Please Input Your Name");
        }
        else {
            PlayerName = playerNameTextField.getText();
            game.setPlayerName(PlayerName);
            game.setMovesLeft(50);
            Parent root = FXMLLoader.load(getClass().getResource("/ui2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Bishop's Dominion");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Switches to the high scores scene when the "High Scores" button is clicked.
     *
     * @param event The action event triggered by clicking the "High Scores" button.
     * @throws IOException If an I/O error occurs while loading the high scores scene.
     */
    public void showHighScores(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/highscores.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Bishop's Dominion");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exits the game when the "Exit Game" button is clicked.
     *
     * @param event The action event triggered by clicking the "Exit Game" button.
     * @throws IOException If an I/O error occurs while closing the application window.
     */
    public void exitGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitGameButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Returns the current player's game instance.
     *
     * @return The current player's game instance.
     */
    public static Game getCurrentPlayer(){
        return game;
    }

}
