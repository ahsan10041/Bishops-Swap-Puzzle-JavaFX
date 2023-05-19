package chessgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class startPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Game game = new Game();

    @FXML
    private TextField playerNameTextField;

    String PlayerName;

    public void SwitchToGame(ActionEvent event) throws IOException {
        PlayerName = playerNameTextField.getText();
        game.setPlayerName(PlayerName);
        Parent root = FXMLLoader.load(getClass().getResource("/ui2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Bishop's Dominion");
        stage.setScene(scene);
        stage.show();
    }

    public static Game getCurrentPlayer(){
        return game;
    }

}
