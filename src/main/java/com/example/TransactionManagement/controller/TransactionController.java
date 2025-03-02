package com.example.TransactionManagement.controller;

import com.example.TransactionManagement.entity.Transaction;
import com.example.TransactionManagement.enums.Status;
import com.example.TransactionManagement.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    // 创建
    @PostMapping
    @Operation(summary = "增加交易")
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
        // TODO 添加用户字段校验
        if (transaction.getAmount() == null) {
            return new ResponseEntity<>("缺少金额", HttpStatus.OK);
        }
        Date date = new Date();
        transaction.setCreateTime(date);
        transaction.setUpdateTime(date);
        transaction.setStatus(Status.NORMAL);
        transactionService.add(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    // 读取
    @GetMapping()
    @Operation(summary = "获取交易列表")
    public ResponseEntity<List<Transaction>> getAll() {
        List<Transaction> transactionList =  transactionService.findAll();
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    // 更新
    @PutMapping("/{id}")
    @Operation(summary = "修改交易")
    public ResponseEntity<Object> updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        Transaction tempTransaction =  transactionService.findById(id);
        if (tempTransaction == null) {
            return new ResponseEntity<Object>("交易不存在", HttpStatus.OK);
        }

        if (Status.DELETE.equals(tempTransaction.getStatus())) {
            return new ResponseEntity<Object>("交易已经删除", HttpStatus.OK);
        }

        // TODO 校验用户ID:修改交易时用户ID改变

        transaction.setUpdateTime(new Date());
        transaction.setStatus(tempTransaction.getStatus());
        transaction.setTransactionId(id);
        transactionService.update(transaction);
         return new ResponseEntity<Object>(transaction, HttpStatus.OK);
    }

    // 删除
    @DeleteMapping("/{id}")
    @Operation(summary = "删除交易")
    public ResponseEntity<Object> deleteTransaction(@PathVariable String id) {
        Transaction tempTransaction =  transactionService.findById(id);
        if (tempTransaction == null) {
            return new ResponseEntity<Object>("交易不存在", HttpStatus.OK);
        }

        tempTransaction.setStatus(Status.DELETE);
        tempTransaction.setUpdateTime(new Date());
        transactionService.delete(id);
        return new ResponseEntity<Object>("删除成功", HttpStatus.OK);
    }
}