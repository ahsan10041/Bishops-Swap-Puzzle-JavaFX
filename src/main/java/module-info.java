module homework.project.ahsan10041 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.tinylog.api;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens chessgame to javafx.fxml;
    exports chessgame;
}
