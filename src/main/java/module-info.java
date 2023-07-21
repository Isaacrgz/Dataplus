module com.dataplus {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive javafx.graphics;

    opens com.dataplus.controllers to javafx.fxml;
    exports com.dataplus.controllers;
    opens com.dataplus to java.sql;
    exports com.dataplus;
    exports com.dataplus.models;
}
