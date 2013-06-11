/*
 * BusyIndicatorTestApplication.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import java.io.InputStream;

import proj.green.javafx.modules.fxml.CustomFxmlTool;
import proj.green.javafx.modules.fxml.NodeAndController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class BusyIndicatorTestApplication2 extends Application {
    
    private BusyIndicatorTestPaneController controller;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        
        NodeAndController<StackPane, BusyIndicatorTestPaneController> nac =
                CustomFxmlTool.loadNodeAndController(StackPane.class, BusyIndicatorTestPaneController.class);
        this.controller = nac.getController();
        
        primaryStage.setScene(new Scene(nac.getNode()));
        primaryStage.setResizable(false);
        
        primaryStage.show();
        primaryStage.requestFocus();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
