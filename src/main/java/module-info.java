module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;
    requires jakarta.persistence;
    requires org.apache.commons.io;

    opens application.controllers to javafx.fxml;
    opens application.models to org.hibernate.orm.core, javafx.base,
            hibernate.entitymanager,jakarta.persistence;
    opens application.util to hibernate.entitymanager, jakarta.persistence, javafx.base;
    exports application;
}