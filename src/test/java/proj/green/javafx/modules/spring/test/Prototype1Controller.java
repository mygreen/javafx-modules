/*
 * Prototype1Controller.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring.test;

import javafx.fxml.FXML;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 *
 * @author T.TSUCHIE
 *
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class Prototype1Controller {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public void callService() {
        sample1Service.doMethod();
        sample2Service.doMethod();
    }
    
    @FXML
    public void initialize() {
        System.out.printf("... call %s#initialize() : Initializable\n", getClass().getSimpleName());
    }
    
    @PostConstruct
    public void init() {
        System.out.printf("... call %s#init() : @PostConstruct\n", getClass().getSimpleName());
    }
    
    @PreDestroy
    public void destroy() {
        System.out.printf("... call %s#destroy() : @PreDestroy\n", getClass().getSimpleName());
    }
    
}
