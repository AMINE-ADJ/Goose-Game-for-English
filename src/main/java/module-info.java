module tp {
    requires javafx.controls;
    requires javafx.fxml;

    exports tp.Controllers;
    exports tp.Noyeau;
    exports tp.Ui;
    opens tp.Controllers to javafx.fxml;
    opens tp.Ui to javafx.fxml;
    opens tp.Noyeau to javafx.fxml;
}