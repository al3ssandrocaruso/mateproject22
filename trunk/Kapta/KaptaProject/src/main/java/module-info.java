module kapta.classes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires core;
    requires com.google.zxing.javase;
    requires base45;
    requires cose.java;
    requires cbor;
    requires java.mail;


    opens kapta to javafx.fxml; //hi
    exports kapta;

    exports kapta.utils.dao;
    opens kapta.utils.dao to javafx.fxml;

    exports kapta.model;
    opens kapta.model to javafx.fxml;

    exports kapta.utils;
    opens kapta.utils to javafx.fxml;

    exports kapta.model.profiles;
    opens kapta.model.profiles to javafx.fxml;

    exports kapta.control.appcontroller;
    opens kapta.control.appcontroller to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone;
    opens kapta.control.guicontroller.interfaceone to javafx.fxml;

    exports kapta.control.guicontroller.interfacetwo;
    opens kapta.control.guicontroller.interfacetwo to javafx.fxml;

    exports kapta.utils.bean.beanin;
    opens kapta.utils.bean.beanin to javafx.fxml;

    exports kapta.utils.init;
    opens kapta.utils.init to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone.request;
    opens kapta.control.guicontroller.interfaceone.request to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone.item;
    opens kapta.control.guicontroller.interfaceone.item to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone.request.requestitem.user;
    opens kapta.control.guicontroller.interfaceone.request.requestitem.user to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone.request.requestitem.club;
    opens kapta.control.guicontroller.interfaceone.request.requestitem.club to javafx.fxml;

    exports kapta.utils.session;
    opens kapta.utils.session to javafx.fxml;

    exports kapta.control.guicontroller.interfaceone.register;
    opens kapta.control.guicontroller.interfaceone.register to javafx.fxml;
    exports kapta.utils.email;
    opens kapta.utils.email to javafx.fxml;
    exports kapta.utils.utils;
    opens kapta.utils.utils to javafx.fxml;
    exports kapta.utils.bean.beanout.jfx1;
    opens kapta.utils.bean.beanout.jfx1 to javafx.fxml;
    exports kapta.utils.bean.beanin.jfx1;
    opens kapta.utils.bean.beanin.jfx1 to javafx.fxml;
    exports kapta.utils.bean.beanin.jfx2;
    opens kapta.utils.bean.beanin.jfx2 to javafx.fxml;
    exports kapta.utils.bean;
    opens kapta.utils.bean to javafx.fxml;
}