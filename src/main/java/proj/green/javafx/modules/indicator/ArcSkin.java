/*
 * ArcSkin.java
 * created in 2013/07/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.Shape;


/**
 * BladeType=ArcSkinのSkin。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class ArcSkin extends BusyIndicatorSkin {

    public ArcSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        super(control, behavior);
    }
    
    @Override
    public Shape createBlade(final double opacity, final int index) {
        
        //TODO: bindする
        
        // 弧の角度（長さ）は、隙間を考慮する
        double length = 180.0/control.getCount() - control.getWeight() - control.getRadius()*0.1;
        
        final Arc blade = ArcBuilder.create()
                .centerX(0.0)
                .centerY(0.0)
                .length(length)
                .radiusX(control.getRadius())
                .radiusY(control.getRadius())
                .stroke(control.getColor())
                .strokeWidth(control.getWeight())
                .fill(null)
                .opacity(opacity)
                .build();
        
        
        return blade;
    }
    
    @Override
    public double getBladeCenterX() {
        return 0.0;
    }
    
    @Override
    public double getBladeCenterY() {
        return 0.0;
    }
}
