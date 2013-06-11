/**
 * CustomFxmlTool.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.fxml;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;


/**
 * シングルトンモデルのFXMLを処理するクラス。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class CustomFxmlTool {
    
    /** 自身のインスタンス */
    private static final CustomFxmlTool singleton = new CustomFxmlTool();
    
    /** FXMLローダ */
    private final CustomFxmlLoader fxmlLoader;
    
    protected CustomFxmlTool() {
        fxmlLoader = new CustomFxmlLoader();
    }
    
    protected static CustomFxmlLoader getFxmlLoader() {
        return singleton.fxmlLoader;
    }
    
    /**
     * システム共通のリソースを設定する
     * @param applicationResources
     */
    public static void init(final ResourceBundle resources) {
        getFxmlLoader().setApplicationResource(resources);
    }
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在しなければ読み込まない。
     * @param nodeClass 読み込みたいFXMLのルートノードのクラス型。
     * @param controllerClass FXMLのマッピングするControllerのクラス型
     * @return 
     */
    public static <N extends Node, C> NodeAndController<N, C> loadNodeAndController(final Class<N> nodeClass, final Class<C> controllerClass) {
        return getFxmlLoader().loadNodeAndController(nodeClass, controllerClass);
    }
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在しなければ読み込まない。
     * @param nodeClass 読み込みたいFXMLのルートノードのクラス型。
     * @param controllerClass FXMLのマッピングするControllerのクラス型
     * @return 
     */
    public static <N extends Node, C> NodeAndController<N, C> loadNodeAndController(final Class<N> nodeClass, final Class<C> controllerClass, final Locale locale) {
        return getFxmlLoader().loadNodeAndController(nodeClass, controllerClass, locale);
    }
    
    /**
     * 自身がルートである<code><fx:root /></code>となっているFXMLを読み込む。
     * 
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在すれば読み込む。
     * <p>自身をrootとして読み込む。
     * 
     * @since 01-00
     * @param root ルートノード。通常は、Controller自身のインスタンス。
     * @param controller Controller自身のインスタンス。
     */
    public static void loadComponent(final Object root, final Object controller) {
        getFxmlLoader().loadComponent(root, controller);
    }
    
    /**
     * 自身がルートである<code><fx:root /></code>となっているFXMLを読み込む。
     * 
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在すれば読み込む。
     * <p>自身をrootとして読み込む。
     * 
     * @since 01-00
     * @param root ルートノード。通常は、Controller自身のインスタンス。
     * @param controller Controller自身のインスタンス。
     * @param locale リソースファイルを友の合う場合のlocale情報。指定しない場合はnullを設定する。
     */
    public static void loadComponent(final Object root, final Object controller, final Locale locale) {
        getFxmlLoader().loadComponent(root, controller);
    }
    
}
