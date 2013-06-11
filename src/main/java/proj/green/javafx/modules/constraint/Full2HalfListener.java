/*
 * Full2HalfListener.java
 * created in 2013/07/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.constraint;

import proj.green.javafx.modules.utils.WordUtils;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputControl;


/**
 * 全角を半角に置換するリスナー
 * @since 01-00
 * @version 01-00
 * @author T.TSUCHIE
 *
 */
public class Full2HalfListener implements ChangeListener<String> {
    
    @Override
    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
        
        final TextInputControl control = (TextInputControl)((ReadOnlyProperty)observable).getBean();
        String converted = WordUtils.convertFull2half(newValue);
        control.setText(converted);
        
        
    }
}
