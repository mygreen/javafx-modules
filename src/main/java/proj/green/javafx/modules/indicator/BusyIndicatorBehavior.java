/*
 * BusyIndicatorBehavior.java
 * created in 2013/06/12
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.indicator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


/**
 * インジケータの振る舞いを設定する。
 * <p>アニメーションで回転させる。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class BusyIndicatorBehavior {
    
    private final BusyIndicator control;
    
    /**
     * 振る舞いとなるアニメーションを設定する
     */
    private Animation animation;
    
    public BusyIndicatorBehavior(final BusyIndicator contorl) {
        this.control = contorl;
   }
    
    public void playFromStart() {
        // 前回のが残っていたら、停止＆破棄する
        stop();
        this.animation = createBehavior();
        
        animation.playFromStart();
    }
    
    public void play() {
        
        if(animation != null) {
            animation.play();            
        } else {
            playFromStart();
        }
        
    }
    
    public void pause() {
        if(animation != null) {
            animation.pause();
        }
    }
    
    public void stop() {
        
        if(animation != null) {
            animation.stop();
            this.animation = null;
        }
        
    }
    
    /**
     * ビヘイビア用のアニメーションを取得する。
     * @return
     */
    protected Animation createBehavior() {
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setDelay(Duration.seconds(0));
        
        final Node indicator = control.getSkin().getIndicator();
        
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(control.getSpeed()),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        indicator.getTransforms().add(
                                new Rotate(control.getSkin().getItemAngle(), 0, -control.getRadius()));
                    }
                }));
        
        return timeline;
    }
    
}
