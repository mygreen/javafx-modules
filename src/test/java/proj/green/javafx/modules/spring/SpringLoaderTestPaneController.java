/*
 * SpringLoaderTestPaneController.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proj.green.javafx.modules.fxml.NodeAndController;
import proj.green.javafx.modules.spring.test.Prototype1Controller;
import proj.green.javafx.modules.spring.test.Prototype2Controller;
import proj.green.javafx.modules.spring.test.Prototype3Controller;
import proj.green.javafx.modules.spring.test.Singleton1Controller;
import proj.green.javafx.modules.spring.test.Singleton2Controller;
import proj.green.javafx.modules.spring.test.Singleton3Controller;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class SpringLoaderTestPaneController implements Initializable {
    
    @FXML
    private AnchorPane myNode;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
    
    @FXML
    public void handleSingleton1(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Singleton1Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Singleton1Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
    }
    
    @FXML
    public void handleSingleton2(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Singleton2Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Singleton2Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
        
    }
    
    @FXML
    public void handleSingleton3(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Singleton3Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Singleton3Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
    }
    
    @FXML
    public void handlePrototype1(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Prototype1Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Prototype1Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
    }
    
    @FXML
    public void handlePrototype2(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Prototype2Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Prototype2Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
    }
    
    @FXML
    public void handlePrototype3(final ActionEvent event) {
        
        Stage stage = new Stage();
        NodeAndController<AnchorPane, Prototype3Controller> nac =
                SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, Prototype3Controller.class);
        stage.setScene(new Scene(nac.getNode()));
        
        stage.show();
        nac.getController().callService();
    }
    
}
