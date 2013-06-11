/*
 * MessageBoxEvent.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.messagebox;


/**
 * メッセージボックスのイベント。
 * <p>表示するボタンの種類を定義する。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public enum MessageBoxEvent {
    
    /**
     * ダイアログがボタンを押下せずに閉じられたときに返却される値。
     * <p>ボタンとしては使用しない。
     */
    CLOSED("", ""),
    DEFAULT("button_DEFAULT", "MessageBox.DEFAULT"),
    RETRY("button_RETRY", "MessageBox.RETRY"),
    ABORT("button_ABORT", "MessageBox.ABORT"),
    IGNORE("button_IGNORE", "MessageBox.IGNORE"),
    YES("button_YES", "MessageBox.YES"),
    OK("button_OK", "MessageBox.OK"),
    NO("button_NO", "MessageBox.NO"),
    CANCEL("button_CANCEL", "MessageBox.CANCEL"),
    ;
    
    /** ボタンのコントロールID(CSS用にも使用する) */
    private String id;
    
    /** ボタンのプロパティを表すキー名 */
    private String key;
    
    private MessageBoxEvent(final String id, final String key) {
        this.id = id;
        this.key = key;
    }
    
    /**
     * ボタン用のCSSのIDを取得する。
     * @return
     */
    public String id() {
        return id;
    }
    
    /**
     * ボタンのメッセージリソース用のキーを取得する。
     * @return
     */
    public String key() {
        return key;
    }
}
