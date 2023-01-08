package com.example.service;

import com.example.model.Transfer;

import java.math.BigDecimal;

public interface ITransferService extends IGeneratedService<Transfer> {
    String transfer(long senderId, long recipientId, BigDecimal money);
}
