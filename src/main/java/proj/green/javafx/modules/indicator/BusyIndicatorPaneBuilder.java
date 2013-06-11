/*
 * BusyIndicatorPaneBuilder.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.ParentBuilder;
import javafx.scene.paint.Color;
import javafx.util.Builder;


/**
 * インジケータパネルのビルダクラス
 *
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
@SuppressWarnings("rawtypes")
public class BusyIndicatorPaneBuilder extends ParentBuilder implements Builder<BusyIndicatorPane> {
    
    private Color backgroundColor;
    
    private double backgroundOpacity;
    
    private String message;
    
    private BusyIndicator busyIndicator;
    
    public static BusyIndicatorPaneBuilder create() {
        return new BusyIndicatorPaneBuilder();
    }
    
    public BusyIndicatorPaneBuilder() {
        this.backgroundColor = Color.web("#FFFFFF");
        this.backgroundOpacity = 0.7;
        this.message = null;
        this.busyIndicator = null;
    }
    
    public BusyIndicatorPaneBuilder backgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    public BusyIndicatorPaneBuilder backgroundOpacity(double backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
        return this;
    }
    
    public BusyIndicatorPaneBuilder message(String message) {
        this.message = message;
        return this;
    }
    
    public BusyIndicatorPaneBuilder busyIndicator(BusyIndicator busyIndicator) {
        this.busyIndicator = busyIndicator;
        return this;
    }
    
    @Override
    public BusyIndicatorPane build() {
        
        BusyIndicatorPane busyIndicatorPane = new BusyIndicatorPane();
        busyIndicatorPane.setBackgroundColor(backgroundColor);
        busyIndicatorPane.setBackgroundOpacity(backgroundOpacity);
        busyIndicatorPane.setMessage(message);
        busyIndicatorPane.setBusyIndicator(busyIndicator);
        
        return busyIndicatorPane;
    }
}
