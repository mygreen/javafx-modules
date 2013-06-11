/*
 * SpringFxmlLoaderTestApplication.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proj.green.javafx.modules.fxml.NodeAndController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class SpringFxmlLoaderTestApplication extends Application {
    
    private ApplicationContext applicationContext;
    
    private SpringLoaderTestPaneController controller;
    
    @Override
    public void init() {
        
        try {
            applicationContext = new ClassPathXmlApplicationContext("TestApplicationContext.xml");
        } catch(Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        SpringFxmlTool.init(applicationContext);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        NodeAndController<AnchorPane, SpringLoaderTestPaneController> nac =
                SpringFxmlTool.loadNodeAndController(AnchorPane.class, SpringLoaderTestPaneController.class);
        
        primaryStage.setScene(new Scene(nac.getNode()));
        this.controller = nac.getController();
        
        primaryStage.show();
        primaryStage.requestFocus();
        
    }
    
    public void stop() {
        
        if(applicationContext != null && applicationContext instanceof AbstractApplicationContext) {
            // Springのコンテナの終了
            SpringFxmlTool.destroyAllProrotypeObject();
            
            
            ((AbstractApplicationContext) applicationContext).close();
            System.out.println("close");
        }
        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
