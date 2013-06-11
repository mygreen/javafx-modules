/*
 * BusyIndicatorTestApplication.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class BusyIndicatorTestApplication extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        
        GridPane root = new GridPane();
        root.setHgap(40);
        root.setVgap(40);
        
        BusyIndicator indicator1 = BusyIndicatorBuilder.create()
                .typeEllipse()
                .color(Color.BLUE)
                .radius(10)
                .weight(10)
                .size(30)
//                .maxGaussian(2.0)
                .build();
        
        root.add(indicator1, 0, 0);
        
        BusyIndicator indicator2 = new BusyIndicator();
        indicator2.setType(BladeType.Circle);
        indicator2.setRadius(20);
        
        root.add(indicator2, 0, 1);
        
        BusyIndicator indicator3 = new BusyIndicator();
        indicator3.setType(BladeType.Rectangle);
        indicator3.setRadius(10);
        
        root.add(indicator3, 1, 0);
        
        BusyIndicator indicator4 = BusyIndicatorBuilder.create()
                .type(BladeType.Tube)
                .radius(5)
                .size(8)
                .weight(2)
                .build();
        
        root.add(indicator4, 1, 1);
        
        BusyIndicator indicator5 = BusyIndicatorBuilder.create()
                .type(BladeType.Polygon)
                .radius(10)
                .size(20)
                .weight(4)
                .build();
        
        root.add(indicator5, 2, 0);
        
        BusyIndicator indicator6 = BusyIndicatorBuilder.create()
                .type(BladeType.Triangle)
                .radius(20)
                .size(20)
                .weight(5)
                .build();
        
        root.add(indicator6, 2, 1);
        
        BusyIndicator indicator7 = BusyIndicatorBuilder.create()
                .type(BladeType.Arc)
                .radius(20)
                .size(20)
                .count(8)
                .weight(5)
                .color(Color.DARKBLUE)
                .build();
        
        root.add(indicator7, 2, 2);
        
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setResizable(false);
        
        indicator1.playFromStart();
        indicator2.playFromStart();
        indicator3.playFromStart();
        indicator4.playFromStart();
        indicator5.playFromStart();
        indicator6.playFromStart();
        indicator7.playFromStart();
//        indicator7.pause();
        
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
