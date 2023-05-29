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


public class HighScoreController {

    @FXML
    private TableView<Game> table;
    @FXML
    private TableColumn<Game, String> playerName;
    @FXML
    private TableColumn<Game, Integer> movesLeft;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() throws IOException {
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        movesLeft.setCellValueFactory(new PropertyValueFactory<>("movesLeft"));
        List<Game> games = JsonHelper.loadGame();
        ObservableList<Game> tableData = FXCollections.observableArrayList(games);
        table.setItems(tableData);
    }

    public void backButtonSwitch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
