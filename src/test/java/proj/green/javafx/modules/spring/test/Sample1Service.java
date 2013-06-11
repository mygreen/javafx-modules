/*
 * SampleService.java
 * created in 2013/06/16
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.spring.test;

import org.springframework.stereotype.Service;


/**
 * アノテーション「@Resource」によるインジェクションをするサービス
 *
 * @author T.TSUCHIE
 *
 */
@Service
public class Sample1Service {
    
    public void doMethod() {
        System.out.printf("call method :%s\n", getClass().getSimpleName());
    }
    
}
