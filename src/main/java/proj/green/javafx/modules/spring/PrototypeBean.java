/*
 * PrototypeBean.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import java.lang.ref.WeakReference;

import org.springframework.util.Assert;


/**
 * プロトタイプのSpringのインスタンスを保持するクラス。
 * <p>インスタンスは弱参照で保持するため、nullになる場合もある。
 *
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class PrototypeBean {
    
    /** Bean名 */
    private final String name;
    
    /** Beanのクラスタイプ */
    private final Class<?> classType;
    
    /** Beanオブジェクト（弱参照で保持する） */
    private final WeakReference<Object> beanInstance;
    
    public PrototypeBean(final String name, final Class<?> classType, final Object beanInstance) {
        
        Assert.hasLength(name);
        Assert.notNull(classType);
        
        this.name = name;
        this.classType = classType;
        this.beanInstance = new WeakReference<Object>(beanInstance);
        
    }
    
    /**
     * Bean名
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Beanのクラスタイプ
     * @return
     */
    public Class<?> getClassType() {
        return classType;
    }
    
    /**
     * Spring Beanのインスタンスを取得する。
     * <p>値がnullの場合もある。
     */
    public Object getBeanInstance() {
        return beanInstance.get();
    }
    
    /**
     * Spring Beanのインスタンスがnullかどうか。
     * @return
     */
    public boolean isNullBeanInstance() {
        return getBeanInstance() == null;
    }
    
    /**
     * Spring Beanのインスタンスがnullでないかどうか。
     * @return
     */
    public boolean isNotNullBeanInstance() {
        return !isNullBeanInstance();
    }
}
