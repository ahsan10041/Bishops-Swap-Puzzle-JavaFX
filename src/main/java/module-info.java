module homework.project.ahsan10041 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;

    opens chessgame to javafx.fxml;
    exports chessgame;
}
