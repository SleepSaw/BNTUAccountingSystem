module org.bntu.accounting.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;
    requires java.persistence;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;

    exports application;
    opens application.controllers to javafx.fxml;
    opens application.models to org.hibernate.orm.core, javafx.base,
            java.persistence,hibernate.entitymanager;
    opens application.util to hibernate.entitymanager, java.persistence, javafx.base, org.hibernate.orm.core;
}