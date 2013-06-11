/*
 * Prototype1Controller.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class Prototype2Controller implements InitializingBean, DisposableBean {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public void callService() {
        sample1Service.doMethod();
        sample2Service.doMethod();
    }
    
    @Override
    public void afterPropertiesSet() {
        System.out.printf("... call %s#afterPropertiesSet() : InitializingBean\n", getClass().getSimpleName());
    }
    
    @Override
    public void destroy() {
        System.out.printf("... call %s#destroy() : DisposableBean\n", getClass().getSimpleName());
    }
    
}
