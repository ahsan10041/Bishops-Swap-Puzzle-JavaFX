package chessgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessGameApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        primaryStage.setTitle("Hello world");
        primaryStage.setScene(new Scene(root, 520 , 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
//        stage.setTitle("JavaFX Board Game Example");
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }

}
