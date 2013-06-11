/*
 * BusyIndicator.java
 * created in 2013/06/11
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.paint.Color;


/**
 * インジケータの本体。
 * <p>ブレードのカスタマイズをする場合は、スキン「{@link BusyIndicatorSkin}」の実装を指定します。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class BusyIndicator extends Parent {
    
    /**
     * ブレードのタイプ
     */
    private BladeType type = BladeType.Tube;
    
    /** ブレードの色 */
    private Color color = Color.web("#000000");
    
    /** ブレードのサイズ（長さ） */
    private int size = 16;
    
    /** ブレードの表示位置の半径 */
    private int radius = 8;
    
    /** ブレードの太さ */
    private int weight = 3;
    
    /** ブレードの枚数 */
    private int count = 12;
    
    /** 回転スピード（ミリ秒） */
    private int speed = 96;
    
    /** ブレードの透過色の最小値 */
    private double minOpacity = 0.25;
    
    /**
     * ぼかしの最大値。
     * <p>1.0の場合はぼかしはなし。
     */
    private double maxGaussian = 1.0;
    
    /** インジケータのノード自体の情報 */
    private BusyIndicatorSkin skin;
    
    /**
     * ブレードタイプごとのSkinの実装のマップ
     */
    private Map<BladeType, Class<?>> skinClassMap = new HashMap<BladeType, Class<?>>();
    {
        skinClassMap.put(BladeType.Triangle, TriangleSkin.class);
        skinClassMap.put(BladeType.Rectangle, RectangleSkin.class);
        skinClassMap.put(BladeType.Tube, TubeSkin.class);
        skinClassMap.put(BladeType.Polygon, PolygonSkin.class);
        skinClassMap.put(BladeType.Ellipse, EllipseSkin.class);
        skinClassMap.put(BladeType.Circle, CircleSkin.class);
        skinClassMap.put(BladeType.Arc, ArcSkin.class);
    }
    
    public BusyIndicator() {
        this(false);
    }
    
    /**
     * ノードを追加と同時にアニメーションをスタートするかどうか。
     * @param playStart
     */
    public BusyIndicator(boolean playStart) {
        //TODO: バインドするようにする
        if(playStart) {
            playFromStart();
            
        }
    }

    
    /**
     * インジケータのスキンを取得する
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public BusyIndicatorSkin getSkin() {
        if(skin == null) {
            //            this.skin = new BusyIndicatorSkin(this, new BusyIndicatorBehavior(this));
            try {
                final Class skinClass = skinClassMap.get(getType());
                final Class[] argTypes = new Class[]{BusyIndicator.class, BusyIndicatorBehavior.class};
                final Constructor constructor = skinClass.getConstructor(argTypes);
                final Object[] args = new Object[]{this, new BusyIndicatorBehavior(this)};
                
                this.skin = (BusyIndicatorSkin) constructor.newInstance(args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return skin;
    }
    
    /**
     * アニメーションを開始する
     * <p>初めからやり直す
     */
    public void playFromStart() {
        
        getChildren().remove(getSkin().getIndicator());
        getChildren().add(getSkin().getIndicator());
        getSkin().getBehavior().playFromStart();
    }
    
    /**
     * アニメーションを再開する
     * <p>一時停止している場合は、再開する。
     */
    public void play() {
        getSkin().getBehavior().play();

    }
    
    /***
     * アニメーションを一時停止する
     */
    public void pause() {
        getSkin().getBehavior().pause();
    }
    
    /**
     * アニメーションを終了する
     */
    public void stop() {
        getSkin().getBehavior().stop();
        getChildren().remove(getSkin().getIndicator());
    }
    
    public BladeType getType() {
        return type;
    }
    
    public void setType(BladeType type) {
        this.type = type;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public double getMinOpacity() {
        return minOpacity;
    }
    
    public double getMaxGaussian() {
        return maxGaussian;
    }
    
    public void setMaxGaussian(double maxGaussian) {
        this.maxGaussian = maxGaussian;
    }
    
    public void setMinOpacity(double minOpacity) {
        this.minOpacity = minOpacity;
    }
    
    public Map<BladeType, Class<?>> getSkinClassMap() {
        return skinClassMap;
    }
    
}
