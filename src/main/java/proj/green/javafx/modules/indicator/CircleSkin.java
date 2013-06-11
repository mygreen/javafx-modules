/*
 * CircleSkin.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Shape;


/**
 * BladeType=EllipseSkinのSkin。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class CircleSkin extends BusyIndicatorSkin {

    public CircleSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        super(control, behavior);
    }
    
    @Override
    public Shape createBlade(final double opacity, final int index) {
        
        //TODO: bindする
        
        final Circle blade = CircleBuilder.create()
                .radius(control.getWeight())
                .fill(control.getColor())
                .opacity(opacity)
                .build();
        return blade;
    }
}
