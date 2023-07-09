module org.bntu.accounting.bntuaccountingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens org.bntu.accounting.bntuaccountingsystem.controllers to javafx.fxml;
    opens org.bntu.accounting.bntuaccountingsystem.models to javafx.base;
    exports org.bntu.accounting.bntuaccountingsystem;
}