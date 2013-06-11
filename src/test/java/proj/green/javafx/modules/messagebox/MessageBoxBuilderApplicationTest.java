/*
 * MessageBoxBuilderApplicationTest.java
 * created in 2013/06/03
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.messagebox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * {@link MessageBoxBuilder}のテスト用アプリケーション。
 *
 * @author T.TSUCHIE
 *
 */
public class MessageBoxBuilderApplicationTest extends Application {
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        
        primaryStage.setScene(new Scene(createPane()));
        
        primaryStage.show();
        
    }
    
    private Pane createPane() {
        
        final GridPane pane = GridPaneBuilder.create()
                .hgap(5)
                .vgap(5)
                .build();
        
        int colSize = 4;
        int rowSize = 4;
        for(int r=0; r < rowSize; r++) {
            for(int c=0; c < colSize; c++) {
                String text = "1";
                Button bt = new Button(text);
                addButtonEvent(bt, c, r);
                pane.add(bt, c, r);
            }
        }
        
        return pane;
    }
    
    private void addButtonEvent(final Button bt, final int colIndex, final int rowIndex) {
        
        final StringBuilder labelText = new StringBuilder();
        bt.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                
                MessageBoxBuilder builder = MessageBoxBuilder.create();
                if(rowIndex == 0) {
                    labelText.append("type=Infomation");
                    builder.iconInformation();
                    builder.title(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    builder.message(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    
                    if(colIndex == 0) {
                        builder.buttonYes(true)
                            .buttonNo();
                    } else if(colIndex == 1) {
                        builder.buttonOk(true)
                            .buttonCancel();
                    } else if(colIndex == 2) {
                        builder.buttonAbort()
                            .buttonIgnore();
                    } else if(colIndex == 3) {
                        builder.buttonYes(false)
                            .buttonNo(false)
                            .buttonOk(false)
                            .buttonCancel(false)
                            .buttonAbort(false)
                            .buttonIgnore(false)
                            .button(MessageBoxEvent.RETRY);
                    }
                    
                } else if(rowIndex == 1) {
                    labelText.append("type=error");
                    builder.iconError();
                    builder.title(String.format("colIndex=%d,rowIndex=%d:", colIndex, rowIndex));
                    builder.message(String.format("colIndex=%d,rowIndex=%d:", colIndex, rowIndex));
                    
                    if(colIndex == 0) {
                        // フォーカスのテスト
                        builder.buttonYes(true)
                            .buttonNo();
                        builder.forcusedButton(MessageBoxEvent.YES);
                        
                        builder.appendMessageWithNewLine("Yesボタンのフォーカスを充てる");
                        
                    } else if(colIndex == 1) {
                        builder.buttonYes(true)
                            .buttonNo();
                        builder.forcusedButton(MessageBoxEvent.OK);
                        builder.appendMessageWithNewLine("フォーカス対象のボタンがない場合");
                    
                    } else if(colIndex == 2) {
                        builder.buttonYes(true)
                            .buttonNo();
                        
                        builder.appendMessage("行の折り返し。日本語。あああああああああああああああああああああああああああああああああああ。");
                        builder.wrapMessageLength(20);
                        
                    } else if(colIndex == 3) {
                        builder.buttonYes(true)
                        .buttonNo();
                        
                        builder.appendMessage("12345678901234567890行の折り返し。英語。aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
                        builder.wrapMessageLength(20);
                    }
                    
                } else if(rowIndex == 2) {
                    labelText.append("type=question");
                    builder.iconQuestion();
                    builder.title(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    builder.message(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    
                    if(colIndex == 0) {
                        // ビープ音のテスト
                        builder.playBeep();
                        
                        builder.appendMessageWithNewLine("beep音のテスト");
                        
                    } else if(colIndex == 1) {
                        // 詳細メッセージのテスト
                        
                        builder.detailMessage("詳細メッセージのテスト。改行なし。あああああああああああああああああああああああああ");
                    
                    } else if(colIndex == 2) {
                        // 詳細メッセージのテスト
                        
                        builder.detailMessage("詳細メッセージのテスト。改行あり。\nあああああ\nああああああああ\nああああああああああああ");
                        
                    } else if(colIndex == 3) {
                        // 詳細メッセージのテスト
                        
                        builder.detailMessage("詳細メッセージのテスト。幅指定。あああああああああああああいいいいいい\nい\nい\nい\nい\nい\nい\nい\nい");
                        builder.detailMessageSize(300, 50);
                        
                    }
                    
                } else if(rowIndex == 3) {
                    labelText.append("type=warning");
                    builder.iconWarning();
                    builder.title(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    builder.message(String.format("colIndex=%d,rowIndex=%d", colIndex, rowIndex));
                    
                    if(colIndex == 0) {
                        // メッセージ＋詳細メッセージ
                        builder.playBeep();
                        builder.appendMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                            .wrapMessageLength(10);
                        builder.detailMessage("メッセージ＋詳細メッセージ。")
                            .appendDetailMessageWithNewLine("新しい行です");
                        
                    } else if(colIndex == 1) {
                        // 任意のノードのテスト
                        builder.message("任意のノード");
                        Label childNode = new Label("任意のノード（Label）");
                        builder.childNode(childNode);
                    
                    } else if(colIndex == 2) {
                        
                        
                    } else if(colIndex == 3) {
                        
                        
                    }
                    
                } else {
                    System.err.printf("not found rowIndex=%d\n", rowIndex);
                }
                
                final MessageBoxEvent res = builder.show();
                System.out.printf("ボタン「%s」が選択されました。\n", res.name());
            }
        });
        
        bt.setText(labelText.toString());
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
