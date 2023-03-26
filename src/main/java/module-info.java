module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires ojdbc8;
    requires java.sql;
    requires java.naming;

    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
    exports com.example.quiz.modules;
}