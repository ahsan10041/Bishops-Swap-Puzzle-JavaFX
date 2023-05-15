package chessgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class startPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Bishop's Dominion");
        stage.setScene(scene);
        stage.show();
    }


}
