package com.meiken.service;

import org.springframework.stereotype.Service;

@Service
public class InterfaceService {

    public void sayHi(){
        System.out.println(" HI");
    }

    public void insertTo(String content){
        System.out.println("insert" + content);
    }

}
