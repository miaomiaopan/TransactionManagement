package com.example.TransactionManagement.entity;

import com.example.TransactionManagement.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transaction {
    // 交易号
    private String transactionId;

    // 交易用户ID
    private String userId;

    // 创建时间
    @Schema(hidden = true)
    private Date createTime;

    // 修改时间
    @Schema(hidden = true)
    private Date updateTime;

    // 交易金额
    private BigDecimal amount;

    // 交易状态
    @Schema(hidden = true)
    private Status status;
}
