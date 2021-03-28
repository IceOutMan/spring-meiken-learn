package com.meiken.controller;

import com.meiken.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author glf
 * @Date 2021/3/27
 */
@RestController
@ResponseBody
public class TestController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test() throws InterruptedException {
        TransactionStatus transactionStatus = goodsService.insertGoods();

        Thread.sleep(4000);

        goodsService.commitInsertGoodsTransaction(transactionStatus);
    }
}
