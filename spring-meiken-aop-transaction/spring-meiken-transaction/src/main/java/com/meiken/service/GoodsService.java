package com.meiken.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author glf
 * @Date 2021/3/27
 */
@Service
public class GoodsService {

    @Autowired
    private DataSourceTransactionManager goodsTransactionManager;

    @Autowired
    private JdbcTemplate goodsJdbcTemplate;


    public TransactionStatus insertGoods(){

        //开启一个事务，如果3秒后事务没有提交就回滚
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//外部存在事务，融入


        TransactionStatus transactionStatus = goodsTransactionManager.getTransaction(transactionDefinition);

        goodsJdbcTemplate.execute("insert into goods (`name`) values ('food')");

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!transactionStatus.isCompleted()){
                goodsTransactionManager.rollback(transactionStatus);
                System.out.println("Over Time RollBack");
            }
            System.out.println("Transaction is Complete");
        }).start();

        return transactionStatus;

    }

    public void commitInsertGoodsTransaction(TransactionStatus transactionStatus){
        goodsTransactionManager.commit(transactionStatus);
        System.out.println("Commit");
    }
}
