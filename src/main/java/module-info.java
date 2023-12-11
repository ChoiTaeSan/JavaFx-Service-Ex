module com.example.javafx_service_ex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_service_ex to javafx.fxml;
    exports com.example.javafx_service_ex;
}