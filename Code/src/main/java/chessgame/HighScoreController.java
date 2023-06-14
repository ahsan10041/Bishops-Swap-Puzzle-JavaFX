package chessgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.JsonHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The controller class for the high score page.
 */
public class HighScoreController {

    /**
     * The table view that displays the high scores.
     */
    @FXML
    private TableView<Game> table;

    /**
     * The table column for the player name.
     */
    @FXML
    private TableColumn<Game, String> playerName;

    /**
     * The table column for the number of moves left.
     */
    @FXML
    private TableColumn<Game, Integer> movesLeft;

    /**
     * The stage for the scene.
     */
    private Stage stage;

    /**
     * The scene for the high score page.
     */
    private Scene scene;

    /**
     * Initializes the high score page.
     *
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void initialize() throws IOException {
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        movesLeft.setCellValueFactory(new PropertyValueFactory<>("movesLeft"));
        List<Game> games = JsonHelper.loadGame();
        ObservableList<Game> tableData = FXCollections.observableArrayList(games);
        table.setItems(tableData);
    }

    /**
     * Switches back to the start page.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void backButtonSwitch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
