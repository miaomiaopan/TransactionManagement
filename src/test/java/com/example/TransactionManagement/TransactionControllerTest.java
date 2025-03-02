package com.example.TransactionManagement;

import com.example.TransactionManagement.controller.TransactionController;
import com.example.TransactionManagement.entity.Transaction;
import com.example.TransactionManagement.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class TransactionControllerTest {
    @Autowired
    TransactionController transactionController;

    // 交易创建测试
    @Test
    void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setUserId("user1");
        ResponseEntity response = transactionController.createTransaction(transaction);
        Assertions.assertEquals((String)response.getBody(), "缺少金额");

        transaction.setAmount(new BigDecimal(12.895));
        response = transactionController.createTransaction(transaction);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // 更新交易测试
    @Test
    void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setUserId("user1");
        transaction.setAmount(new BigDecimal(12.895));
        ResponseEntity response = transactionController.createTransaction(transaction);

        String transactionId = ((Transaction) response.getBody()).getTransactionId();

        transaction.setAmount(new BigDecimal(1234.456));
        response = transactionController.updateTransaction("jy2", transaction);
        Assertions.assertEquals((String)response.getBody(), "交易不存在");

        response = transactionController.updateTransaction(transactionId, transaction);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        transactionController.deleteTransaction(transactionId);
        response = transactionController.updateTransaction(transactionId, transaction);
        Assertions.assertEquals((String)response.getBody(), "交易已经删除");
    }

    // 删除交易测试
    @Test
    void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        transaction.setUserId("user1");
        transaction.setAmount(new BigDecimal(12.895));
        ResponseEntity response = transactionController.createTransaction(transaction);

        String transactionId = ((Transaction) response.getBody()).getTransactionId();

        response = transactionController.deleteTransaction("jy2");
        Assertions.assertEquals((String)response.getBody(), "交易不存在");

        response = transactionController.deleteTransaction(transactionId);
        Assertions.assertEquals((String)response.getBody(), "删除成功");
    }

    // 交易查询测试
    @Test
    void testGetAll() {
        Transaction transaction = new Transaction();
        transaction.setUserId("user1");
        transaction.setAmount(new BigDecimal(12.895));
        ResponseEntity response = transactionController.createTransaction(transaction);

        String transactionId = ((Transaction) response.getBody()).getTransactionId();

        response = transactionController.getAll();

        //TODO 提供清除map的方法， 并且校验返回结果的数量
//        Assertions.assertEquals(((List<Transaction>)response.getBody()).size(), 4);

        transactionController.deleteTransaction(transactionId);
        response = transactionController.getAll();
        // TODO 检验返回的数据中是否有刚才已经删除的交易
    }
}