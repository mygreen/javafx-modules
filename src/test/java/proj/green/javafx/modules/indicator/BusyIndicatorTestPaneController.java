/*
 * BusyIndicatorTestPaneController.java
 * created in 2013/06/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class BusyIndicatorTestPaneController {
    
    @FXML //  fx:id="myNode"
    private StackPane myNode;
    
    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private ResourceBundle resources;
    
    private BusyIndicatorPane indicatorPane;
    
    @FXML
    private void initialize() {
        
        // インジケータ用のpaneを追加
        indicatorPane = new BusyIndicatorPane();
        indicatorPane.setBusyIndicator(BusyIndicatorBuilder.create()
                .color(Color.BLUE)
                .build());
        
    }
    
    @FXML
    private void handleShowIndicator(final ActionEvent event) {
        
        indicatorPane.playFromStart();
        myNode.getChildren().add(indicatorPane);
        
        indicatorPane.setMessage("aaaaaaaaaa");
        
        Task<String> task = new Task<String>() {
            
            @Override
            protected String call() throws Exception {
                
                for(int i=0; i < 5; i++) {
                    System.out.println("task=" + i);
//                    for(int j =0; j < 1000000000; j++) {
////                        System.out.print("");
//                    }
                    Thread.sleep(1000);
                    indicatorPane.setMessage(String.format("count=%d", i));
                    
                }
                
                System.out.println("end");
                
                indicatorPane.stop();
                return "end";
            }
        };
        
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        // インジケータの終了
                        stopInidcator();
                        
                        executor.shutdown();
                    }
                });
        
        executor.submit(task);
//        
        
//        Callable<String> task = new Callable<String>() {
//            
//            @Override
//            public String call() throws Exception {
//                for(int i = 0; i < 5; i++) {
//                    System.out.println("task=" + i);
//                    Thread.sleep(1000);
//                    indicatorPane.setMessage(String.format("count=%d", i));
//                }
//                
//                System.out.println("end");
//                indicatorPane.stop();
//                myNode.getChildren().remove(indicatorPane);
//                return "end";
//            }
//        };
        
//        Thread task = new Thread() {
//            @Override
//            public void run() {
//                for(int i = 0; i < 5; i++) {
//                    System.out.println("task=" + i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    indicatorPane.setMessage(String.format("count=%d", i));
//                }
//                System.out.println("end");
//                myNode.getChildren().remove(indicatorPane);
//            }
//        };
//        
//        Platform.runLater(task);
        
        for(int i = 0; i < 1; i++) {
            System.out.println("task=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            indicatorPane.setMessage(String.format("count=%d", i));
        }
        System.out.println("end");
//        myNode.getChildren().remove(indicatorPane);
//        indicatorPane.stop();
        
        myNode.getChildren().remove(indicatorPane);
    }
    
    private void stopInidcator() {
        if(inidicatorPane != null) {
            myNode.getChildren().remove(indicatorPane);
            indicatorPane.stop();
            indicatorPane = null;
        }
    }
}
