/*
 * BusyIndicatorSkin.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;


/**
 * インジケータのノードを保持する抽象クラス。
 * <p>ブレードのタイプにより実装が異なる。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public abstract class BusyIndicatorSkin {
    
    protected final BusyIndicator control;
    
    protected final BusyIndicatorBehavior behavior;
    
    /***
     * インジケータの実体
     */
    private Node indicator;
    
    public BusyIndicatorSkin(final BusyIndicator control, final BusyIndicatorBehavior behavior) {
        this.control = control;
        this.behavior = behavior;
        
        init();
    }
    
    public BusyIndicatorBehavior getBehavior() {
        return behavior;
    }
    
    protected void init() {
        this.indicator = createIndicator();
    }
    
    /**
     * インジケータ用の図形を作成する
     * @return
     */
    protected Node createIndicator() {
        
        Group g = new Group();
        final double angle = getItemAngle();
        
        final double iOpacity = (1.0 - control.getMinOpacity()) / control.getCount();
        final double iGaussian = (control.getMaxGaussian() - 1.0) / control.getCount();
        
        for(int i=0; i < control.getCount(); i++) {
            
            // 色が薄いものから作成していく。
            double opacity = i*iOpacity + control.getMinOpacity();
            final Shape blade = createBlade(opacity, i);
            
            // ぼかしを入れる
            if(iGaussian != 0.0) {
                double gaussian = 1.0 + (control.getCount() - i - 1)*iGaussian;
                blade.setEffect(new GaussianBlur(gaussian));
            }
            
            // ブレードを回転させる
            blade.getTransforms().add(new Rotate(i*angle, getBladeCenterX(), getBladeCenterY()));
            
            g.getChildren().add(blade);
        }
        
        return g;
        
    }
    
    /**
     * ブレードの１枚の回転角度を取得する
     * @return
     */
    public double getItemAngle() {
        //補正を書ける
        return 360.0 / control.getCount();
    }
    
    /**
     * インジケータの元となる１枚のブレードを取得する
     * @param opacity ブレードの透過率
     * @param index ブレードのインデックス
     * @return
     */
    protected abstract Shape createBlade(double opacity, int index);
    
    /**
     * ブレードのX座標の中心を取得する。
    * <p>デフォルトは「0.0」。
     * <p>Rectangleなど、中心は、「幅/2」であるため。
     * @return
     */
    public double getBladeCenterX() {
        return 0.0;
    }
    
   /**
    * ブレードのY座標の中心を取得する。
    * <p>デフォルトは「- radius(半径)」。
    * <p>Ellipseなど、中心は、「サイズ/2」であるため。
    * @return
    */
   public double getBladeCenterY() {
       return -control.getRadius();
   }
    
    public Node getIndicator() {
        return indicator;
    }
    
}
