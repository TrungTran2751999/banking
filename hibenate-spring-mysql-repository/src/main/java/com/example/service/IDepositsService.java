package com.example.service;

import com.example.model.Deposits;

import java.math.BigDecimal;

public interface IDepositsService extends IGeneratedService<Deposits> {
    String deposits(long id, BigDecimal money);
}
