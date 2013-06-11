/**
 * @file SpringFxmlLoader.java
 * created in 2013/05/18
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Callback;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.Assert;

import proj.green.javafx.modules.fxml.CustomFxmlLoader;
import proj.green.javafx.modules.fxml.NodeAndController;


/**
 * ControllerにSpringオブジェクトをインジェクションするFXMLローダ。
 * <p>使用する前に、{@link ApplicationContext}を設定しておく必要がある。
 * <p>{@link SpringAnnotationInjector}をSpringのコンテナに登録しておく必要がある。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class SpringFxmlLoader extends CustomFxmlLoader {
    
    private static Logger logger = Logger.getLogger(SpringFxmlLoader.class.getSimpleName());
    
    /** Springのコンテキスト */
    protected ApplicationContext applicationContext;
    
    /**
     * プロトタイプのオブジェクト
     * <p>Destroyを終了時に自分で行う。
     */
    protected List<PrototypeBean> prototypeBeans = Collections.synchronizedList(new ArrayList<PrototypeBean>());
    
    /**
     * SpringのApplicationContextを設定する。
     * <p>で保持するのでシステムで共通なものを利用する。
     * @param context
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
    public <N extends Node, C> NodeAndController<N, C> loadNodeAndControllerFromSpring(final Class<N> nodeClass, final Class<C> controllerClass) {
        return loadNodeAndControllerFromSpring(nodeClass, controllerClass, Locale.getDefault());
    }
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在すれば読み込む。
     * @param controllerClass
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <N extends Node, C> NodeAndController<N, C> loadNodeAndControllerFromSpring(final Class<N> nodeClass, final Class<C> controllerClass, final Locale locale) {
        
        final FXMLLoader loader = new FXMLLoader();
        
        // リソースの設定
        ResourceBundle resources = getControllerResources(controllerClass, locale);
        if(resources != null) {
            loader.setResources(resources);
        }
        
        /*
         * ベースURLの設定
         * 画像を読み込む際などに必要
         */
        final String basePath = "/".concat(controllerClass.getPackage().getName().concat("/")).replaceAll("\\.", "/");
        loader.setLocation(getClass().getResource(basePath));
        
        // Spring用のControllerのCallbackの設定
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(final Class<?> param) {
                
                // ControllerをSprign Beanコンテナから取得する
                final String beanName = getSpringBeanNameFromClassName(param);
                final Object beanObj = applicationContext.getBean(beanName);
                if(isPrototypeBean(beanName, beanObj)) {
                    prototypeBeans.add(new PrototypeBean(beanName, controllerClass, beanObj));
                }
                return beanObj;                
                
            }
        });
        
        final String fxmlPath = "/".concat(controllerClass.getCanonicalName()).replaceAll("\\.", "/").replaceFirst("Controller", "").concat(".fxml");
        final N node;
        try {
            node = (N) loader.load(getClass().getResourceAsStream(fxmlPath));
            
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("fail load fxml : %s", controllerClass.getClass().getCanonicalName()),
                    e);
        }
        
        // CSSの設定
        if(node instanceof Parent) {
            final URL stylesheet = getControllerCssUrl(controllerClass);
            if(stylesheet != null) {
                ((Parent) node).getStylesheets().add(stylesheet.toExternalForm());
            }
        }
        
        // Controllerの取得
        final C controller = loader.getController();
        
        return new NodeAndController(node, controller);
        
    }
    
    /**
     * FXMLが<code><fx:root /></code>を読み込む。ControllerにSpring Beanをインジェクションする。
     * <p>通常、Controllerは<code><fx:root /></code>の属性「type」でしていたJavaFXのノードクラスを継承している。
     * 
     * @see 01-00
     * @param root ルートノード。通常は、Controller自身のインスタンス。
     * @param controller Controller自身のインスタンス。
     */
    public void loadComponentFromSpring(final Object root, final Object controller) {
        loadComponentFromSpring(root, controller, Locale.getDefault());
        
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
    public void loadComponentFromSpring(final Object root, final Object controller, final Locale locale) {
        
        loadComponent(root, controller, locale);
        
        // spring beanのインジェクション
        injectSpringBean(controller);
    }
    
    /**
     * Spring Bean名をクラス名から逆引きして取得する。
     * <p>クラス名の先頭の文字を小文字にする。
     * @param clazz
     * @return
     */
    protected String getSpringBeanNameFromClassName(final Class<?> clazz) {
        Assert.notNull(clazz);
        String className = clazz.getSimpleName();
        
        className = String.valueOf(className.charAt(0)).toLowerCase().concat(className.substring(1));
        return className;
        
    }
    
    /**
     * 任意のオブジェクトに対してSpring Beanをインジェクションする
     * @param obj インジェクション対象のインスタンス
     */
    public void injectSpringBean(final Object obj) {
        Assert.notNull(obj);
        
        SpringAnnotationInjector springInjector = (SpringAnnotationInjector) applicationContext.getBean(
                getSpringBeanNameFromClassName(SpringAnnotationInjector.class));
        springInjector.inject(obj);
        
    }
    
    /**
     * SpringのBeanがSingletonかどうか判定する
     * @param name
     * @param beanObj
     * @return
     */
    protected boolean isPrototypeBean(final String name, final Object beanObj) {
        
        if(applicationContext == null) {
            logger.warning("applicationContext should be not null.");
            return false;
        }
        
        if(name == null || name.isEmpty()) {
            logger.warning("bean name is empty.");
            return false;
        }
        
        if(!(applicationContext instanceof AbstractApplicationContext)) {
            logger.warning("applicationContext is not AbstractApplicationContext");
        }
        
        return ((AbstractApplicationContext) applicationContext).isPrototype(name);
    }
    
    /**
     * 今まで生成した、Scope=Prototypeのオブジェクトを削除する。
     * @param beanObjList SpringのBeanオブジェクト
     */
    
    public void destroyAllProrotypeObject() {
        
        if(applicationContext == null) {
            logger.warning("applicationContext should be not null.");
            return;
        }
        
        if(!(applicationContext instanceof AbstractApplicationContext)) {
            if(logger.isLoggable(Level.INFO)) {
                logger.info("applicationContext is not AbstractApplicationContext");
            }
            
            return;
        }
        
        final ConfigurableBeanFactory beanFactory = 
                ((AbstractApplicationContext) applicationContext).getBeanFactory();
        
        for(PrototypeBean bean : getPrototypeBeans()) {
            if(bean.isNotNullBeanInstance()) {
                beanFactory.destroyBean(bean.getName(), bean.getBeanInstance());
                
            }
        }
        
        getPrototypeBeans().clear();
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 今まで生成したprptotypeスコープのBean情報を取得する。
     * @return
     */
    public List<PrototypeBean> getPrototypeBeans() {
        return prototypeBeans;
    }
    
}
