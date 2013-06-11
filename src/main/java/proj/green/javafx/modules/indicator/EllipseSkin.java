/*
 * EllipseSkin.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Shape;


/**
 * BladeType=EllipseSkinのSkin。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class EllipseSkin extends BusyIndicatorSkin {

    public EllipseSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        super(control, behavior);
    }
    
    @Override
    public Shape createBlade(final double opacity, final int index) {
        
        //TODO: bindする
        
        final Ellipse blade = EllipseBuilder.create()
                .radiusX(control.getWeight()/2.0)
                .radiusY(control.getSize()/2.0)
                .fill(control.getColor())
                .opacity(opacity)
                .build();
        return blade;
    }
    
    @Override
    public double getBladeCenterY() {
        return -control.getRadius() - control.getSize()/2.0;
    }
}
