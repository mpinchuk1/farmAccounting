open module org.example.stasInduk {
    exports org.example.dao;
    requires javafx.base;
    requires java.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.bytebuddy;
    requires java.xml.bind;

    exports org.example.entity;

    provides org.example.dao.OrdersDao with org.example.dao.OrdersDaoImpl;
}