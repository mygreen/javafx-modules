/*
 * Singleton2Controller.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring.test;

import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import javafx.fxml.Initializable;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * XML定義によるSingletonのオブジェクト
 *
 * @author T.TSUCHIE
 *
 */
public class Singleton2Controller implements InitializingBean, DisposableBean, Initializable {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public void callService() {
        sample1Service.doMethod();
        sample2Service.doMethod();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        System.out.printf("... call %s#initialize() : Initializable\n", getClass().getSimpleName());
        
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
