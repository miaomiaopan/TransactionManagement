package com.example.TransactionManagement.service.imp;

import com.example.TransactionManagement.entity.Transaction;
import com.example.TransactionManagement.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionServiceImpl implements TransactionService {
    private Map<String, Transaction> transactionsMap = new ConcurrentHashMap<>();

    @Override
    public String add(Transaction transaction) {
        String transactionId = UUID.randomUUID().toString();
        transaction.setTransactionId(transactionId);
        transactionsMap.put(transactionId, transaction);
        return transactionId;
    }

    @Override
    public String update(Transaction transaction) {
        transactionsMap.put(transaction.getTransactionId(), transaction);
        return transaction.getTransactionId();
    }

    @Override
    public String delete(String transactionId) {
        Transaction transaction = transactionsMap.get(transactionId);
        transactionsMap.put(transaction.getTransactionId(), transaction);
        return transaction.getTransactionId();
    }

    @Override
    public List<Transaction> findAll() {
        // TODO 过滤掉状态为delete的交易
        return new ArrayList<>(transactionsMap.values());
    }

    @Override
    public Transaction findById(String transactionId) {
        return transactionsMap.get(transactionId);
    }
}