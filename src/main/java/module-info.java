module com.example.algo_pro4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algo_pro4 to javafx.fxml;
    exports com.example.algo_pro4;
}