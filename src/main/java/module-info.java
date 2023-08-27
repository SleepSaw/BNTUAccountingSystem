module org.bntu.accounting.bntuaccountingsystem {
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

    opens org.bntu.accounting.bntuaccountingsystem.controllers to javafx.fxml;
    opens org.bntu.accounting.bntuaccountingsystem.models to org.hibernate.orm.core, javafx.base,
            java.persistence,hibernate.entitymanager;
    exports org.bntu.accounting.bntuaccountingsystem;
    opens org.bntu.accounting.bntuaccountingsystem.util to hibernate.entitymanager, java.persistence, javafx.base, org.hibernate.orm.core;
}