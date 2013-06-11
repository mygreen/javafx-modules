/**
 * @file CustomFxmlLoader.java
 * @brief CustomFxmlLoader
 * @date 2013/05/07
 *
 * (C) Copyright 2003-2008 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;


/**
 * FXMLファイルから、{@link Node}とそれに付随するControllerオブジェクトを取得するためのユーティリティクラス。
 * <p>引数のControllerクラスの名前を元に、FXMLファイルを自動で読み込むためファイル名を規則に沿ったものにする必要がある。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class CustomFxmlLoader {
    
    /** システム共通のリソース */
    protected ResourceBundle applicationResource;
    
    /**
     * アプリケーション共通のリソース情報を設定する。
     * <p>アプリケーション実行前に呼ぶ。
     * <p>ここで設定された{@link ResourceBundle}は、Controllerに渡される。
     * @param resource
     */
    public void setApplicationResource(final ResourceBundle resource) {
        applicationResource = resource;
    }
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在しなければ読み込まない。
     * @param nodeClass 読み込みたいFXMLのルートノードのクラス型。
     * @param controllerClass FXMLのマッピングするControllerのクラス型
     * @return 
     */
    public <N extends Node, C> NodeAndController<N, C> loadNodeAndController(final Class<N> nodeClass, final Class<C> controllerClass) {
        return loadNodeAndController(nodeClass, controllerClass, Locale.getDefault());
    }
    
    
    /**
     * コントローラを伴うFXMLを読み込む。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * <p>リソースファイルも<コントローラ名>.properties"とする。ただし、存在しなければ読み込まない。
     * @param nodeClass 読み込みたいFXMLのルートノードのクラス型。
     * @param controllerClass FXMLのマッピングするControllerのクラス型
     * @return 
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <N extends Node, C> NodeAndController<N, C> loadNodeAndController(final Class<N> nodeClass, final Class<C> controllerClass, final Locale locale) {
        
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
        
        // FXMLの取得
        final String fxmlPath = "/".concat(controllerClass.getCanonicalName()).replaceAll("\\.", "/").replaceFirst("Controller", "").concat(".fxml");
        final Node node;
        try {
            node = (Node) loader.load(getClass().getResourceAsStream(fxmlPath));
            
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
    public void loadComponent(final Object root, final Object controller) {
        loadComponent(root, controller, Locale.getDefault());
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
    public void loadComponent(final Object root, final Object controller, final Locale locale) {
        if(root == null) {
            throw new IllegalArgumentException("root obj should be non-null.");
        }
        
        if(controller == null) {
            throw new IllegalArgumentException("controller obj should be non-null.");
        }
        
        final FXMLLoader loader = new FXMLLoader(getControllerFxmlUrl(controller.getClass()));
        
        /*
         * ベースURLの設定
         * 画像を読み込む際などに必要
         */
        final String basePath = "/".concat(controller.getClass().getPackage().getName().concat("/")).replaceAll("\\.", "/");
        loader.setLocation(getClass().getResource(basePath));
        
        // リソースの設定
        final ResourceBundle resources = getControllerResources(controller.getClass(), locale);
        if(resources != null) {
            loader.setResources(resources);
        }
        
        loader.setRoot(root);
        loader.setController(controller);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(String.format("fail load fxml : %s", controller.getClass().getCanonicalName()),
                    e);
        }
        
        // CSSの設定
        if(controller instanceof Parent) {
            final URL stylesheet = getControllerCssUrl(controller.getClass());
            if(stylesheet != null) {
                ((Parent) controller).getStylesheets().add(stylesheet.toExternalForm());
            }
        }
        
    }
    
    /**
     * コントローラに対するFxmlの{@link URL}を取得する。
     * <p>FXMLファイルは、"<コントローラ名>.fxml"とする。
     * @param controllerClass
     * @return
     */
    protected URL getControllerFxmlUrl(final Class<?> controllerClass) {
        
        final String fxmlPath = buildControllerResourcePath(controllerClass, "fxml");
        return getClass().getResource(fxmlPath);
        
    }
    
    /**
     * コントローラクラスに対応する{@link ResourceBundle}を取得する。
     * <p>ResouceBundleファイルは、"<コントローラ名>.properties"とする。
     * <p>存在しない場合は、nullを返す。
     * @param controllerClass
     * @return
     */
    protected ResourceBundle getControllerResources(final Class<?> controllerClass, final Locale locale) {
        
        //TODO: 複数読み込みの場合は↓を参照。独自作成はしなくてよい
        // http://stackoverflow.com/questions/2630339/java-multiple-resourcebundles
        // システム共通のリソース
        final MergeResourceBundle resource = new MergeResourceBundle();
        if(applicationResource != null) {
            resource.addResource(applicationResource);
        }
        // Controller固有のリソース
        ResourceBundle controllerResource;
        try {
            controllerResource = ResourceBundle.getBundle(controllerClass.getCanonicalName(), locale);
        } catch(MissingResourceException e) {
            controllerResource = null;
        }
        
        if(controllerResource != null) {
            resource.addResource(controllerResource);
        }
        
        //TODO: 継承している場合は、その上を読み込む。
        // パッケージ名で判断する
        
        if(applicationResource == null && controllerResource == null) {
            // システム共通用とController共通用が見つからない場合は、nullを返す。
            return null;
        }
        
        return resource;
        
    }
    
    /**
     * コントローラに対するCSSの{@link URL}を取得する。
     * <p>FXMLファイルは、"<コントローラ名>.css"とする。
     * @param controllerClass
     * @return 存在しない場合はnullを返す。
     */
    protected URL getControllerCssUrl(final Class<?> controllerClass) {
        
        final String fxmlPath = buildControllerResourcePath(controllerClass, "css");
        return getClass().getResource(fxmlPath);
        
    }
    
    /**
     * コントローラ名に対応する拡張子を持つリソースパスを組み立てる。
     * @param controllerClass コントローラのクラス名
     * @param extenion 拡張子（区切り文字'.'は除く）
     * @return
     */
    protected String buildControllerResourcePath(final Class<?> controllerClass, final String extenion) {
        
        final String path = controllerClass.getCanonicalName().replaceAll("\\.", "/").concat(".").concat(extenion);
        return "/" + path;
    }

    
}
