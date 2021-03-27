package com.meiken.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author glf
 * @Date 2021/1/19
 */
@Component
public class LiuLaoGenBigStage implements Performance{

    public LiuLaoGenBigStage(){
        System.out.println("Liu Lao Gen Big Stage");
    }
    @Override
    public void perform() {
        System.out.println("Liu Lao Gen Big Stage Performance");
    }

    @Override
    public void performXml() {
        System.out.println("Liu Lao Gen Big Stage Performance Xml");
    }

    @Override
    public void printCount(int count) {
        System.out.println("Liu Lao Gen Big Stage PrintCount " + count);
    }
}
