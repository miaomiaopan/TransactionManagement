package com.example.TransactionManagement;

import com.example.TransactionManagement.controller.TransactionController;
import com.example.TransactionManagement.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.math.BigDecimal;
import java.util.Random;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Autowired
    TransactionController transactionController;

    // 性能测试，模拟500个用户同时访问创建交易的接口
    @Test
    void testPerformance() {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            executor.execute(() -> {
                Transaction transaction = new Transaction();
                float randomInt = random.nextFloat();
                transaction.setUserId("userTest");
                transaction.setAmount(new BigDecimal(randomInt));
                transactionController.createTransaction(transaction);
            });
        }
    }
}