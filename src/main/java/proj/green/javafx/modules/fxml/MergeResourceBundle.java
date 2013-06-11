/**
 * @file MergeResourceBundle.java
 * @brief MergeResourceBundle
 * @date 2013/05/07
 *
 * (C) Copyright 2003-2008 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.fxml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * 複数のリソースバンドルをマージした情報を保持することができるリソースバンドル。
 * <p>同じ名前のキーがあった場合、後から追加したものが優先される。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class MergeResourceBundle extends ResourceBundle {
    
    /** リソースバンドルの情報 */
    protected Map<String, Object> resourceMap = new Hashtable<>();
    
    protected List<ResourceBundle> resources = new ArrayList<>();
    
    /**
     * リソースバンドルを追加する。
     * <p>引数がnullの場合は何もしない。
     * @param resource
     */
    public void addResource(final ResourceBundle resource) {
        if(resource == null) {
            return;
        }
        
        resources.add(resource);
        for(String key : resource.keySet()) {
            resourceMap.put(key, resource.getObject(key));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final String key) {
        return resourceMap.containsKey(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Object handleGetObject(final String key) {
        return resourceMap.get(key);
    }
    
    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(resourceMap.keySet());
    }
    
    /**
     * リソース情報があるかチェックする。
     * @return
     */
    public boolean isEmptyResources() {
        return resources.isEmpty();
    }
}
