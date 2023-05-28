package chessgame;

import chessgame.model.ChessGameModel;
import chessgame.model.Position;
import chessgame.model.Square;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.tinylog.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import static chessgame.ChessGameMoveSelector.Phase;

/**
 * The controller class for the Chess Game application.
 * Handles user interaction and manages the game logic.
 */
public class ChessGameController {

    Alert alert = new Alert(AlertType.INFORMATION);

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ImageView imageView = new ImageView();

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label movesLeftLabel;


    @FXML
    private Label myPlayerName;

    @FXML
    private TextField movesLeft;

    @FXML
    private GridPane board;

    private ChessGameModel model = new ChessGameModel();

    private ChessGameMoveSelector selector = new ChessGameMoveSelector(model);

    /**
     * Initializes the Chess Game controller.
     * Sets up the initial state of the game board and handles phase changes.
     */
    @FXML
    private void initialize() {
        myPlayerName.setText((model.playerName()));
        String moves = String.valueOf(model.movesLeft());
        movesLeft.setText(moves);
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        selector.phaseProperty().addListener(this::showSelectionPhaseChange);
    }

    private void updatedMoves(){
        String moves = String.valueOf(model.updateMovesLeft());
        movesLeft.setText(moves);
    }

    /**
     * Creates a square on the game board with the given position.
     *
     * @param i The row index of the square.
     * @param j The column index of the square.
     * @return The created StackPane representing the square.
     */
    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");

        // Create an ImageView with the desired image
        ImageView imageView = new ImageView();

        // Bind the imageProperty of the ImageView based on the square property
        imageView.imageProperty().bind(createSquareBinding(model.squareProperty(i, j)));

        square.getChildren().add(imageView);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }


    /**
     * Creates an ObjectBinding for the square image based on the square property.
     *
     * @param squareProperty The ReadOnlyObjectProperty representing the square.
     * @return The ObjectBinding for the square image.
     */
    private ObjectBinding<Image> createSquareBinding(ReadOnlyObjectProperty<Square> squareProperty) {
        return new ObjectBinding<>() {
            {
                super.bind(squareProperty);
            }

            @Override
            protected Image computeValue() {
                switch (squareProperty.get()) {
                    case NONE:
                        return null; // No image for NONE state
                    case BLACK:
                        return new Image("/blackBishop.png"); // Set the path to black image
                    case WHITE:
                        return new Image("/whiteBishop.png"); // Set the path to white image
                }
                return null; // Default case, no image
            }
        };
    }

    /**
     * Handles the mouse click event on a game board square.
     * Updates the selected position and checks if a move can be made.
     *
     * @param event The MouseEvent representing the mouse click event.
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Logger.info("Click on square ({},{})", row, col);
        selector.select(new Position(row, col));
//        selector.checkResetPhase(new Position(row,col));
        if (selector.isReadyToMove()) {
            selector.makeMove();
            HandleGameOver();
        }
    }

    /**
     * Handles the phase change event in the move selector.
     * Updates the UI based on the new phase.
     *
     * @param value    The ObservableValue representing the new phase.
     * @param oldPhase The old phase.
     * @param newPhase The new phase.
     */
    private void showSelectionPhaseChange(ObservableValue<? extends Phase> value, Phase oldPhase, Phase newPhase) {
        switch (newPhase) {
            case SELECT_FROM -> {}
            case SELECT_TO -> {showSelection(selector.getFrom());
                              possibleMoves(selector.getFrom());}
            case READY_TO_MOVE -> {hideSelection(selector.getFrom());
                                    hidePossibleMoves();
                                    updatedMoves(); }
        }
    }

    /**
     * Displays the possible moves from the given position on the game board.
     *
     * @param position The position from which to display possible moves.
     */
    private void possibleMoves(Position position){
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                if(model.canMove(position,(new Position(i,j)))){
                    var square = getSquare(new Position(i,j));
                    square.getStyleClass().add("allowed");
                }
            }
        }
    }

    /**
     * Hides the possible moves displayed on the game board.
     */
    private void hidePossibleMoves() {
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = getSquare(new Position(i, j));
                square.getStyleClass().remove("allowed");
            }
        }
    }


    /**
     * Displays the selection effect on the square at the given position.
     *
     * @param position The position of the selected square.
     */
    private void showSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().add("selected");
    }

    /**
     * Hides the selection effect on the square at the given position.
     *
     * @param position The position of the deselected square.
     */
    private void hideSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().remove("selected");
    }

    /**
     * Retrieves the StackPane representing the square at the given position on the game board.
     *
     * @param position The position of the square.
     * @return The StackPane representing the square.
     * @throws AssertionError if the square is not found.
     */
    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }
    /**
     * Switches the scene to the start page.
     *
     * @param event The ActionEvent representing the button click event.
     * @throws IOException if an I/O error occurs.
     */
    public void SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Undoes the current piece selection
     *
     * @param event The ActionEvent representing the button click event.
     * @throws IOException if an I/O error occurs.
     */
    public void undoPhase(ActionEvent event) throws IOException {
        try{hideSelection(selector.getFrom());
        hidePossibleMoves();
        selector.reset();}
        catch (Exception e){

        }
    }

    /**
     * Handles the game over scenario by displaying an alert dialog.
     * Checks if the game is won or lost and shows the appropriate message.
     */
    public void HandleGameOver(){

        if(model.isGameWon()){
            alert.setTitle("Game Won");
            alert.setHeaderText(null);
            alert.setContentText("\tCongratulations! \nYou won the game.\n" +
                                 "Your Scores are Saved,\n" +
                                 "You can go back to start page by clicking on Quit Game");

            // Display the alert dialog
            alert.showAndWait();
            board.setDisable(true);
        }
        else if (model.isGameLost()) {
            alert.setTitle("Game LOST");
            alert.setHeaderText(null);
            alert.setContentText("!!!! GAME OVER !!!!\n You ran out of Moves.\n" +
                                "You can go back to start page by clicking on Quit Game ");

            // Display the alert dialog
            alert.showAndWait();
            board.setDisable(true);
        }

    }
}
