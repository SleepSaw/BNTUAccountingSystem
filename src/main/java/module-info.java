module org.bntu.accounting.bntuaccountingsystem {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens org.bntu.accounting.bntuaccountingsystem to javafx.fxml;
    exports org.bntu.accounting.bntuaccountingsystem;
}