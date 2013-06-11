package proj.green.javafx.modules.indicator;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.util.Duration;

public class SampleIndicatorApplication extends Application {
    
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 400));
        
        GridPane g = new GridPane();
        
        ProgressIndicator p1 = new ProgressIndicator();
        p1.setPrefSize(100, 100);
//        p1.se
//        p1.set
        
        Rectangle r1 = RectangleBuilder.create()
                .width(10)
                .height(100)
                .fill(Color.BLUE)
                .opacity(0.8)
                .build();
//        g.add(r1, 1, 1);
//        g.add(createCircle(), 1, 1);
//        g.add(createAnimateCircle(), 1, 1);
//        g.add(createAnimateOval(), 1, 1);
        g.add(createAnimateTube(), 1, 1);
        
//        ProgressIndicator p2 = new ProgressIndicator();
//        p2.setPrefSize(50, 50);
//        p2.setProgress(0.25F);
//        
//        ProgressIndicator p3 = new ProgressIndicator();
//        p3.setPrefSize(50, 50);
//        p3.setProgress(0.5F);
//        
//        ProgressIndicator p4 = new ProgressIndicator();
//        p4.setPrefSize(50, 50);
//        p4.setProgress(1.0F);
//        
        g.add(p1, 1, 0);
//        g.add(p2, 0, 1);
//        g.add(p3, 1, 1);
//        g.add(p4, 2, 1);
        
        g.setHgap(40);
        g.setVgap(40);
        
        root.getChildren().add(g);
    }
    
    private Group createRectangle() {
        
        Group g = new Group();
        
        Rectangle r1 = RectangleBuilder.create()
                .width(10)
                .height(100)
                .fill(Color.BLUE)
                .opacity(0.8)
                .build();
        
//        RotateTransition rotate = RotateTransitionBuilder.create()
//                .node(r1)
//                .fromAngle(0)
//                .toAngle(45)
//                .build();
        Rotate rotate = RotateBuilder.create()
                
                .build();
        
        r1.getTransforms().add(new Rotate(45, 90, 90));
        
        g.getChildren().add(r1);
        
        return g;
    }
    
    private Group createCircle() {
        
        Group g = new Group();
        
        int bradeNum = 12;
        double angle = 360.0/bradeNum;
        
        for(int i=0; i < bradeNum; i++) {
        
            Rectangle rect = RectangleBuilder.create()
                    .width(10)
                    .height(50)
                    .fill(Color.BLUE)
                    .opacity(1.0 - i*(1.0/bradeNum))
                    .build();
            
            // Pibotは、widhtの半分のサイズにする必要がある。
            // heightは、半径のサイズ
            rect.getTransforms().add(new Rotate(i*angle, 5, -30));
            
            // ガウス（ぼかし）を入れる
            rect.setEffect(new GaussianBlur(1.5));
            
            g.getChildren().add(rect);
        }
        
        return g;
    }
    
    private Group createAnimateRect() {
        
        final Group g = new Group();
        
        final int bradeNum = 12;
        final double angle = 360.0/bradeNum;
        
        final Rectangle[] brades = new Rectangle[bradeNum];
        for(int i=0; i < bradeNum; i++) {
        
            Rectangle rect = RectangleBuilder.create()
                    .width(10)
                    .height(30)
                    .fill(Color.BLUE)
                    .opacity((i+1)*(1.0/bradeNum))
                    .build();
            
            // Pibotは、widhtの半分のサイズにする必要がある。
            // heightは、半径のサイズ
            rect.getTransforms().add(new Rotate(i*angle, 5, -30));
            
            // ガウス（ぼかし）を入れる
            rect.setEffect(new GaussianBlur(1.5));
            
            brades[i] = rect;
        }
        
        g.getChildren().addAll(brades);
        
        Timeline timeline = TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .build();
        
//        KeyValue[] keyValues = new KeyValue[bradeNum];
//        for(int i=0; i < bradeNum; i++) {
//            int k = Math.abs(i - 0);
//            brades[i].setOpacity(1.0 - k*(1.0/bradeNum));
//        }
//        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0),
//                keyValues));
        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0.2),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        
//                        int count = (int)((System.currentTimeMillis() / 100) % bradeNum);
//                        
//                        for(int i = 0; i < bradeNum; i++) {
//                             int k = Math.abs(i - count);
//                             brades[i].setOpacity(k * (1.0/bradeNum));
//                            
//                        }
//                        
//                    }
//            
//        }));
        
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        g.getTransforms().add(new Rotate(angle, 5, -30));
                        
                    }
            
        }));
        
        timeline.play();
        
