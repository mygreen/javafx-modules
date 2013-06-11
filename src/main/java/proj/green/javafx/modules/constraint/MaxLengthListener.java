/*
 * MaxLengthListener.java
 * created in 2013/07/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.constraint;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputControl;


/**
 * Controlの最大文字数の制限をするリスナー。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class MaxLengthListener implements ChangeListener<String> {
    
    /**
     * 入力可能な最大文字数
     */
    final int maxLength;
    
    public MaxLengthListener(final int maxLength) {
        
        if(maxLength <= 0) {
            throw new IllegalArgumentException("maxLength shoud be lather thane zero (> 0)");
        }
        
        this.maxLength = maxLength;
    }

    @Override
    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
        if(maxLength < newValue.length()) {
            // 入力を受け付けない場合は、textプロパティを変更前に戻す。
            final TextInputControl control = (TextInputControl)((ReadOnlyProperty)observable).getBean();
            control.setText(oldValue);
        }
        
    }
    
    
}
