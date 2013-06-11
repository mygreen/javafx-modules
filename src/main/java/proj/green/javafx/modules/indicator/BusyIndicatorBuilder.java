/*
 * BusyIndicatorBuilder.java
 * created in 2013/06/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.ParentBuilder;
import javafx.scene.paint.Color;
import javafx.util.Builder;


/**
 *
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
@SuppressWarnings("rawtypes")
public class BusyIndicatorBuilder extends ParentBuilder implements Builder<BusyIndicator> {
    
    private BladeType type;
    
    private Color color;
    
    private int size;
    
    private int radius;
    
    private int weight;
    
    private int count;
    
    private int speed;
    
    private double minOpacity;
    
    private double maxGaussian;
    
    public static BusyIndicatorBuilder create() {
        return new BusyIndicatorBuilder();
    }
    
    public BusyIndicatorBuilder() {
        this.type = BladeType.Tube;
        this.color = Color.web("#000000");
        this.size = 16;
        this.radius = 8;
        this.weight = 3;
        this.count = 12;
        this.speed = 96;
        this.minOpacity = 0.25;
        this.maxGaussian = 1.0;
    }
    
    @Override
    public BusyIndicator build() {
        
        final BusyIndicator indicator = new BusyIndicator();
        indicator.setType(type);
        indicator.setColor(color);
        indicator.setSize(size);
        indicator.setRadius(radius);
        indicator.setWeight(weight);
        indicator.setCount(count);
        indicator.setSpeed(speed);
        indicator.setMinOpacity(minOpacity);
        indicator.setMaxGaussian(maxGaussian);
        
        return indicator;
    }
    
    public BusyIndicatorBuilder type(BladeType type) {
        this.type = type;
        return this;
    }
    
    public BusyIndicatorBuilder typeTriangle() {
        return type(BladeType.Triangle);
    }
    
    public BusyIndicatorBuilder typeRectanble() {
        return type(BladeType.Rectangle);
    }
    
    public BusyIndicatorBuilder typeTube() {
        return type(BladeType.Tube);
    }
    
    public BusyIndicatorBuilder typePolygon() {
        return type(BladeType.Polygon);
    }
    public BusyIndicatorBuilder typeEllipse() {
        return type(BladeType.Ellipse);
    }
    
    public BusyIndicatorBuilder typeCircle() {
        return type(BladeType.Circle);
    }
    
    public BusyIndicatorBuilder typeArc() {
        return type(BladeType.Arc);
    }
    
    public BusyIndicatorBuilder color(Color color) {
        this.color = color;
        return this;
    }
    
    public BusyIndicatorBuilder size(int size) {
        this.size = size;
        return this;
    }
    
    public BusyIndicatorBuilder radius(int radius) {
        this.radius = radius;
        return this;
    }
    
    public BusyIndicatorBuilder weight(int weight) {
        this.weight = weight;
        return this;
    }
    
    public BusyIndicatorBuilder count(int count) {
        this.count = count;
        return this;
    }
    
    public BusyIndicatorBuilder speed(int speed) {
        this.speed = speed;
        return this;
    }
    
    public BusyIndicatorBuilder minOpacity(double minOpacity) {
        this.minOpacity = minOpacity;
        return this;
    }
    
    public BusyIndicatorBuilder maxGaussian(double maxGaussian) {
        this.maxGaussian = maxGaussian;
        return this;
    }
}
