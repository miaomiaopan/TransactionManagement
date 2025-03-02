package com.example.TransactionManagement.service;

import com.example.TransactionManagement.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: miaomiaopan
 * @CreateTime: 2025-03-02
 * @Description:
 * @Version: 1.0
 */
public interface TransactionService {
    public String add(Transaction transaction);
    public String update(Transaction transaction);
    public String delete(String transactionId);
    public List<Transaction> findAll();
    public Transaction findById(String transactionId);
}
