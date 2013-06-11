/*
 * Singleton3Controller.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 *
 * @author T.TSUCHIE
 *
 */
public class Singleton3Controller {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public void callService() {
        sample1Service.doMethod();
        sample2Service.doMethod();
    }
    
    public void init() {
        System.out.printf("... call %s#init() : initialize spring bean\n", getClass().getSimpleName());
    }
    
    public void destroy() {
        System.out.printf("... call %s#destroy() : destroy spring bean\n", getClass().getSimpleName());
    }
    
}
