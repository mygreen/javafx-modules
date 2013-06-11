/*
 * MessageBoxIcon.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.messagebox;


/**
 * メッセージボックスのアイコンの種類
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public enum MessageBoxIcon {
    
    /** Error icon. */
    INFORMATION("icon_information.png"),
    
    /** Warning icon. */
    WARNING("icon_warning.png"),
    
    /** Information icon. */
    ERROR("icon_error.png"),
    
    /** Question icon. */
    QUESTION("icon_question.png"),
    ;
    
    /** アイコン画像のパス */
    private String imagePath;
    
    private MessageBoxIcon(String imagePath) {
        this.imagePath = imagePath;
    }
    
    /**
     * アイコン画像のパスの取得
     * @return
     */
    public String imagePath() {
        return imagePath;
    }
}
