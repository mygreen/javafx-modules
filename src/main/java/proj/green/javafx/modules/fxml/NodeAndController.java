/**
 * @file NodeAndController.java
 * @brief NodeAndController
 * @date 2013/05/07
 *
 * (C) Copyright 2003-2008 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.fxml;

import java.io.Serializable;

import javafx.scene.Node;


/**
 * JavaFXの{@code Node}とControllerを保持するクラス。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 * 
 * @param <N> JavaFXのノードタイプ
 * @param <C> Controllerのクラスタイプ
 *
 */
public class NodeAndController<N extends Node, C> implements Serializable {
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** JavaFXのノード */
    private final N node;
    
    /** Controller */
    private final C controller;
    
    public NodeAndController(final N node, final C controller) {
        this.node = node;
        this.controller = controller;
    }
    
    /**
     * FXMLのルート要素で定義したJavaFXのノードを取得する。
     * @return
     */
    public N getNode() {
        return node;
    }
    
    /**
     * ノードに対するコントローラを取得する。
     * @return
     */
    public C getController() {
        return controller;
    }
    
}
