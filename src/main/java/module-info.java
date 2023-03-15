module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
}