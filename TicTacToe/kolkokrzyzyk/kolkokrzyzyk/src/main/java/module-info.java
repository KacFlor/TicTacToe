module com.example.kolkokrzyzyk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kolkokrzyzyk to javafx.fxml;
    exports com.example.kolkokrzyzyk;
}