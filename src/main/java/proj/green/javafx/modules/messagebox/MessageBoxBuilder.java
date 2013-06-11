/*
 * MessageBoxBuilder.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.messagebox;

import java.awt.Toolkit;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import proj.green.javafx.modules.fxml.NodeAndController;
import proj.green.javafx.modules.fxml.CustomFxmlTool;


/**
 * メッセージボックスを組み立てるクラス。
 * <p>メッセージダイアログの返却値を返すために、JavaFX用のBuilderインタフェースは実装していない。
 * 
 * <pre>
 * MessageBoxEvent ans = MessageBoxBulder.create()
 *     .icon(MessageBoxIcon.QUESTION)
 *     .title("daialog title")
 *     .message("message").appendMessage("append")
 *     .buttonYes().buttonNo()
 *     .show();
 * </pre>
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class MessageBoxBuilder {
    
    private MessageBoxIcon icon;
    
    private String title;
    
    private StringBuilder message;
    
    private int messageWrapLength;
    
    private StringBuilder detailMessage;
    
    private int detailMessageWidth = 0;
    
    private int detailMessageHeight = 0;
    
    private Set<MessageBoxEvent> buttons;
    
    private Set<MessageBoxEvent> defaultButtons;
    
    private MessageBoxEvent forcusButton;
    
    private boolean playBeep;
    
    private Node childNode;
    
    private String styleSheet;
    
    private MessageBoxBuilder() {
        // 初期値
        this.title = "";
        this.message = new StringBuilder(50);
        this.messageWrapLength = -1;
        this.detailMessage = new StringBuilder(0);
        this.detailMessageWidth = 250;
        this.detailMessageHeight = 100;
        this.icon = MessageBoxIcon.INFORMATION;
        this.buttons = new LinkedHashSet<>();
        this.defaultButtons = new LinkedHashSet<>();
        this.playBeep = false;
        this.childNode = null;
        this.styleSheet = null;
        
    }
    
    /**
     * ビルダのインスタンスを作成する。
     * @return
     */
    public static MessageBoxBuilder create() {
        return new MessageBoxBuilder();
    }
    
    /**
     * タイトルの設定
     * @param title
     * @return
     */
    public MessageBoxBuilder title(final String title) {
        if(title == null || title.isEmpty()) {
            return this;
        }
        
        this.title = title;
        return this;
    }
    
    /** 
     * メッセージ本文の設定 
     * @param str メッセージ
     */
    public MessageBoxBuilder message(final String str) {
        
        if(str == null || str.isEmpty()) {
            return this;
        }
        
        this.message = new StringBuilder(str);
        return this;
    }
    
    /**
     * メッセージを追加する。
     * @param str メッセージ
     * @return
     */
    public MessageBoxBuilder appendMessage(final String str) {
        
        if(str == null || str.isEmpty()) {
            return this;
        }
        
        this.message.append(str);
        return this;
    }
    
    /**
     * メッセージに改行を加えて追加する。
     * @param str メッセージ
     * @return
     */
    public MessageBoxBuilder appendMessageWithNewLine(final String str) {
        
        if(str == null || str.isEmpty()) {
            return this;
        }
        
        if(this.message.length() > 0) {
            this.message.append(System.lineSeparator());
        }
        
        this.message.append(str);
        
        return this;
    }
    
    /**
     * メッセージの折り返し行数を設定する。
     * @param wrapLength 折り返しの文字数。0以下の場合折り返しはしない。
     * @return
     */
    public MessageBoxBuilder wrapMessageLength(int wrapLength) {
        this.messageWrapLength = wrapLength;
        return this;
    }
    
    /**
     * 詳細メッセージの設定。
     * 
     * @param detail 詳細メッセージの設定
     * @return
     */
    public MessageBoxBuilder detailMessage(final String detail) {
        
        if(detail == null || detail.isEmpty()) {
            return this;
        }
        
        this.detailMessage = new StringBuilder(detail);
        return this;
        
    }
    
    /**
     * 詳細メッセージを追加する。
     * 
     * @param detail 詳細メッセージの設定
     * @param width テキストエリアの幅
     * @param height テキストエリアの高さ
     * @return
     */
    public MessageBoxBuilder appendDetailMessage(final String detail) {
        
        if(detail == null || detail.isEmpty()) {
            return this;
        }
        
        this.detailMessage.append(detail);
        return this;
        
    }
    
    /**
     * メッセージに改行を加えて追加する。
     * @param detail 詳細メッセージの設定
     * @return
     */
    public MessageBoxBuilder appendDetailMessageWithNewLine(final String detail) {
        
        if(detail == null || detail.isEmpty()) {
            return this;
        }
        
        if(this.detailMessage.length() > 0) {
            this.detailMessage.append(System.lineSeparator());
        }
        
        this.detailMessage.append(detail);
        return this;
        
    }
    
    /**
     * 詳細メッセージの表示領域のサイズを設定する。
     * 
     * @param width 初期値=250
     * @param height 初期値=100
     * @return
     */
    public MessageBoxBuilder detailMessageSize(int width, int height) {
        this.detailMessageWidth = width;
        this.detailMessageHeight = height;
        return this;
    }
    
    /***
     * アイコンの設定
     * @param icon
     * @return
     */
    public MessageBoxBuilder icon(final MessageBoxIcon icon) {
        this.icon = icon;
        return this;
    }
    
    /**
     * アイコン「INFORMATION」の設定
     * @return
     */
    public MessageBoxBuilder iconInformation() {
        return icon(MessageBoxIcon.INFORMATION);
    }
    
    /**
     * アイコン「WARNING」の設定
     * @return
     */
    public MessageBoxBuilder iconWarning() {
        return icon(MessageBoxIcon.WARNING);
    }
    
    /**
     * アイコン「ERROR」の設定
     * @return
     */
    public MessageBoxBuilder iconError() {
        return icon(MessageBoxIcon.ERROR);
    }
    
    /**
     * アイコン「QUESTION」の設定
     * @return
     */
    public MessageBoxBuilder iconQuestion() {
        return icon(MessageBoxIcon.QUESTION);
    }
    
    /**
     * フォーカスを充てるボタンを設定する
     * @param button
     * @return
     */
    public MessageBoxBuilder forcusedButton(MessageBoxEvent button) {
        this.forcusButton = button;
        return this;
    }
    
    /**
     * ボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder button(final MessageBoxEvent button) {
        return button(button, false);
    }
    
    /**
     * ボタンを追加する。
     * @param button
     * @param defaultButton デフォルトボタンとして設定する
     * @return
     */
    public MessageBoxBuilder button(final MessageBoxEvent button, final boolean defaultButton) {
        
        if(button.equals(MessageBoxEvent.CLOSED)) {
            throw new IllegalArgumentException("CLOSED Option can't used button.");
        }
        
        buttons.add(button);
        if(defaultButton) {
            defaultButtons.add(button);
        }
        return this;
    }
    
    /**
     * OKボタンを追加する。
     * @return
     */
    public MessageBoxBuilder buttonOk() {
        return buttonOk(false);
    }
    
    /**
     * OKボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonOk(boolean defaultButton) {
        return button(MessageBoxEvent.OK, defaultButton);
    }
    
    /**
     * CANCELボタンを追加する。
     * @return
     */
    public MessageBoxBuilder buttonCancel() {
        return buttonCancel(false);
    }
    
    /**
     * CANCELボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonCancel(boolean defaultButton) {
        return button(MessageBoxEvent.CANCEL, defaultButton);
    }
    
    /**
     * YESボタンを追加する。
     * @return
     */
    public MessageBoxBuilder buttonYes() {
        return buttonYes(false);
    }
    
    /**
     * YESボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonYes(boolean defaultButton) {
        return button(MessageBoxEvent.YES, defaultButton);
    }
    
    /**
     * NOボタンを追加する。
     * @return
     */
    public MessageBoxBuilder buttonNo() {
        return buttonNo(false);
    }
    
    
    /**
     * NOボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonNo(boolean defaultButton) {
        return button(MessageBoxEvent.NO, defaultButton);
    }
    
    /**
     * ABORTボタンを追加する。
     * @return
     */
    public MessageBoxBuilder buttonAbort() {
        return buttonAbort(false);
    }
    
    /**
     * ABORTボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonAbort(boolean defaultButton) {
        return button(MessageBoxEvent.ABORT, defaultButton);
    }
    
    /**
     * RETRYボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonRetry() {
        return buttonRetry(false);
    }
    
    /**
     * RETRYボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonRetry(boolean defaultButton) {
        return button(MessageBoxEvent.RETRY, defaultButton);
    }
    
    /**
     * IGNOREボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonIgnore() {
        return buttonIgnore(false);
    }
    
    /**
     * IGNOREボタンを追加する。
     * @param button
     * @return
     */
    public MessageBoxBuilder buttonIgnore(boolean defaultButton) {
        return button(MessageBoxEvent.IGNORE, defaultButton);
    }
    
    /**
     * ビープ音を鳴らす
     * @return
     */
    public MessageBoxBuilder playBeep() {
        return playBeep(true);
    }
    
    /**
     * ビープ音を鳴らすか設定する。
     * @param playBeep ビープ音を鳴らす。
     * @return
     */
    public MessageBoxBuilder playBeep(boolean playBeep) {
        this.playBeep = playBeep;
        return this;
    }
    
    /**
     * 任意のノードをメッセージ領域に追加する。
     * <p>引数childNodeの値がnullの場合は、ノードを追加しない。
     * 
     * @param childNode 追加するノード
     * @return
     */
    public MessageBoxBuilder childNode(Node childNode) {
        this.childNode = childNode;
        return this;
    }
    
    /**
     * スタイルシートのパスを設定する。
     * <p>引数styleSheetの値がnullまたは空の場合は追加しない。
     * 
     * @param styleSheet 設定するstyleSheet
     * @return
     */
    public MessageBoxBuilder styleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
        return this;
    }
    
    /**
     * メッセージボックスを表示する。
     * @return
     */
    public MessageBoxEvent show() {
        return show(null);
    }
    
    /**
     * メッセージボックスを表示する
     * @param parent 親のStage。指定しない場合はnullを指定。
     * @return 押下されたボタンの種類を返す。
     */
    public MessageBoxEvent show(final Window parent) {
        
        // Windowの作成
        final Stage dialog = new Stage(StageStyle.UTILITY);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.initModality(Modality.WINDOW_MODAL);
        
        if(parent != null) {
            dialog.initOwner(parent);
        }
        
        // FXMLの取得
        final NodeAndController<VBox, MessageBoxPaneController> nac = 
                CustomFxmlTool.loadNodeAndController(VBox.class, MessageBoxPaneController.class);
        
        dialog.setScene(new Scene(nac.getNode()));
        
        // スタイルシートの設定
        if(styleSheet != null && styleSheet.length() > 0) {
            dialog.getScene().getStylesheets().add(styleSheet);
        }
        
        final MessageBoxPaneController controller = nac.getController();
        controller.setIcon(icon);
        controller.setMessage(message.toString(), messageWrapLength);
        
        // 詳細メッセージの表示
        if(detailMessage.length() > 0) {
            controller.setDetailMessage(detailMessage.toString(), detailMessageWidth, detailMessageHeight);
        }
        
        // 任意のノードの追加
        if(childNode != null) {
            controller.setChildNode(childNode);
        }
        
        // ボタンの追加
        for(MessageBoxEvent button : MessageBoxEvent.values()) {
            if(buttons.contains(button)) {
                if(button.equals(forcusButton)) {
                    controller.addButton(button, defaultButtons.contains(button), true);
                } else {
                    controller.addButton(button, defaultButtons.contains(button), false);
                }
            }
        }
        
        if(buttons.isEmpty()) {
            controller.addButton(MessageBoxEvent.DEFAULT, false, true);
        }
        
        // ダイアログの閉じるボタンがクリックされたたときのイベント追加
        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                // 返却タイプを CLOSEDにする
                controller.setResult(MessageBoxEvent.CLOSED);
                
            }
        });
        
        // ビープ音を鳴らす
        if(playBeep) {
            Toolkit.getDefaultToolkit().beep();
        }
        
        // ダイアログが閉じられるまで待つ
        dialog.requestFocus();
        dialog.showAndWait();
        
        return controller.getResult();
    }
}
