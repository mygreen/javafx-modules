/**
 * @file FXValue.java
 * @brief FXValue
 * @date 2013/05/11
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.fxml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * JavaFXのControlの値の初期値を定義するアノテーション。
 * <p>プロパティファイルなどから値を保存・設定するために利用する。
 * 
 * @version 03-10
 * @since 03-10
 * @author T.TSUCHIE
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FXValue {
    
    /** 初期値 */
    String value() default "";
    
    /** ChoiceBox、ComboBoxなどGenericsの場合に、値のクラスタイプを指定する。 */
    Class<?> typeClass() default Class.class;
    
}
