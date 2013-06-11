/**
 * @file SpringAnnotationInjector.java
 * @brief SpringAnnotationInjector
 * @date 2013/05/07
 * 
 * (C) Copyright 2003-2008 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * 既存のオブジェクトに、SpringBeanをインジェクションするクラス。
 * <p>アノテーションによるインジェクションをする。
 * <ul>
 *  <li>Spring2.5の場合は、<code>@Resource</code>を利用するには、{@link CommonAnnotationBeanPostProcessor}をSpring Beanとして登録しておく必要がある。
 *  <li><code>@Autowired、@Value(Spring3.0以降)</code>が利用可能。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Component
public class SpringAnnotationInjector {
    
    @Autowired
    private AutowiredAnnotationBeanPostProcessor autowiredProcessor;
    
    @Resource
    private CommonAnnotationBeanPostProcessor commonProcessor;
    
    /**
     * 任意のオブジェクトにSpringBeanをインジェクションする。
     * <p>アノテーション「@Resource」「@Autowired」「@Value（Spring3.0以降）」が対象。
     * @param unamanagedBean インジェクション対象のBean
     */
    public void inject(final Object unmanagedBean) {
        Assert.notNull(unmanagedBean);
        
        // javax.* 関連のアノテーションのインジェクション(@Resoure)
        //Spring2.5の場合、「CommonAnnotationBeanPostProcessor」は自動登録されないので、自分で登録しておく必要がある。
        commonProcessor.postProcessPropertyValues(null, null, unmanagedBean, getBeanName(unmanagedBean.getClass()));
        
        // @Autowire、@Valueのアノテーションのインジェクション
        autowiredProcessor.processInjection(unmanagedBean);
        
    }
    
    /**
     * クラス名の先頭を小文字にした名称を取得する。
     * @param clazz
     * @return
     */
    private String getBeanName(final Class<?> clazz) {
        String name = clazz.getSimpleName();
        return String.valueOf(name.charAt(0)).toLowerCase().concat(name.substring(1));
    }
    
}
