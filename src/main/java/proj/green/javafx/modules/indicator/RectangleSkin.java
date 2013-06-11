/*
 * RectangleSkin.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.shape.Shape;


/**
 * BladeType=RectangleのSkin。
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class RectangleSkin extends BusyIndicatorSkin {

    public RectangleSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        super(control, behavior);
    }
    
    @Override
    public Shape createBlade(final double opacity, final int index) {
        
        //TODO: bindする
        
        final Rectangle blade = RectangleBuilder.create()
                .width(control.getWeight())
                .height(control.getSize())
                .fill(control.getColor())
                .opacity(opacity)
                .build();
        return blade;
    }
    
    @Override
    public double getBladeCenterX() {
        return control.getWeight()/2.0;
    }
}
