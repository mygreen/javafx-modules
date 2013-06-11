/*
 * PolygonSkin.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.PolygonBuilder;
import javafx.scene.shape.Shape;


/**
 * BladeType=PolygonのSkin。
 * <p>台形にする。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class PolygonSkin extends BusyIndicatorSkin {

    public PolygonSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        super(control, behavior);
    }
    
    @Override
    public Shape createBlade(final double opacity, final int index) {
        
        //TODO: bindする
        
        final Polygon blade = PolygonBuilder.create()
                .points(0.0d, 0.0d,
                        control.getWeight()/4.0, control.getSize()*1.0,
                        control.getWeight()/4.0*3, control.getSize()*1.0,
                        control.getWeight()*1.0, 0.0d)
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
