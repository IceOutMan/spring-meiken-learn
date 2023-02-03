package com.meiken.service;

import com.meiken.annotation.LogAnnotation;
import org.springframework.stereotype.Service;

@Service
public class AnnotationService {

    @LogAnnotation(lockName = "sayHi")
    public void sayHi(){
        System.out.println("Annotation HI");
    }
    @LogAnnotation(lockName = "insertTo")
    public void insertTo(String content){
        System.out.println("Annotation insert" + content);
    }
}
