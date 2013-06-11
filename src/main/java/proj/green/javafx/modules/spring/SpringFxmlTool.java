/**
 * SpringFxmlTool.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;

import org.springframework.context.ApplicationContext;

import proj.green.javafx.modules.fxml.NodeAndController;



/**
 * シングルトンモデルのFXMLを処理するクラス。
 * <p>Springのインジェクションが可能。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class SpringFxmlTool {
    
    /** 自身のインスタンス */
    private static final SpringFxmlTool singleton = new SpringFxmlTool();
    
    /** FXMLローダ */
    private final SpringFxmlLoader fxmlLoader;
    
    protected SpringFxmlTool() {
        fxmlLoader = new SpringFxmlLoader();
    }
    
    protected static SpringFxmlLoader getFxmlLoader() {
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
     * SpringのApplicationContextを設定する
     * @param applicationContext
     */
    public static void init(final ApplicationContext applicationContext) {
        getFxmlLoader().setApplicationContext(applicationContext);
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
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在すれば読み込む。
     * <p>自身をrootとして読み込む。
     * 
     * @since 01-00
     * @param controllerClass
     * @return 
     * @return
     */
    public static <N extends Node, C> NodeAndController<N, C> loadNodeAndControllerFromSpring(final Class<N> nodeClass, final Class<C> controllerClass) {
        return getFxmlLoader().loadNodeAndControllerFromSpring(nodeClass, controllerClass);
    }
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在すれば読み込む。
     * <p>自身をrootとして読み込む。
     * @param controllerClass
     */
    public static <N extends Node, C> NodeAndController<N, C> loadNodeAndControllerFromSpring(final Class<N> nodeClass, final Class<C> controllerClass, final Locale locale) {
        return getFxmlLoader().loadNodeAndControllerFromSpring(nodeClass, controllerClass, locale);
        
    }
    
    /**
     * FXMLが<code><fx:root /></code>を読み込む。ControllerにSpring Beanをインジェクションする。
     * <p>通常、Controllerは<code><fx:root /></code>の属性「type」でしていたJavaFXのノードクラスを継承している。
     * 
     * @see 01-00
     * @param root ルートノード。通常は、Controller自身のインスタンス。
     * @param controller Controller自身のインスタンス。
     */
    public static void loadComponentFromSpring(final Object root, final Object controller) {
        getFxmlLoader().loadComponentFromSpring(root, controller);
        
    }
    
    /**
     * FXMLが<code><fx:root /></code>を読み込む。ControllerにSpring Beanをインジェクションする。
     * <p>通常、Controllerは<code><fx:root /></code>の属性「type」でしていたJavaFXのノードクラスを継承している。
     * <p>FXMLファイルは、引数「controller」のクラスの名称から、文言「Controller」を
     * 
     * @see 01-00
     * @param root ルートノード。通常は、Controller自身のインスタンス。
     * @param controller Controller自身のインスタンス。
     * @param locale リソースファイルを友の合う場合のlocale情報。指定しない場合はnullを設定する。
     */
    public static void loadComponentFromSpring(final Object root, final Object controller, final Locale locale) {
        getFxmlLoader().loadComponentFromSpring(root, controller, locale);
    }
    
    /**
     * 任意のオブジェクトに対してSpring Beanをインジェクションする。
     * <p>アノテーションを設定する必要がある。
     * @param obj
     */
    public static void injectSpringBean(final Object obj) {
        getFxmlLoader().injectSpringBean(obj);
    }
    
    /**
     * 今まで生成した、Scope=prototypeのSpringBeanのオブジェクト
     * @return
     */
    public static List<PrototypeBean> getPrototypeBeans() {
        return getFxmlLoader().getPrototypeBeans();
    }
    
    /**
     * 今まで生成した Scope=prototypeのSpringBeanのオブジェクトを全て削除する。
     * <p>削除する際には、destroyメソッドを呼ぶ。
     */
    public static void destroyAllProrotypeObject() {
        getFxmlLoader().destroyAllProrotypeObject();
    }
    
}
