package com.example.service;

import com.example.model.Withdraw;

import java.math.BigDecimal;

public interface IWithdrawService extends IGeneratedService<Withdraw> {
    String withdraw(long id, BigDecimal withdrawMoney);
}
