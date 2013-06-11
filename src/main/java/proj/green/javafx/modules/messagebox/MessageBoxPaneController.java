/*
 * MessageBoxPaneController.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.messagebox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proj.green.javafx.modules.utils.WordUtils;


/**
 * メッセージダイアログのコントローラ
 * <p>使用する際には、{@link MessageBoxBuilder}を利用してインスタンスを生成するため、このコントローラは直接操作しません。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class MessageBoxPaneController {
    
    private MessageBoxEvent buttonResult;
    
    @FXML
    private VBox myNode;
    
    @FXML
    private ImageView iconImage;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private VBox messageLayoutPane;
    
    @FXML
    private HBox buttonLayoutPane;
    
    @FXML
    private URL location;
    
    @FXML
    private ResourceBundle resources;
    
    @FXML
    void initialize() {
        
        // メッセージ表示領域の設定
        messageLabel.autosize();
        messageLabel.setWrapText(false);
        
        // 詳細の表示の設定
        messageLayoutPane.autosize();
        
        // ボタンレイアウト領域の設定
        buttonLayoutPane.autosize();
    }
    
    /**
     * アイコンを設定する
     * @param icon
     */
    public void setIcon(final MessageBoxIcon icon) {
        
        iconImage.setImage(new Image(getClass().getResourceAsStream(icon.imagePath())));
        iconImage.autosize();
        
    }
    
    /**
     * メッセージを設定する
     * @param message
     * @param wrapLength
     */
    public void setMessage(final String message, int wrapLength) {
        
        final String text = WordUtils.wrapText(message, wrapLength);
        
        messageLabel.setText(text);
        messageLabel.autosize();
        
    }
    
    /**
     * 詳細メッセージを追加する。
     * @param detailMessage
     */
    public void setDetailMessage(final String detailMessage, int width, int height) {
        
        final TextArea textArea = new TextArea();
        textArea.setText(detailMessage);
        textArea.setDisable(false);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMinWidth(width);
        textArea.setMinHeight(height);
        textArea.setOpacity(2.0);
        textArea.focusTraversableProperty().set(false);
        
        VBox.setMargin(textArea, new Insets(10.0, 0, 10, 0));
        messageLayoutPane.getChildren().add(textArea);
        
        messageLabel.autosize();
        messageLayoutPane.autosize();
    }
    
    /**
     * 任意のノードを追加する
     * @param childNode
     */
    public void setChildNode(final Node childNode) {
        
        messageLayoutPane.getChildren().add(childNode);
        
    }
    
    /**
     * ボタンを追加する。
     * @param buttonType
     * @param forcussedButton フォーカスを設定するかどうか
     */
    public void addButton(final MessageBoxEvent buttonType, final boolean defaultButton, final boolean forcussedButton) {
        
        final Button button = new Button();
        button.setId(buttonType.id());
        button.setText(resources.getString(buttonType.key()));
        button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(final ActionEvent event) {
                buttonResult = buttonType;
                ((Stage)(myNode.getScene().getWindow())).close();
            }
        });
        
        if(buttonType == MessageBoxEvent.CANCEL) {
            button.setCancelButton(true);
        }
        
        button.setDefaultButton(defaultButton);
        
        // フォーカスの設定
        if(forcussedButton) {
            button.focusTraversableProperty().set(true);
        } else {
            button.focusTraversableProperty().set(false);
        }
        
        // レイアウト領域に追加
        buttonLayoutPane.getChildren().add(button);
        buttonLayoutPane.autosize();
        myNode.autosize();
    }
    
    /**
     * 押下されたボタンを取得する
     * @return
     */
    public MessageBoxEvent getResult() {
        return buttonResult;
    }
    
    /**
     * 押下されたボタンを設定
     * @return
     */
    public void setResult(MessageBoxEvent buttonResult) {
        this.buttonResult = buttonResult;
    }
}
