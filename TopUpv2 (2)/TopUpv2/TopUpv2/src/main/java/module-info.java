module org.example.topupgameonline {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.topupgameonline to javafx.fxml;
    exports org.example.topupgameonline;
}