package chessgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The ChessGameApplication class is the entry point for the Chess Game application.
 * It extends the JavaFX Application class and handles the initialization and startup of the application.
 */
public class ChessGameApplication extends Application {

        /**
         * The start method is called when the application is launched.
         * It loads the startpage.fxml file, sets up the primary stage, and displays the main scene.
         *
         * @param primaryStage the primary stage of the application
         * @throws IOException if an I/O error occurs while loading the startpage.fxml file
         */
        @Override
        public void start (Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        primaryStage.setTitle("BISHOP's DOMINION ");
        primaryStage.setScene(new Scene(root, 570, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