//        RotateTransition rotateAnime = RotateTransitionBuilder.create()
//                .node(g)
//                .duration(Duration.seconds(1))
//                .fromAngle(0)
//                .toAngle(720)
//                .cycleCount(Timeline.INDEFINITE)
//                .autoReverse(false)
//                .build();
//        rotateAnime.play();
        
        return g;
    }
    
    /**
     * 角が丸い四角
     * @return
     */
    private Group createAnimateTube() {
        
        final Group g = new Group();
        
        final int bradeNum = 12;
        final double angle = 360.0/bradeNum;
        
        final Rectangle[] brades = new Rectangle[bradeNum];
        for(int i=0; i < bradeNum; i++) {
        
            Rectangle rect = RectangleBuilder.create()
                    .width(10)
                    .height(30)
                    .arcWidth(10)
                    .arcHeight(10)
                    .fill(Color.BLUE)
                    .opacity((i+1)*(1.0/bradeNum))
                    .build();
            
            // Pibotは、widhtの半分のサイズにする必要がある。
            // heightは、半径のサイズ
            rect.getTransforms().add(new Rotate(i*angle, 5, -30));
            
            brades[i] = rect;
        }
        
        g.getChildren().addAll(brades);
        
        Timeline timeline = TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .build();
        
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.08),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        g.getTransforms().add(new Rotate(angle, 5, -30));
                        
                    }
            
        }));
        
        timeline.play();
        
        return g;
    }
    
    private Group createAnimateCircle() {
        
        final Group g = new Group();
        
        final int bradeNum = 12;
        final double angle = 360.0/bradeNum;
        
        final Circle[] brades = new Circle[bradeNum];
        for(int i=0; i < bradeNum; i++) {
        
            Circle rect = CircleBuilder.create()
                    .radius(5)
                    .fill(Color.BLUE)
                    .opacity((i+1)*(1.0/bradeNum))
                    .build();
            
            // Pibotは、widhtの半分のサイズにする必要がある。
            // heightは、半径のサイズ
            rect.getTransforms().add(new Rotate(i*angle, 5, -30));
            
            // ガウス（ぼかし）を入れる
//            rect.setEffect(new GaussianBlur(1.5));
            
            brades[i] = rect;
        }
        
        g.getChildren().addAll(brades);
        
        Timeline timeline = TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .build();
        
//        KeyValue[] keyValues = new KeyValue[bradeNum];
//        for(int i=0; i < bradeNum; i++) {
//            int k = Math.abs(i - 0);
//            brades[i].setOpacity(1.0 - k*(1.0/bradeNum));
//        }
//        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0),
//                keyValues));
        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0.2),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        
//                        int count = (int)((System.currentTimeMillis() / 100) % bradeNum);
//                        
//                        for(int i = 0; i < bradeNum; i++) {
//                             int k = Math.abs(i - count);
//                             brades[i].setOpacity(k * (1.0/bradeNum));
//                            
//                        }
//                        
//                    }
//            
//        }));
        
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        g.getTransforms().add(new Rotate(angle, 5, -30));
                        
                    }
            
        }));
        
        timeline.play();
        
//        RotateTransition rotateAnime = RotateTransitionBuilder.create()
//                .node(g)
//                .duration(Duration.seconds(1))
//                .fromAngle(0)
//                .toAngle(720)
//                .cycleCount(Timeline.INDEFINITE)
//                .autoReverse(false)
//                .build();
//        rotateAnime.play();
        
        return g;
    }
    
    private Group createAnimateOval() {
        
        final Group g = new Group();
        
        final int bradeNum = 12;
        final double angle = 360.0/bradeNum;
        
        final Ellipse[] brades = new Ellipse[bradeNum];
        for(int i=0; i < bradeNum; i++) {
        
            Ellipse rect = EllipseBuilder.create()
                    .radiusX(10)
                    .radiusY(20)
                    .fill(Color.BLUE)
                    .opacity((i+1)*(1.0/bradeNum))
                    .build();
            
            // Pibotは、widhtの半分のサイズにする必要がある。
            // heightは、半径のサイズ
//            rect.getTransforms().add(new Rotate(i*angle, 5, -30));
            rect.getTransforms().add(new Rotate(i*angle, 0, -50));
            
            // ガウス（ぼかし）を入れる
//            rect.setEffect(new GaussianBlur(1.5));
            
            brades[i] = rect;
        }
        
        g.getChildren().addAll(brades);
        
        Timeline timeline = TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .build();
        
//        KeyValue[] keyValues = new KeyValue[bradeNum];
//        for(int i=0; i < bradeNum; i++) {
//            int k = Math.abs(i - 0);
//            brades[i].setOpacity(1.0 - k*(1.0/bradeNum));
//        }
//        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0),
//                keyValues));
        
//        timeline.getKeyFrames().add(new KeyFrame(
//                Duration.seconds(0.2),
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        
//                        int count = (int)((System.currentTimeMillis() / 100) % bradeNum);
//                        
//                        for(int i = 0; i < bradeNum; i++) {
//                             int k = Math.abs(i - count);
//                             brades[i].setOpacity(k * (1.0/bradeNum));
//                            
//                        }
//                        
//                    }
//            
//        }));
        
        // 楕円の時は、中心がずれるので注意
        
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        g.getTransforms().add(new Rotate(angle, 0, -50));
                        
                    }
            
        }));
        
        timeline.play();
        
//        RotateTransition rotateAnime = RotateTransitionBuilder.create()
//                .node(g)
//                .duration(Duration.seconds(1))
//                .fromAngle(0)
//                .toAngle(720)
//                .cycleCount(Timeline.INDEFINITE)
//                .autoReverse(false)
//                .build();
//        rotateAnime.play();
        
        return g;
    }
    
    public double getSampleWidth() {
        return 400;
    }
    
    public double getSampleHeight() {
        return 400;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
