package com.meiken.service;

import com.meiken.ICommonService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author glf
 * @Date 2023/2/23
 */
@DubboService
public class CommonServiceImpl implements ICommonService {

    @Override
    public String hello(String name) {
        return "hello : " + name;
    }
}
