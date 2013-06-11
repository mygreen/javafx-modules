/*
 * BusyIndicatorPane.java
 * created in 2013/06/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 *
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class BusyIndicatorPane extends StackPane {
    
    /***
     * パネルの背景色
     */
    private Color backgroundColor = Color.web("#FFFFFF");
    
    /**
     * パネルの透過色
     */
    private double backgroundOpacity = 0.7;
    
    /**
     * インジケータの本体
     */
    private BusyIndicator busyIndicator;
    
    /**
     * インジケータを配置するペイン
     */
    private Pane backgroundPane;
    
    /**
     * インジケータやメッセージ表示用のペイン
     */
    private VBox layoutPane;
    
    /**
     * メッセージを表示するペイン
     */
    private Label messageLabel;
    
    public BusyIndicatorPane() {
        setAlignment(Pos.CENTER);
        
        // 色の初期化
        this.backgroundPane = new Pane();
        backgroundPane.setOpacity(backgroundOpacity);
        backgroundPane.setStyle(String.format("-fx-background-color: %s;", convertWebColor(backgroundColor)));
        
        layoutPane = new VBox();
        layoutPane.setAlignment(Pos.CENTER);
        getChildren().add(layoutPane);
        
        this.messageLabel = new Label();
        messageLabel.setId("indicatorMessage");
    }
    
    /**
     * 色オブジェクトを16進数の Web形式に変換する
     * @param color
     * @return
     */
    private String convertWebColor(final Color color) {
        StringBuilder value = new StringBuilder();
        value.append("#")
            .append(String.format("%02X", (int)(color.getRed()*255)))
            .append(String.format("%02X", (int)(color.getGreen()*255)))
            .append(String.format("%02X", (int)(color.getBlue()*255)));
        
        return value.toString();
    }
    
    public void playFromStart() {
        
        // 前のノードを削除する
        if(busyIndicator == null) {
            throw new IllegalStateException("not instance busyIndicator");
        }
        
        if(backgroundPane != null) {
            getChildren().remove(backgroundPane);
        }
        
        if(layoutPane != null) {
            getChildren().remove(layoutPane);
        }
        
        if(busyIndicator != null) {
            layoutPane.getChildren().remove(busyIndicator);
        }
        
        if(messageLabel != null) {
            layoutPane.getChildren().remove(messageLabel);
        }
        
        // ノードを追加する
        getChildren().add(backgroundPane);
        
        layoutPane.getChildren().add(busyIndicator);
        layoutPane.getChildren().add(messageLabel);
        getChildren().add(layoutPane);
        
        busyIndicator.playFromStart();
        
        setVisible(true);
        
    }
    
    public void play() {
        if(busyIndicator == null) {
            throw new IllegalStateException("not instance busyIndicator");
        }
        
        busyIndicator.play();
    }
    
    public void pause() {
        if(busyIndicator == null) {
            throw new IllegalStateException("not instance busyIndicator");
        }
        
        busyIndicator.pause();
    }
    
    public void stop() {
        if(busyIndicator == null) {
            throw new IllegalStateException("not instance busyIndicator");
        }
        
        busyIndicator.stop();
        
        // ノードを削除する
        if(backgroundPane != null) {
            getChildren().remove(backgroundPane);
        }
        
        if(layoutPane != null) {
            getChildren().remove(layoutPane);
        }
        
        if(busyIndicator != null) {
            layoutPane.getChildren().remove(busyIndicator);
        }
        
        if(messageLabel != null) {
            layoutPane.getChildren().remove(messageLabel);
        }
        
        setVisible(false);
        
    }
    
    public String getMessage() {
        return messageLabel.getText();
    }
    
    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }
    
    public StringProperty messageProperty() {
        return messageLabel.textProperty();
    }
    
    public BusyIndicator getBusyIndicator() {
        return busyIndicator;
    }
    
    public void setBusyIndicator(BusyIndicator busyIndicator) {
        this.busyIndicator = busyIndicator;
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public double getBackgroundOpacity() {
        return backgroundOpacity;
    }
    
    public void setBackgroundOpacity(double backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }
    
}
