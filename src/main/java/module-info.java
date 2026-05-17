module drinkshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.controlsfx.controls;

    opens drinkshop.ui to javafx.fxml;
    exports drinkshop.ui;

    opens drinkshop.domain to  javafx.base;
    exports drinkshop.domain;

    exports drinkshop.repository;
    opens drinkshop.repository to org.mockito;

    exports drinkshop.service.validator;
    opens drinkshop.service.validator to org.mockito;
}