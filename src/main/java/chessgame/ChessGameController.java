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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

import static chessgame.ChessGameMoveSelector.Phase;

public class ChessGameController {

    private Stage stage;
    private Scene scene;
    private Parent root;
//    private startPageController startpage = new startPageController();
//    private Game thisGame = new Game(startpage.getCurrentPlayer());

    @FXML
    private Label myPlayerName;

    @FXML
    private TextField movesLeft;

    @FXML
    private GridPane board;

    private ChessGameModel model = new ChessGameModel();

    private ChessGameMoveSelector selector = new ChessGameMoveSelector(model);

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
    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(25);
        piece.fillProperty().bind(createSquareBinding(model.squareProperty(i, j)));
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

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
        }
    }

    private ObjectBinding<Paint> createSquareBinding(ReadOnlyObjectProperty<Square> squareProperty) {
        return new ObjectBinding<Paint>() {
            {
                super.bind(squareProperty);
            }
            @Override
            protected Paint computeValue() {
                return switch (squareProperty.get()) {
                    case NONE -> Color.TRANSPARENT;
                    case BLACK -> Color.BLACK;
                    case WHITE -> Color.WHITE;
                };
            }
        };
    }

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

    private void hidePossibleMoves() {
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = getSquare(new Position(i, j));
                square.getStyleClass().remove("allowed");
            }
        }
    }


    private void showSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().add("selected");
    }

    private void hideSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().remove("selected");
    }

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }
    public void SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void undoPhase(ActionEvent event) throws IOException {
        hideSelection(selector.getFrom());
        hidePossibleMoves();
        selector.reset();
    }
}
